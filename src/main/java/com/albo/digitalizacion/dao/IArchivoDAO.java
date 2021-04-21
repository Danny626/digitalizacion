package com.albo.digitalizacion.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.albo.digitalizacion.model.Archivo;

@Repository
public interface IArchivoDAO extends JpaRepository<Archivo, Long> {

}
