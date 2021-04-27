package com.albo.digitalizacion.dto;

import com.albo.digitalizacion.model.TipoDocumento;

public class TipoDocContGeneralDTO {

	private Long cantidad;
	private TipoDocumento tipoDocumento;

	public TipoDocContGeneralDTO(Long cantidad, TipoDocumento tipoDocumento) {
		this.cantidad = cantidad;
		this.tipoDocumento = tipoDocumento;
	}

	public Long getCantidad() {
		return cantidad;
	}

	public void setCantidad(Long cantidad) {
		this.cantidad = cantidad;
	}

	public TipoDocumento getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(TipoDocumento tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

}
