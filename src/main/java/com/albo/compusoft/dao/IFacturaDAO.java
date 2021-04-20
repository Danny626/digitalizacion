package com.albo.compusoft.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.albo.compusoft.model.Factura;
import com.albo.compusoft.model.FacturaPK;

@Repository
public interface IFacturaDAO extends JpaRepository<Factura, FacturaPK> {

}
