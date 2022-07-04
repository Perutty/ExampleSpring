package co.edu.ufps.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import co.edu.ufps.model.User;
import co.edu.ufps.services.UserService;

@Controller
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping("/")
	public String login(Model model) {
		return "index";
	}

	@PostMapping("/login")
	public String loginUser(RedirectAttributes ra , String email, String clave, HttpServletRequest request, HttpSession session, Model model) {

		User user = userService.select(email, clave);
		if (user != null) {
			request.getSession().setAttribute("user_id", user.getId());
			request.getSession().setAttribute("pais", user.getPais());
			return "menu-principal";
		} else {
			ra.addFlashAttribute("loginError", "Usuario o contraseña incorrecto");
			return "redirect:/";
		}
	}


	@GetMapping("/logout")
	public String cerrarSesion(HttpSession session){
		session.invalidate();
		return "redirect:/";
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

	@GetMapping("/menu")
	public String verMenu(){
		return "menu-principal";
	}
}
