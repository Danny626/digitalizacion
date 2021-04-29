package com.albo.digitalizacion.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.albo.digitalizacion.model.Prefijo;

@Repository
public interface IPrefijoDAO extends JpaRepository<Prefijo, String> {

}
