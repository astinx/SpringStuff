package com.generic.server.ui.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/")
public class HomeController {

	@RequestMapping(value = "main/")
	public String main() {
	  return "main";
	}
	
	@RequestMapping(value = "login/")
	public String login() {
	  return "login";
	}

}