package co.edu.ufps.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController 
@RequestMapping("/bills")
public class BillRest {
	
	@GetMapping
	public String getAll() {
		return "ok user";
	}
	@GetMapping("/{id}")
	public String getObject(@PathVariable Integer id) {
		return "ok user " + id;
	}
	
	@PostMapping
	public String postObject() {
		return "POST";
	}
	
	@PutMapping("/{id}")
	public String putObject(@PathVariable Integer id) {
		return "PUT";
	}
	
	@DeleteMapping("/{id}")
	public String deleteObject(@PathVariable Integer id) {
		return "DELETE";
	}

}
