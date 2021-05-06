package com.albo.digitalizacion.service;

import java.time.LocalDateTime;

import com.albo.digitalizacion.model.ErrorProceso;

public interface IErrorProcesoService extends IService<ErrorProceso, Long> {

	Integer buscarTotalRegistrosError(LocalDateTime fechaFinalProceso);

}
