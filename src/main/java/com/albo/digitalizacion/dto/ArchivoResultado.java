package com.albo.digitalizacion.dto;

import com.albo.soa.model.DocArchivo;
import com.albo.soa.model.Inventario;

public class ArchivoResultado {

	private String nuevoNombreArchivo;
	private Inventario inventario;
	private String tipoDocArchivo;
	private String gestion;
	private String codAduana;
	private String nroArchivo;
	private DocArchivo docArchivo;

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

	public String getGestion() {
		return gestion;
	}

	public void setGestion(String gestion) {
		this.gestion = gestion;
	}

	public String getCodAduana() {
		return codAduana;
	}

	public void setCodAduana(String codAduana) {
		this.codAduana = codAduana;
	}

	public String getNroArchivo() {
		return nroArchivo;
	}

	public void setNroArchivo(String nroArchivo) {
		this.nroArchivo = nroArchivo;
	}

	public DocArchivo getDocArchivo() {
		return docArchivo;
	}

	public void setDocArchivo(DocArchivo docArchivo) {
		this.docArchivo = docArchivo;
	}

}
