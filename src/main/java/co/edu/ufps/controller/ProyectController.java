package co.edu.ufps.controller;

import java.io.ByteArrayInputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import co.edu.ufps.model.Proyect;
import co.edu.ufps.services.ProyectService;

@Controller
@RequestMapping("/proyect")
public class ProyectController {

	private List<Proyect> listMostrar = new ArrayList<>();
	@Autowired
	private ProyectService proyectService;

	@GetMapping("/list")
	public String listProyect(HttpServletRequest request, Model model) {
		List<Proyect> listActual = proyectService.getAll();
		listMostrar.clear();
		int u_id = (int) request.getSession().getAttribute("user_id");
		listActual.forEach((proyect)->{
			if(proyect.getUsuario_id() == u_id)
				listMostrar.add(proyect);
		});
		model.addAttribute("listproyect", listMostrar);
		return "listproyect";
	}
	
	@GetMapping("/export/all/{id}")
	public ResponseEntity<InputStreamResource> exportAllData(@PathVariable("id")Integer id) throws Exception{
		ByteArrayInputStream stream = proyectService.exportAllData(id);
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", "attachment; filename=proyectos.xls");
		
		return ResponseEntity.ok().headers(headers).body(new InputStreamResource(stream));
	}
	
	@GetMapping("/edit/{id}")
	public String editProyect(RedirectAttributes att,@PathVariable("id") Integer id, Proyect proyecto, Model model) {
		if(id!= null) {
			model.addAttribute("proyect", proyectService.get(id));
			att.addFlashAttribute("accion", "¡Proyecto editado con éxito!");
		}else
		{
			model.addAttribute("proyect", new Proyect());
		}
		return "editproyect";
	}

	@GetMapping("/show")
	public String showNewForm(Model model) {
		return "registerproyect";
	}

	@GetMapping("/showproyect/{id}")
	public String showProyect(@PathVariable("id") Integer id, Model model) {
		if(id!= null) {
			model.addAttribute("viewproyect", proyectService.get(id));
		}
		return "verproyecto";
	}

	@PostMapping("/save")
	public String register(RedirectAttributes att,Proyect proyecto, HttpServletRequest request, HttpSession session, Model model) {
		Date dat = new Date();
		java.sql.Date sqlDate = new java.sql.Date(dat.getTime());
		int u_id = (int) request.getSession().getAttribute("user_id");
		proyecto.setUsuario_id(u_id);
		proyecto.setFechacreacion(sqlDate);
		proyectService.save(proyecto);
		att.addFlashAttribute("accion", "¡Proyecto guardado con éxito!");
		return "redirect:/proyect/list";
	}

	@GetMapping("/delete/{id}")
	public String delete(RedirectAttributes att,@PathVariable("id") Integer id, Model model) {
		proyectService.delete(id);
		att.addFlashAttribute("accion", "¡Proyecto eliminado con éxito!");
		return "redirect:/proyect/list";
	}

}
