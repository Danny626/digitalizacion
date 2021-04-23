package com.albo.digitalizacion.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.albo.digitalizacion.model.Relacion;

@Repository
public interface IRelacionDAO extends JpaRepository<Relacion, Long> {

}
