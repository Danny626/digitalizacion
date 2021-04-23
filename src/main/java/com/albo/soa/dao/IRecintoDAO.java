package com.albo.soa.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.albo.soa.model.Recinto;

@Repository
public interface IRecintoDAO extends JpaRepository<Recinto, String> {

}
