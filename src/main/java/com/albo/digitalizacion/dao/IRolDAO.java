package com.albo.digitalizacion.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.albo.digitalizacion.model.Rol;

@Repository
public interface IRolDAO extends JpaRepository<Rol, Long> {

}
