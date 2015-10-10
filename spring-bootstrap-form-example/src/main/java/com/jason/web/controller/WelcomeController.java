package com.jason.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class WelcomeController {

	@RequestMapping(value = "/index*", method = RequestMethod.GET)
	public ModelAndView index() {

		ModelAndView model = new ModelAndView("forward:/static/html/index.html");
	
		return model;

	}

}