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

/**
 * 代还绑卡
 * 
 * 2018年11月8日
 * @author zcc
 */
public class DhBindServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(DhBindServlet.class);
	private static final String TAG = "【绑卡】-";

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");

		String msg = "处理失败，请重试";
		String redirectPath = "send.jsp";
		try {
			// 1、利用treeMap对参数按key值进行排序
			Map<String, String> transMap = ParamUtil.getParamMap(request);

			String merchantNo = Config.getInstance().getMerchantNo();

			String cardNum = transMap.get("cardNum"); // 卡号(需要加密)
			String userName = transMap.get("userName"); // (需要加密)
			String certificateNum = transMap.get("certificateNum"); // (需要加密)
			String mobile = transMap.get("mobile"); //(需要加密)
			
			
			
			// 组织交易报文
			// 交易流水号
			String tranSerialNum = UUID.randomUUID().toString().replaceAll("-", "");
			transMap.put("merchantNo", merchantNo);
			transMap.put("tranSerialNum", tranSerialNum);
			// 敏感信息加密
			transMap.put("cardNum", CertUtil.getInstance().encrypt(cardNum));
			transMap.put("userName", CertUtil.getInstance().encrypt(userName));
			transMap.put("certificateNum", CertUtil.getInstance().encrypt(certificateNum));
			transMap.put("mobile", CertUtil.getInstance().encrypt(mobile));

			// 组织签名字符串
			String signMsg = ParamUtil.getSignMsg(transMap);
			logger.info(TAG + "[签名串]signMsg={}", signMsg);
			// 签名
			String sign = CertUtil.getInstance().sign(signMsg);
			// 将签名放入交易map中
			transMap.put("sign", sign);

			logger.debug(TAG + "[请求报文]transMap={}", transMap);
			logger.debug(TAG + "[请求地址]url={}", Config.getInstance().getDhBindUrl());

			request.setAttribute("action", Config.getInstance().getDhBindUrl());
			request.setAttribute("dataMap", transMap);

		} catch (Exception e) {
			logger.error("绑卡异常，", e);
			redirectPath = "error.jsp";
		} finally {
			request.setAttribute("errorMsg", msg);
			request.getRequestDispatcher(redirectPath).forward(request, response);
		}
	}

}
