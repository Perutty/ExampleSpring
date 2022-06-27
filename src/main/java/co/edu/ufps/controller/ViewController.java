package co.edu.ufps.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ViewController {
	
	@GetMapping({"/index","/"})
	public String index(HttpServletRequest request, Model modelo) {
		
		return "index";
	}

}
