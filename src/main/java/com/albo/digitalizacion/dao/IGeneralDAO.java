package com.albo.digitalizacion.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.albo.digitalizacion.model.General;

@Repository
public interface IGeneralDAO extends JpaRepository<General, Long> {

}
