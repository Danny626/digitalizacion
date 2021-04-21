package com.albo.digitalizacion.dto;

import com.albo.soa.model.Inventario;

public class ArchivoResultado {

	private String nuevoNombreArchivo;
	private Inventario inventario;
	private String tipoDocArchivo;

	public String getNuevoNombreArchivo() {
		return nuevoNombreArchivo;
	}

	public void setNuevoNombreArchivo(String nuevoNombreArchivo) {
		this.nuevoNombreArchivo = nuevoNombreArchivo;
	}

	public Inventario getInventario() {
		return inventario;
	}

	public void setInventario(Inventario inventario) {
		this.inventario = inventario;
	}

	public String getTipoDocArchivo() {
		return tipoDocArchivo;
	}

	public void setTipoDocArchivo(String tipoDocArchivo) {
		this.tipoDocArchivo = tipoDocArchivo;
	}

}
