package com.albo.digitalizacion.dao;

import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.albo.digitalizacion.model.ErrorProceso;

@Repository
public interface IErrorProcesoDAO extends JpaRepository<ErrorProceso, Long> {

	@Query("SELECT COUNT(*) as cantidad FROM ErrorProceso ep WHERE ep.fecPro = :fechaFinalProceso AND ep.e3Cod = :recinto")
	Integer buscarTotalRegistrosError(@Param("fechaFinalProceso") LocalDateTime fechaFinalProceso,
			@Param("recinto") String recinto);
}
