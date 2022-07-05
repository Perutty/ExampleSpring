package co.edu.ufps.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import lombok.Data;

@Entity
@Table (name="proyecto")
@Data
public class Proyect {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	@Column
	private Integer usuario_id;
	
	@Column
	private String titulo;
	
	@Column
	private String objetivo;
	
	@Column
	private String notas;
	
	@Column
	@DateTimeFormat(iso = ISO.DATE)
	private Date fechainicio;
	
	@Column
	@DateTimeFormat(iso = ISO.DATE)
	private Date fechafin;
	
	@Column
	private String estado;
	
	@Column
	@DateTimeFormat(iso = ISO.DATE)
	private Date fechacreacion;

	

	public Proyect (String titulo){
		this.titulo = titulo;
	}
	
	public Proyect(){
		
	}

}
