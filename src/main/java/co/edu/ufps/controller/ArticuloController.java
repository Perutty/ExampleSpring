package co.edu.ufps.controller;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

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

	private List<Articulo> listMostrar = new ArrayList<>();

	@Autowired
	private ArticuloService articuloService;

	@RequestMapping("/list")
	public String listArticulos(Model model, HttpServletRequest sr) {
		
		List<Articulo> listActual = articuloService.getAll();
		listMostrar.clear();
		int user_id = (int) sr.getSession().getAttribute("user_id");
		listActual.forEach((arts)->{
			if(arts.getUsuario_id() == user_id)
				listMostrar.add(arts);
		});

		model.addAttribute("list", listMostrar);
		return "articles";
	}

	@GetMapping("/new-article")
	public String nuevoArticulo(Model model) {
		return "form-article";
	}

	@PostMapping("/save-article")
	public String registerArticulo(Articulo article, Model model, HttpSession sesion, HttpServletRequest rq) {
		String recortar =  (String) rq.getSession().getAttribute("pais").toString();
		String pais_sustituto = recortar.substring(0, 3);
		article.setPais(pais_sustituto);
		article.setUsuario_id((int) rq.getSession().getAttribute("user_id"));
		
		Long datetime = System.currentTimeMillis();
		Timestamp timestamp = new Timestamp(datetime);
		article.setFecha_creacion(timestamp);
		
		
		articuloService.save(article);
		return "redirect:/articulo/list";
	}
}
