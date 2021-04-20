package com.albo.dao.soa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.albo.model.soa.DocArchivo;
import com.albo.model.soa.DocArchivoPK;

@Repository
public interface IDocArchivoDAO extends JpaRepository<DocArchivo, DocArchivoPK> {

}
