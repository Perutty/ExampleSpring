package co.edu.ufps.controller;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import co.edu.ufps.model.Articulo;
import co.edu.ufps.services.ArticuloService;

@Controller
@RequestMapping("/articulo")
public class ArticuloController {

	@Autowired
	private ArticuloService articuloService;

	@RequestMapping("/list")
	public String listArticulos(Model model) {
		model.addAttribute("list", articuloService.getAll());
		return "articles";
	}

	@GetMapping("/new-article")
	public String nuevoArticulo(Model model) {
		return "form-article";
	}

	@PostMapping("/save-article")
	public String registerArticulo(Articulo article, Model model, HttpSession sesion, HttpServletRequest rq) {
		// String recortar =  (String) rq.getSession().getAttribute("pais").toString();
		// String pais_sustituto = recortar.substring(0, 3);
		// article.setPais(pais_sustituto);
		article.setUsuario_id((int) rq.getSession().getAttribute("user_id"));
		Long datetime = System.currentTimeMillis();
		Timestamp timestamp = new Timestamp(datetime);
		article.setFecha_creacion(timestamp);
		articuloService.save(article);
		return "redirect:/articulo/list";
	}
}
