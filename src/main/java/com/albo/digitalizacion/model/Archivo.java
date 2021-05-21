package com.albo.digitalizacion.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

@Entity
@Table(name = "archivo", schema = "public")
public class Archivo implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id", nullable = false, unique = true)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	// archivo destino
	@Column(name = "nom_archivo", length = 100)
	private String nomArchivo;

	@Column(name = "origen", nullable = false, length = 100)
	private String origen;

	@Column(name = "destin", length = 100)
	private String destin;

	@JsonSerialize(using = ToStringSerializer.class)
	@Column(name = "fec_pro")
	private LocalDateTime fecPro;

	@Column(name = "recinto", length = 10)
	private String recinto;

	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "archivo")
	private List<General> generales = new ArrayList<General>();

	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "archivo")
	private List<ErrorProceso> errores = new ArrayList<ErrorProceso>();

	public String getNomArchivo() {
		return nomArchivo;
	}

	public void setNomArchivo(String nomArchivo) {
		this.nomArchivo = nomArchivo;
	}

	public String getOrigen() {
		return origen;
	}

	public void setOrigen(String origen) {
		this.origen = origen;
	}

	public String getDestin() {
		return destin;
	}

	public void setDestin(String destin) {
		this.destin = destin;
	}

	public LocalDateTime getFecPro() {
		return fecPro;
	}

	public void setFecPro(LocalDateTime fecPro) {
		this.fecPro = fecPro;
	}

	public List<General> getGenerales() {
		return generales;
	}

	public void setGenerales(List<General> generales) {
		this.generales = generales;
	}

	public List<ErrorProceso> getErrores() {
		return errores;
	}

	public void setErrores(List<ErrorProceso> errores) {
		this.errores = errores;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRecinto() {
		return recinto;
	}

	public void setRecinto(String recinto) {
		this.recinto = recinto;
	}
}
