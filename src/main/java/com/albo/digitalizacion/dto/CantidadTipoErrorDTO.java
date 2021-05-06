package com.albo.digitalizacion.dto;

import com.albo.digitalizacion.model.TipoError;

public class CantidadTipoErrorDTO {

	private Long cantidad;
	private TipoError tipoError;

	public CantidadTipoErrorDTO() {
	}

	public CantidadTipoErrorDTO(Long cantidad, TipoError tipoError) {
		this.cantidad = cantidad;
		this.tipoError = tipoError;
	}

	public Long getCantidad() {
		return cantidad;
	}

	public void setCantidad(Long cantidad) {
		this.cantidad = cantidad;
	}

	public TipoError getTipoError() {
		return tipoError;
	}

	public void setTipoError(TipoError tipoError) {
		this.tipoError = tipoError;
	}
}
