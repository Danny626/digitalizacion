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
@Table(name = "tipo_error", schema = "PUBLIC")
public class TipoError implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "cod_error", nullable = false, unique = true, length = 3)
	private String codError;

	@Column(name = "descripcion", length = 50)
	private String descripcion;

	@Column(name = "relevancia", length = 1)
	private String relevancia;

	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "tipoError")
	private List<Error> errores = new ArrayList<Error>();

	public String getCodError() {
		return codError;
	}

	public void setCodError(String codError) {
		this.codError = codError;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getRelevancia() {
		return relevancia;
	}

	public void setRelevancia(String relevancia) {
		this.relevancia = relevancia;
	}

	public List<Error> getErrores() {
		return errores;
	}

	public void setErrores(List<Error> errores) {
		this.errores = errores;
	}

}
