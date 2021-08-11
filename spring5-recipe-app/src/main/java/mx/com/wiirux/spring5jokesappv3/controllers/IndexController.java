package mx.com.wiirux.spring5jokesappv3.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
	
	@RequestMapping({"", "/", "/index"})
	public String getIndexPage(Model m) {
		return "index";
	}
}
