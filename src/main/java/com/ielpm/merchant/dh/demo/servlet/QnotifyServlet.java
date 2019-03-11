package com.ielpm.merchant.dh.demo.servlet;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.ielpm.merchant.dh.demo.util.ParamUtil;

/**
 * 前台通知
 * 
 * 2018年8月20日
 * 
 * @author zcc
 */
public class QnotifyServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(QnotifyServlet.class);

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		String redirectPath = "result.jsp";
		String msg = "处理失败，请重试";

		try {
			// 利用treeMap对参数按key值进行排序
			Map<String, String> transMap = ParamUtil.getParamMap(request);
			msg = transMap.toString();
			logger.info("[-前台通知-]transMap=" + transMap);
		} catch (Exception e) {
			msg = "订单报文处理失败";
		} finally {
			request.setAttribute("errorMsg", msg);
			request.getRequestDispatcher(redirectPath).forward(request, response);
		}
	}
}
