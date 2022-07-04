package co.edu.ufps.controller;


import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import co.edu.ufps.model.User;
import co.edu.ufps.services.UserService;


@Controller
public class ViewController {
	
    @Autowired
	private UserService userService;
	
    
	@GetMapping("/")
	public String login(Model model) {
		return "index";
	}

	@GetMapping("/login")
	public String loginUser(HttpServletRequest request,@PathVariable String email, @PathVariable String clave, Model model) {
		
		User user = userService.select(email, clave);
		if(user != null) {
			return "dashboard";
		}else {
			
	    request.setAttribute("loginError","Usuario o contraseña incorrecto");
		return "mostrar";
		}
	}
	
	@RequestMapping("/list")
	public String listUsers(Model model) {
		model.addAttribute("list", userService.getAll());
		return "mostrar";
	}
	
	@GetMapping("/show")
	public String showNewForm(Model model) {
		return "register";
	}
	
	@PostMapping("/save")
	public String register(User user, Model model) {
		userService.save(user);
		return "redirect:/list";
	}
	
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable Integer id, Model model) {
		userService.delete(id);
		return "redirect:/list";
	}
	

}
