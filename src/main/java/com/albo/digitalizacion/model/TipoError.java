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
@Table(name = "tipoError", schema = "PUBLIC")
public class TipoError implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "COD_ERROR", nullable = false, unique = true, length = 3)
	private String COD_ERROR;

	@Column(name = "DESCRIPCION", length = 50)
	private String DESCRIPCION;

	@Column(name = "RELEVANCIA", length = 1)
	private String RELEVANCIA;

	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "TIPO_ERROR")
	private List<Error> errores = new ArrayList<Error>();

	public String getCOD_ERROR() {
		return COD_ERROR;
	}

	public void setCOD_ERROR(String cOD_ERROR) {
		COD_ERROR = cOD_ERROR;
	}

	public String getDESCRIPCION() {
		return DESCRIPCION;
	}

	public void setDESCRIPCION(String dESCRIPCION) {
		DESCRIPCION = dESCRIPCION;
	}

	public String getRELEVANCIA() {
		return RELEVANCIA;
	}

	public void setRELEVANCIA(String rELEVANCIA) {
		RELEVANCIA = rELEVANCIA;
	}

	public List<Error> getErrores() {
		return errores;
	}

	public void setErrores(List<Error> errores) {
		this.errores = errores;
	}

}
