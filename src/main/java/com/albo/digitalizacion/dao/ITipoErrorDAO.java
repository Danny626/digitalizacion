package com.albo.digitalizacion.dao;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.albo.digitalizacion.dto.CantidadTipoErrorDTO;
import com.albo.digitalizacion.model.TipoError;

@Repository
public interface ITipoErrorDAO extends JpaRepository<TipoError, String> {

	@Query("SELECT new com.albo.digitalizacion.dto.CantidadTipoErrorDTO(COUNT(ep.tipoError) as cantidad, ep.tipoError as tipoError) "
			+ "FROM ErrorProceso ep JOIN ep.tipoError WHERE ep.fecPro = :fechaFinalProceso GROUP BY ep.tipoError")
	List<CantidadTipoErrorDTO> buscarTotalPorTipoError(@Param("fechaFinalProceso") LocalDateTime fechaFinalProceso);

}
