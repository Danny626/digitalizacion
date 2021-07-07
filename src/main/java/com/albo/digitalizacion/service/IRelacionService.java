package com.albo.digitalizacion.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.albo.digitalizacion.model.Relacion;
import com.albo.digitalizacion.model.TipoDocumento;

public interface IRelacionService extends IService<Relacion, Long> {

	Optional<Relacion> buscarExistente(String cnsAduTra1, String cnsNroTra1, String cnsEmisor1,
			LocalDateTime cnsFechaEmi1, String cnsAduTra2, String cnsNroTra2, String cnsEmisor2,
			LocalDateTime cnsFechaEmi2, TipoDocumento tipoDocumento1, TipoDocumento tipoDocumento2, String recinto);

	Integer buscarTotalRegistrosRelacion(LocalDateTime fechaFinalProceso, String recinto);

	List<Relacion> listarxFecha(LocalDateTime fechaFinalProceso);
}
