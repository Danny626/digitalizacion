package com.albo.digitalizacion.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.albo.digitalizacion.model.TipoDocumento;

@Repository
public interface ITipoDocumentoDAO extends JpaRepository<TipoDocumento, String> {

}
