package com.ielpm.merchant.dh.demo.servlet;

import java.io.IOException;
import java.util.TreeMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.ielpm.mer.sdk.secret.CertUtil;
import com.ielpm.merchant.dh.demo.util.ParamUtil;

/**
 * 异步通知
 * 
 * 2018年8月20日
 * 
 * @author zcc
 */
public class NotifyServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(NotifyServlet.class);

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		try {
			// 利用treeMap对参数按key值进行排序
			TreeMap<String, String> transMap = ParamUtil.getParamMap(request);
			logger.info("[-后台通知-]-获取参数transMap=" + transMap);
			String sign = transMap.get("sign");
			transMap.remove("sign");
			// 拼接签名串
			String signMsg = ParamUtil.getSignMsg(transMap);
			// 验签
			boolean verifyResults = false;
			verifyResults = CertUtil.getInstance().verify(signMsg, sign);
			logger.info("[-异步通知-]-验签结果=" + verifyResults);
		} catch (Exception e) {
			logger.error("失败", e);
		}
	}

}
