package com.flur.exception;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.flur.api.ApiMsg;

public class ExceptionResolver implements HandlerExceptionResolver {

	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
		ApiMsg msg = new ApiMsg(ApiMsg.EXCEPTION);
		response.setContentType("application/json");
		try {
			response.getWriter().write(JSONObject.toJSONString(msg));
		} catch (IOException e) {
			e.printStackTrace();
		}
		ex.printStackTrace();
		return new ModelAndView();
	}

}
