package com.ielpm.merchant.dh.demo.servlet;

import java.io.IOException;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ielpm.mer.sdk.secret.CertUtil;
import com.ielpm.merchant.dh.demo.common.Config;
import com.ielpm.merchant.dh.demo.util.ParamUtil;
import com.ielpm.merchant.dh.demo.util.ResponseUtil;
import com.ielpm.merchant.dh.demo.util.http.Httpz;

/**
 * 代还绑卡
 * 
 * 2018年10月15日
 * 
 * @author zcc
 */
public class DhUnbindServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(DhUnbindServlet.class);
	private static final String TAG = "【解绑】-";

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");

		String msg = "处理失败，请重试";
		String redirectPath = "result.jsp";
		Map<String, String> resultMap = null;
		try {
			// 1、利用treeMap对参数按key值进行排序
			Map<String, String> transMap = ParamUtil.getParamMap(request);

			String merchantNo = Config.getInstance().getMerchantNo();
			String cardNum = transMap.get("cardNum"); // 卡号(需要加密)
			// 交易流水号
			String tranSerialNum = UUID.randomUUID().toString().replaceAll("-", "");

			// 组织交易报文
			transMap.put("merchantNo", merchantNo);
			transMap.put("tranSerialNum", tranSerialNum);

			// 敏感信息加密
			transMap.put("cardNum", CertUtil.getInstance().encrypt(cardNum));

			// 组织签名字符串
			String signMsg = ParamUtil.getSignMsg(transMap);
			logger.info(TAG + "[签名串]signMsg={}", signMsg);
			// 签名
			String sign = CertUtil.getInstance().sign(signMsg);
			// 将签名放入交易map中
			transMap.put("sign", sign);

			// 发送查询请求报文
			logger.info(TAG + "[请求报文]transMap={}", transMap);
			String asynMsg = new Httpz().post(Config.getInstance().getDhUnbindUrl(), transMap);
			logger.info(TAG + "[响应报文]响应消息={}", asynMsg);
			// 解析返回
			resultMap = ResponseUtil.parseResponse(asynMsg);
			resultMap.remove("sign");
			msg = resultMap.get("rtnMsg").toString();

		} catch (Exception e) {
			logger.error("绑卡异常，", e);
			redirectPath = "error.jsp";
		} finally {
			request.setAttribute("resultMap", resultMap);
			request.setAttribute("errorMsg", msg);
			request.getRequestDispatcher(redirectPath).forward(request, response);
		}
	}

}
