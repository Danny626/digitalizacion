package com.albo.soa.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.albo.soa.model.Inventario;

@Repository
public interface IInventarioDAO extends JpaRepository<Inventario, String> {

	@Query("FROM Inventario inv WHERE inv.invParte = :invParte AND inv.invGestion = :gestion")
	Inventario buscarPorParte(@Param("invParte") String invParte, @Param("gestion") String gestion);

	@Query("FROM Inventario inv WHERE inv.invNro = :invNro AND inv.invGestion = :gestion AND inv.invAduana = :invAduana")
	Inventario buscarPorNroInventario(@Param("invNro") String invNro, @Param("gestion") String gestion,
			@Param("invAduana") String invAduana);

}
