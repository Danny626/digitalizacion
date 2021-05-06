package com.albo.digitalizacion.dto;

import java.util.List;

public class ResultadoProcesoDTO {

	private int totalArchivosProcesados;
	private int totalRegistrosGeneral;
	private int totalRegistrosRelacion;
	private int totalRegistrosError;
	private List<CantidadTipoErrorDTO> listaCantidadTipoError;

	public ResultadoProcesoDTO() {
	}

	public ResultadoProcesoDTO(int totalArchivosProcesados, int totalRegistrosGeneral, int totalRegistrosRelacion,
			int totalRegistrosError, List<CantidadTipoErrorDTO> listaCantidadTipoError) {
		this.totalArchivosProcesados = totalArchivosProcesados;
		this.totalRegistrosGeneral = totalRegistrosGeneral;
		this.totalRegistrosRelacion = totalRegistrosRelacion;
		this.totalRegistrosError = totalRegistrosError;
		this.listaCantidadTipoError = listaCantidadTipoError;
	}

	public int getTotalArchivosProcesados() {
		return totalArchivosProcesados;
	}

	public void setTotalArchivosProcesados(int totalArchivosProcesados) {
		this.totalArchivosProcesados = totalArchivosProcesados;
	}

	public int getTotalRegistrosGeneral() {
		return totalRegistrosGeneral;
	}

	public void setTotalRegistrosGeneral(int totalRegistrosGeneral) {
		this.totalRegistrosGeneral = totalRegistrosGeneral;
	}

	public int getTotalRegistrosRelacion() {
		return totalRegistrosRelacion;
	}

	public void setTotalRegistrosRelacion(int totalRegistrosRelacion) {
		this.totalRegistrosRelacion = totalRegistrosRelacion;
	}

	public int getTotalRegistrosError() {
		return totalRegistrosError;
	}

	public void setTotalRegistrosError(int totalRegistrosError) {
		this.totalRegistrosError = totalRegistrosError;
	}

	public List<CantidadTipoErrorDTO> getListaCantidadTipoError() {
		return listaCantidadTipoError;
	}

	public void setListaCantidadTipoError(List<CantidadTipoErrorDTO> listaCantidadTipoError) {
		this.listaCantidadTipoError = listaCantidadTipoError;
	}
}
