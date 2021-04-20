package com.albo.dao.soa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.albo.model.soa.Inventario;

@Repository
public interface IInventarioDAO extends JpaRepository<Inventario, String> {

	@Query("FROM Inventario inv WHERE inv.invParte = :invParte AND inv.invGestion = :gestion")
	Inventario buscarPorParte(@Param("invParte") String invParte, @Param("gestion") String gestion);

}
