package com.albo.digitalizacion.dao;

import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.albo.digitalizacion.model.Archivo;

@Repository
public interface IArchivoDAO extends JpaRepository<Archivo, Long> {

	@Query("SELECT COUNT(*) as cantidad FROM Archivo arc WHERE arc.recinto = :recinto AND arc.fecPro = :fechaFinalProceso")
	Integer buscarTotalRegistrosPorRecinto(@Param("recinto") String recinto,
			@Param("fechaFinalProceso") LocalDateTime fechaFinalProceso);

}
