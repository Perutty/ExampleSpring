package co.edu.ufps.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;


@Entity
@Table (name="usuario")
@Data
public class User {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column
	private String nombre;
	
	@Column
	private String email;
	
	@Column
	private String clave;
	
	@Column
	private String pais;
	
	@Column
	private String entidad;

	@Column
	private String estado;
	
	public User (String email, String clave){
		this.email = email;
		this.clave = clave;
	}

	public User() {
	}
}
