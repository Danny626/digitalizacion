package com.albo.digitalizacion.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.albo.digitalizacion.model.ErrorProceso;

@Repository
public interface IErrorProcesoDAO extends JpaRepository<ErrorProceso, Long> {

}
