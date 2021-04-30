package com.albo.digitalizacion.dto;

import com.albo.compusoft.model.Factura;
import com.albo.digitalizacion.model.TipoDocumento;
import com.albo.soa.model.DocArchivo;
import com.albo.soa.model.Inventario;

public class ArchivoResultadoDTO {

	private String nuevoNombreArchivo;
	private String gestion;
	private String codAduana;
	private String nroArchivo;
	private TipoDocumento tipoDocumento;
	private String codError;
	private String tramite;
	private Inventario inventario;
	private Factura factura;
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

	public TipoDocumento getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(TipoDocumento tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public String getCodError() {
		return codError;
	}

	public void setCodError(String codError) {
		this.codError = codError;
	}

	public String getTramite() {
		return tramite;
	}

	public void setTramite(String tramite) {
		this.tramite = tramite;
	}

	public Factura getFactura() {
		return factura;
	}

	public void setFactura(Factura factura) {
		this.factura = factura;
	}

}
