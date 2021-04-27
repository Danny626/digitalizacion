package com.albo.digitalizacion.dao;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.albo.digitalizacion.dto.TipoDocContGeneralDTO;
import com.albo.digitalizacion.model.Total;

@Repository
public interface ITotalDAO extends JpaRepository<Total, Long> {

	@Query("SELECT new com.albo.digitalizacion.dto.TipoDocContGeneralDTO(COUNT(gral.tipoDocumento) as cantidad, gral.tipoDocumento as tipoDocumento) "
			+ "FROM General gral JOIN gral.tipoDocumento WHERE gral.cnsFechaPro = :fechaProceso "
			+ "GROUP BY gral.tipoDocumento, gral.tipoDocumento")
	List<TipoDocContGeneralDTO> buscarDistintos(@Param("fechaProceso") LocalDateTime fechaProceso);

}
