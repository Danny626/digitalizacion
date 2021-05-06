package com.albo.digitalizacion.service;

import java.time.LocalDateTime;
import java.util.List;

import com.albo.digitalizacion.dto.CantidadTipoErrorDTO;
import com.albo.digitalizacion.model.TipoError;

public interface ITipoErrorService extends IService<TipoError, String> {

	List<CantidadTipoErrorDTO> buscarTotalPorTipoError(LocalDateTime fechaFinalProceso);

}
