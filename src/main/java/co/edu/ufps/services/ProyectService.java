package co.edu.ufps.services;

import java.io.ByteArrayInputStream;

import co.edu.ufps.commands.GenericService;
import co.edu.ufps.model.Proyect;

public interface ProyectService extends GenericService<Proyect, Integer>{
	public Proyect select(String titulo);
	
	ByteArrayInputStream exportAllData(int id) throws Exception;
}
