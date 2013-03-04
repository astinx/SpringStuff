package ar.com.kimboo.server.ui.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/server")
public class HomeController {

	@RequestMapping(value = "/home")
	public @ResponseBody String home() {
	  return "login";
	}
	
	@RequestMapping(value = "/home/main")
	public @ResponseBody String main() {
	  return "main";
	}
	
	@RequestMapping(value = "home/about")
	public @ResponseBody String about() {
	  return "about";
	}

}