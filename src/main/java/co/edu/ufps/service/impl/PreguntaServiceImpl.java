package co.edu.ufps.service.impl;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import co.edu.ufps.commands.GenericServiceImpl;
import co.edu.ufps.model.Pregunta;
import co.edu.ufps.repository.PreguntaRepository;
import co.edu.ufps.services.PreguntaService;

@Service
public class PreguntaServiceImpl extends GenericServiceImpl<Pregunta,Integer> implements PreguntaService{

	@Autowired
	private PreguntaRepository preguntaRepository;
	
	public CrudRepository<Pregunta, Integer> getDao(){
		return preguntaRepository;
	}
}
