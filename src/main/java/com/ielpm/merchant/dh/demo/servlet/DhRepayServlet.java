package com.ielpm.merchant.dh.demo.servlet;

import java.io.IOException;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Pattern;

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
 * 代还还款交易
 * 
 * 2018年11月08日
 * 
 * @author zcc
 */
public class DhRepayServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(DhRepayServlet.class);
	private static final String TAG = "【代还还款交易】-";

	private static final String REGEX_NUMBER = "^[-\\+]?[\\d]*$";

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

			// 2、金额参数判断
			/*String amount = transMap.get("amount");
			if (!isInteger(amount)) {
				logger.error("金额格式不正确，单位是分(内部参数校验失败)。");
				msg = "金额格式不正确，单位是分(内部参数校验失败)。";
				throw new Exception("金额格式不正确。");
			}*/

			String merchantNo = Config.getInstance().getMerchantNo();
			// 交易流水号
			String tranSerialNum = UUID.randomUUID().toString().replaceAll("-", "");

			// 组织交易报文
			transMap.put("merchantNo", merchantNo);
			transMap.put("tranSerialNum", tranSerialNum);

			// 组织签名字符串
			String signMsg = ParamUtil.getSignMsg(transMap);
			logger.info(TAG + "[签名串]signMsg={}", signMsg);
			// 签名
			String sign = CertUtil.getInstance().sign(signMsg);
			// 将签名放入交易map中
			transMap.put("sign", sign);

			// 发送扫码请求报文
			logger.info(TAG + "[请求报文]transMap={}", transMap);
			String asynMsg = new Httpz().post(Config.getInstance().getDhRepayUrl(), transMap);
			logger.info(TAG + "[返回报文]asynMsg={}", asynMsg);

			// 解析返回
			resultMap = ResponseUtil.parseResponse(asynMsg);
			msg = resultMap.get("rtnMsg").toString();
		} catch (Exception e) {
			redirectPath = "error.jsp";
		} finally {
			request.setAttribute("resultMap", resultMap);
			request.setAttribute("errorMsg", msg);
			request.getRequestDispatcher(redirectPath).forward(request, response);
		}
		return;
	}

	public static boolean isInteger(String str) {
		Pattern pattern = Pattern.compile(REGEX_NUMBER);
		return pattern.matcher(str).matches();
	}

}
