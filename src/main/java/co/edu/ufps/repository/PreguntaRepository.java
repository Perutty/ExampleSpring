package co.edu.ufps.repository;

import org.springframework.data.repository.CrudRepository;

import co.edu.ufps.model.Pregunta;

public interface PreguntaRepository extends CrudRepository<Pregunta, Integer> {
}
