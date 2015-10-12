package base.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
public class AppController {

	/*
	 * This method will list all existing employees.
	 */
	@RequestMapping(value = { "/"}, method = RequestMethod.GET)
	public String listEmployees(ModelMap model) {

		return "index";
	}
}
