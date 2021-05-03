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

	@Query("FROM Inventario inv WHERE inv.invParte = :invParte AND inv.invGestion = :gestion AND inv.invEstado = 'ACT'")
	Inventario buscarPorParte(@Param("invParte") String invParte, @Param("gestion") String gestion);

	@Query("FROM Inventario inv WHERE inv.invNro = :invNro AND inv.invRecinto.recCod = :invRecinto "
			+ "AND inv.invFecha BETWEEN :fechaProcesoInicio AND :fechaProcesoFin AND inv.invEstado = 'ACT'")
	List<Inventario> buscarPorNroInventario(@Param("invNro") String invNro, @Param("invRecinto") String invRecinto,
			@Param("fechaProcesoInicio") LocalDateTime fechaProcesoInicio,
			@Param("fechaProcesoFin") LocalDateTime fechaProcesoFin);

}
