package com.albo.digitalizacion.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.albo.digitalizacion.dao.IRelacionDAO;
import com.albo.digitalizacion.model.Relacion;
import com.albo.digitalizacion.model.TipoDocumento;
import com.albo.digitalizacion.service.IRelacionService;

@Service
public class RelacionServiceImpl implements IRelacionService {

	@Autowired
	private IRelacionDAO relacionDao;

	@Override
	public List<Relacion> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Relacion> findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Relacion saveOrUpdate(Relacion t) {
		return relacionDao.save(t);
	}

	@Override
	public String deleteById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Relacion> buscarExistente(String cnsAduTra1, String cnsNroTra1, String cnsEmisor1,
			LocalDateTime cnsFechaEmi1, String cnsAduTra2, String cnsNroTra2, String cnsEmisor2,
			LocalDateTime cnsFechaEmi2, TipoDocumento tipoDocumento1, TipoDocumento tipoDocumento2) {

		Optional<Relacion> optRelacion = Optional
				.ofNullable(relacionDao.buscarExistente(cnsAduTra1, cnsNroTra1, cnsEmisor1, cnsFechaEmi1, cnsAduTra2,
						cnsNroTra2, cnsEmisor2, cnsFechaEmi2, tipoDocumento1, tipoDocumento2));

		return optRelacion;
	}

	@Override
	public Integer buscarTotalRegistrosRelacion(LocalDateTime fechaFinalProceso, String codRecinto) {
		return relacionDao.buscarTotalRegistrosRelacion(fechaFinalProceso, codRecinto);
	}

}
