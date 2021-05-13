package com.albo.soa.service;

import java.util.Optional;

import com.albo.digitalizacion.service.IService;
import com.albo.soa.model.DocArchivo;
import com.albo.soa.model.DocArchivoPK;

public interface IDocArchivoService extends IService<DocArchivo, DocArchivoPK> {

	Optional<DocArchivo> buscarPorNroSalida(String nroSalida, String codRecinto, Integer gestion);

}
