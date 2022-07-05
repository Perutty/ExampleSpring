package co.edu.ufps.repository;

import org.springframework.data.repository.CrudRepository;
import co.edu.ufps.model.Proyect;

public interface ProyectRepository extends CrudRepository<Proyect, Integer> {
	
	public abstract Proyect findByTitulo(String titulo);

}
