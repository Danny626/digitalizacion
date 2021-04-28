package com.albo.soa.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.albo.soa.model.DocArchivo;
import com.albo.soa.model.DocArchivoPK;

@Repository
public interface IDocArchivoDAO extends JpaRepository<DocArchivo, DocArchivoPK> {

	@Query("FROM DocArchivo doa WHERE doa.docArchivoPK.darNro LIKE :nroSalida AND "
			+ "doa.docArchivoPK.recCod = :codRecinto AND doa.docArchivoPK.darGestion = :gestion AND doa.darEstado = 'ACT'")
	DocArchivo buscarPorNroSalida(@Param("nroSalida") String nroSalida, @Param("codRecinto") String codRecinto,
			@Param("gestion") Integer gestion);

}
