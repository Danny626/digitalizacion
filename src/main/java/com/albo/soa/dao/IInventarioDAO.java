package com.albo.soa.dao;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.albo.soa.model.Inventario;

@Repository
public interface IInventarioDAO extends JpaRepository<Inventario, String> {

	@Query("FROM Inventario inv WHERE inv.invNro = :invNro AND inv.invRecinto.recCod = :invRecinto "
			+ "AND inv.invFecha BETWEEN :fechaProcesoInicio AND :fechaProcesoFin "
			+ "AND inv.invEstado = 'ACT' AND inv.invConsol IS NULL")
	List<Inventario> buscarPorNroInventarioNoConsolidado(@Param("invNro") String invNro,
			@Param("invRecinto") String invRecinto, @Param("fechaProcesoInicio") LocalDateTime fechaProcesoInicio,
			@Param("fechaProcesoFin") LocalDateTime fechaProcesoFin);

	@Query("FROM Inventario inv WHERE inv.invNro = :invNro AND inv.invRecinto.recCod = :invRecinto "
			+ "AND inv.invFecha BETWEEN :fechaProcesoInicio AND :fechaProcesoFin "
			+ "AND inv.invEstado = 'ACT' AND inv.invConsol = :consolidado")
	List<Inventario> buscarPorNroInventarioConsolidado(@Param("invNro") String invNro,
			@Param("invRecinto") String invRecinto, @Param("fechaProcesoInicio") LocalDateTime fechaProcesoInicio,
			@Param("fechaProcesoFin") LocalDateTime fechaProcesoFin, @Param("consolidado") String consolidado);

}
