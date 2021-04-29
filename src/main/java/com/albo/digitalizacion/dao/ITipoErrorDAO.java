package com.albo.digitalizacion.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.albo.digitalizacion.model.TipoError;

@Repository
public interface ITipoErrorDAO extends JpaRepository<TipoError, String> {

}
