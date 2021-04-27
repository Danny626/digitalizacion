package com.albo.digitalizacion.service;

import java.time.LocalDateTime;
import java.util.List;

import com.albo.digitalizacion.dto.TipoDocContGeneralDTO;
import com.albo.digitalizacion.model.Total;

public interface ITotalService extends IService<Total, Long> {

	List<TipoDocContGeneralDTO> buscarDistintos(LocalDateTime fechaProceso);

}
