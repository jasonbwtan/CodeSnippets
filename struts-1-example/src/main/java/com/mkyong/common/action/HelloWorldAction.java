package com.mkyong.common.action;

import java.io.PrintWriter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.mkyong.common.form.HelloWorldForm;

public class HelloWorldAction extends Action {

	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		PrintWriter out = response.getWriter();

		HelloWorldForm helloWorldForm = (HelloWorldForm) form;
		String headers = "request headers:" + request.getHeaderNames() + ", ";
		String cookies = "";
		if (request.getCookies() != null) {
			for (Cookie cookie : request.getCookies()) {
				cookies = cookies + "request cookie:" + cookie.getName() + ":" + cookie.getValue() + ", ";
				response.addCookie(cookie);
			}
		}
		response.setHeader(HelloWorldAction.class.getName(), "true");
		helloWorldForm.setMessage("Hello World! Struts. Parameters:" + request.getParameter("param") + ","
				+ headers + cookies);
		// response.sendRedirect("/javax-example/Demo1");

		return mapping.findForward("success");
	}

}