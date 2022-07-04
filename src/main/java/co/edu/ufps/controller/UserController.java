package co.edu.ufps.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.ufps.model.User;
import co.edu.ufps.repository.UserRepository;

@RestController
@RequestMapping("/usuario")
public class UserController {
	
	@Autowired
	UserRepository userRepository;
	
	@GetMapping("/status")
	public String status() {
		return "ok";
	}
	
	@GetMapping
	public List <User> getAll() {
		return (List<User>) userRepository.findAll();
	}
	
	@GetMapping("/{id}")
	public User getObject(@PathVariable Integer id) {
		
		Optional user = userRepository.findById(id);
		if(user.isPresent()) {
			User u = (User) user.get();
			return u;
		}
		return null;
	}
	
	@PostMapping
	public User postObject(@RequestBody User userIn) {
		
		User entity = new User();
		entity.setNombre(userIn.getNombre());
		entity.setEmail(userIn.getEmail());
		entity.setClave(userIn.getClave());
		entity.setPais(userIn.getPais());
		entity.setEntidad(userIn.getEntidad());
		entity.setEstado(userIn.getEstado());
		
		userRepository.save(entity);
		return entity;
	}
	
	@PutMapping("/{id}")
	public User putObject( @PathVariable Integer id, @RequestBody User userIn) {
		
		Optional user = userRepository.findById(id);
		if(user.isPresent()) {
		User u = (User) user.get();
		User entity = u;
		entity.setNombre(userIn.getNombre());
		entity.setEmail(userIn.getEmail());
		entity.setClave(userIn.getClave());
		entity.setPais(userIn.getPais());
		entity.setEntidad(userIn.getEntidad());
		entity.setEstado(userIn.getEstado());
		userRepository.save(entity);
		return entity;
		}
		
		return null;
	}
	
	@DeleteMapping("/{id}")
	public User deleteObject(@PathVariable Integer id) {
		
		Optional user = userRepository.findById(id);
		if(user.isPresent()) {
			User u = (User) user.get();
			userRepository.delete(u);
			return u;
		}
		return null;
	}
}
