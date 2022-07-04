package co.edu.ufps.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table (name="articulo")
@Data
public class Articulo {
    
    @Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column
	private User usuario_id;
	
	@Column
	private String titulo;
	
	@Column
	private String ano;
	
	@Column
	private String autores;
	
	@Column
	private String citacion;

	@Column
	private User pais;

    @Column
	private String resumen;
	
	@Column
	private String conclusiones;

	@Column
	private User vacio;

    @Column
	private User url;

    @Column
	private User notas;

    @Column
	private String fecha_creacion;

    @Column
	private User revista;

    @Column
	private User categoria;

    @Column
	private User palabrasclave;

    
}
