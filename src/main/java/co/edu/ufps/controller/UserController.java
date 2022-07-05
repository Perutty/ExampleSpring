package co.edu.ufps.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import co.edu.ufps.model.User;
import co.edu.ufps.services.UserService;


@Controller
@RequestMapping("/user")
public class UserController {
	
    @Autowired
	private UserService userService;
	
    
	@GetMapping("/")
	public String login(Model model) {
		return "index";
	}
	
	@PostMapping("/login")
	public String validate(RedirectAttributes att, @RequestParam String email, @RequestParam String clave, HttpServletRequest request, HttpSession session,  Model model) {
		User u = userService.select(email, clave);
		if(u!=null)
		{
			request.getSession().setAttribute("user_id", u.getId());
			model.addAttribute("user", u);
			return "menu-principal";
		}else {
		att.addFlashAttribute("loginError", "Usuario o contraseña incorrecta");
		return "redirect:/";
		}
	}
	
	@GetMapping("/menu")
	public String menu(Model model) {
		return "menu-principal";
	}
	
	@GetMapping("/logout")
	public String logout(HttpServletRequest request, HttpSession session,  Model model) {
			request.getSession().invalidate();
			return "redirect:/";
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
