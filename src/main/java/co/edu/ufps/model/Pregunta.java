package co.edu.ufps.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="pregunta")
@Data
public class Pregunta {
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column
	private Integer proyecto_id;
	
	@Column
	private String pregunta;
	
	@Column
	private String cadena;
	
	@Column
	private String notas;
	
	
	public Pregunta(Integer proyecto_id, String pregunta) {
		this.proyecto_id = proyecto_id;
		this.pregunta = pregunta;
	}

	public Pregunta() {}
}
