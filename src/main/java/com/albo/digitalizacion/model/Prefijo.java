package com.albo.digitalizacion.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "prefijo", schema = "public")
public class Prefijo implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "codigo", nullable = false, unique = true, length = 1)
	private String codigo;

	@Column(name = "descripcion", length = 100)
	private String descripcion;

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

}
