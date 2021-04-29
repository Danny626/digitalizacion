package com.albo.digitalizacion.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "tipo_documento", schema = "PUBLIC")
public class TipoDocumento implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "codigo", nullable = false, length = 5, unique = true)
	private String codigo;

	@Column(name = "descripcion", length = 100)
	private String descripcion;

	@Column(name = "emisor", length = 100)
	private String emisor;

	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "tipoDocumento")
	private List<General> generales = new ArrayList<General>();

	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "tipoDocumento1")
	private List<Relacion> relaciones1 = new ArrayList<Relacion>();

	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "tipoDocumento2")
	private List<Relacion> relaciones2 = new ArrayList<Relacion>();

	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "tipoDocumento")
	private List<ErrorProceso> errores = new ArrayList<ErrorProceso>();

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getEmisor() {
		return emisor;
	}

	public void setEmisor(String emisor) {
		this.emisor = emisor;
	}

	public List<General> getGenerales() {
		return generales;
	}

	public void setGenerales(List<General> generales) {
		this.generales = generales;
	}

	public List<Relacion> getRelaciones1() {
		return relaciones1;
	}

	public void setRelaciones1(List<Relacion> relaciones1) {
		this.relaciones1 = relaciones1;
	}

	public List<Relacion> getRelaciones2() {
		return relaciones2;
	}

	public void setRelaciones2(List<Relacion> relaciones2) {
		this.relaciones2 = relaciones2;
	}

	public List<ErrorProceso> getErrores() {
		return errores;
	}

	public void setErrores(List<ErrorProceso> errores) {
		this.errores = errores;
	}

}
