package com.albo.digitalizacion.dao;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.albo.digitalizacion.model.General;
import com.albo.digitalizacion.model.TipoDocumento;

@Repository
public interface IGeneralDAO extends JpaRepository<General, Long> {

	@Query("FROM General gral WHERE gral.cnsCodConc = :cnsCodConc AND gral.cnsEmisor = :cnsEmisor "
			+ "AND gral.tipoDocumento = :tipoDocumento AND gral.archivo.nomArchivo = :nombreArchivoDestino "
			+ "AND gral.cnsAduTra = :cnsAduTra AND gral.cnsNroTra = :cnsNroTra "
			+ "AND gral.recinto = :recinto AND gral.cnsEstado = 'A'")
	General buscarExistente(@Param("cnsCodConc") String cnsCodConc, @Param("cnsEmisor") String cnsEmisor,
			@Param("tipoDocumento") TipoDocumento tipoDocumento,
			@Param("nombreArchivoDestino") String nombreArchivoDestino, @Param("cnsAduTra") String cnsAduTra,
			@Param("cnsNroTra") String cnsNroTra, @Param("recinto") String recinto);

	@Query("SELECT COUNT(*) as cantidad FROM General gral WHERE gral.cnsFechaPro = :fechaFinalProceso AND gral.recinto = :recinto")
	Integer buscarTotalRegistrosGeneral(@Param("fechaFinalProceso") LocalDateTime fechaFinalProceso,
			@Param("recinto") String recinto);

	@Query(value = "FROM General gral WHERE gral.cnsFechaPro = :fechaFinalProceso")
	List<General> listarxFecha(@Param("fechaFinalProceso") LocalDateTime fechaFinalProceso);

}
