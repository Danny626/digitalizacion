package com.albo.soa.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.albo.soa.model.DocArchivo;
import com.albo.soa.model.DocArchivoPK;

@Repository
public interface IDocArchivoDAO extends JpaRepository<DocArchivo, DocArchivoPK> {

}
