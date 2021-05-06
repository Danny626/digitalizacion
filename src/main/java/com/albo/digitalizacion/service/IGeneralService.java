package com.albo.digitalizacion.service;

import java.time.LocalDateTime;
import java.util.Optional;

import com.albo.digitalizacion.model.General;
import com.albo.digitalizacion.model.TipoDocumento;

public interface IGeneralService extends IService<General, Long> {

	Optional<General> buscarExistente(String cnsCodConc, String cnsEmisor, TipoDocumento tipoDocumento,
			String nombreArchivoDestino, String cnsAduTra, String cnsNroTra, LocalDateTime cnsFechaPro);

	Integer buscarTotalRegistrosGeneral(LocalDateTime fechaFinalProceso, String codRecinto);
}
