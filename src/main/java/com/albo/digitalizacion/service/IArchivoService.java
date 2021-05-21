package com.albo.digitalizacion.service;

import java.time.LocalDateTime;

import com.albo.digitalizacion.model.Archivo;

public interface IArchivoService extends IService<Archivo, Long> {

	Integer buscarTotalRegistrosPorRecinto(String recinto, LocalDateTime fechaFinalProceso);

}
