package co.edu.ufps.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table (name="articulo")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Articulo {
    
    @Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column
	private Integer usuario_id;
	
	@Column
	private String titulo;
	
	@Column
	private String ano;
	
	@Column
	private String autores;
	
	@Column
	private String citacion;

	@Column
	private String pais;

    @Column
	private String resumen;
	
	@Column
	private String conclusiones;

	@Column
	private String vacio;

    @Column
	private String url;

    @Column
	private String notas;

    @Column
	private Timestamp fecha_creacion;

    @Column
	private String revista;

    @Column
	private String categoria;

    @Column
	private String palabrasclave;


}
