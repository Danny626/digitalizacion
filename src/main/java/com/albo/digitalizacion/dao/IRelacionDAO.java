package com.albo.digitalizacion.dao;

import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.albo.digitalizacion.model.Relacion;
import com.albo.digitalizacion.model.TipoDocumento;

@Repository
public interface IRelacionDAO extends JpaRepository<Relacion, Long> {

	@Query("FROM Relacion rel WHERE rel.cnsAduTra1 = :cnsAduTra1 AND rel.cnsNroTra1 = :cnsNroTra1 "
			+ "AND rel.cnsEmisor1 = :cnsEmisor1 AND rel.cnsFechaEmi1 = :cnsFechaEmi1 AND rel.cnsAduTra2 = :cnsAduTra2 "
			+ "AND rel.cnsNroTra2 = :cnsNroTra2 AND rel.cnsEmisor2 = :cnsEmisor2 AND rel.cnsFechaEmi2 = :cnsFechaEmi2 "
			+ "AND rel.tipoDocumento1 = :tipoDocumento1 AND rel.tipoDocumento2 = :tipoDocumento2 "
			+ "AND rel.recinto = :recinto AND rel.cnsEstado = 'A'")
	Relacion buscarExistente(@Param("cnsAduTra1") String cnsAduTra1, @Param("cnsNroTra1") String cnsNroTra1,
			@Param("cnsEmisor1") String cnsEmisor1, @Param("cnsFechaEmi1") LocalDateTime cnsFechaEmi1,
			@Param("cnsAduTra2") String cnsAduTra2, @Param("cnsNroTra2") String cnsNroTra2,
			@Param("cnsEmisor2") String cnsEmisor2, @Param("cnsFechaEmi2") LocalDateTime cnsFechaEmi2,
			@Param("tipoDocumento1") TipoDocumento tipoDocumento1,
			@Param("tipoDocumento2") TipoDocumento tipoDocumento2, @Param("recinto") String recinto);

	@Query("SELECT COUNT(*) as cantidad FROM Relacion rel WHERE rel.fecPro = :fechaFinalProceso AND rel.recinto = :recinto")
	Integer buscarTotalRegistrosRelacion(@Param("fechaFinalProceso") LocalDateTime fechaFinalProceso,
			@Param("recinto") String recinto);

}
