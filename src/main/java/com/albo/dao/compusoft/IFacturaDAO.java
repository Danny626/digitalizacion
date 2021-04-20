package com.albo.dao.compusoft;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.albo.model.compusoft.Factura;
import com.albo.model.compusoft.FacturaPK;

@Repository
public interface IFacturaDAO extends JpaRepository<Factura, FacturaPK> {

}
