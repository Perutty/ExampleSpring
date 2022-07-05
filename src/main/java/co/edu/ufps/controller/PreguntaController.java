package co.edu.ufps.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import co.edu.ufps.model.Pregunta;
import co.edu.ufps.model.Proyect;
import co.edu.ufps.services.PreguntaService;

@Controller
@RequestMapping("/pregunta")
public class PreguntaController {

	List<Pregunta> listMostrar = new ArrayList<>();
	@Autowired
	private PreguntaService preguntaService;
	
	
	@GetMapping("/list")
	public String listPreguntas(HttpServletRequest request, HttpSession session, Model model) {
		
		List<Pregunta> listActual = preguntaService.getAll();
		listMostrar.clear();
		int p_id = (int)request.getSession().getAttribute("pro_id");
		listActual.forEach((pregunta)->{
			if(pregunta.getProyecto_id() == p_id)
				listMostrar.add(pregunta);
		});
		model.addAttribute("preguntas", listMostrar);
		return "listpreguntas";
	}
	
	@GetMapping("/sesion/{id}")
	public String sesionP(@PathVariable("id") Integer id, HttpServletRequest request, HttpSession session, Model model) {
		request.getSession().setAttribute("pro_id", id);
		return "redirect:/pregunta/list";
	}
	
	@GetMapping("/show")
	public String show(HttpServletRequest request, HttpSession session, Model model) {
		return "registerpregunta";
	}
	
	
	@PostMapping("/save")
	public String register(@RequestParam String pregunta, HttpServletRequest request, HttpSession session, Model model) {
		
		int p_id = (int)request.getSession().getAttribute("pro_id");
			Pregunta p = new Pregunta(p_id,pregunta);
			p.setCadena(null);
			p.setNotas(null);
			preguntaService.save(p);
		return "redirect:/pregunta/list";
	}
	
	@GetMapping("/edit/{id}")
	public String editPregunta(@PathVariable("id") Integer id, Pregunta pregunta, Model model) {
		if(id!=null) {
			model.addAttribute("pregunta", preguntaService.get(id));
		}else {
			model.addAttribute("pregunta", new Pregunta());
		}
		return "editpregunta";
	}
	
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable("id") Integer id, Model model) {
		preguntaService.delete(id);
		return "redirect:/pregunta/list";
	}
}
