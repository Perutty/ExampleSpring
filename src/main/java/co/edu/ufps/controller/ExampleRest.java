package co.edu.ufps.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExampleRest {

	@GetMapping("/helloworld")
	public String hello() {
		return "Hello World!";
	}
	
	@GetMapping("/")
	public String ok() {
		return "ok page";
	}
	
}
