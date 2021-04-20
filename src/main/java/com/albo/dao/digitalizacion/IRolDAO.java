package com.albo.dao.digitalizacion;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.albo.model.digitalizacion.Rol;

@Repository
public interface IRolDAO extends JpaRepository<Rol, Long> {

}
