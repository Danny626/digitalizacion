package com.albo.compusoft.dao;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.albo.compusoft.model.Factura;
import com.albo.compusoft.model.FacturaPK;

@Repository
public interface IFacturaDAO extends JpaRepository<Factura, FacturaPK> {

	@Query("FROM Factura fac WHERE fac.facturaPK.e3Cod = :e3Cod AND fac.factNroreg LIKE :factNroreg "
			+ "AND fac.factFecha BETWEEN :fechaInicioProceso AND :fechaFinProceso AND fac.facturaPK.docCod = 'FA' "
			+ "AND fac.factEstado = 'CON'")
	List<Factura> buscarPorNroReg(@Param("e3Cod") String e3Cod, @Param("factNroreg") String factNroreg,
			@Param("fechaInicioProceso") LocalDateTime fechaInicioProceso,
			@Param("fechaFinProceso") LocalDateTime fechaFinProceso);

}
