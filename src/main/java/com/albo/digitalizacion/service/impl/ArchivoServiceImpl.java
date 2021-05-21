package com.albo.digitalizacion.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.albo.digitalizacion.dao.IArchivoDAO;
import com.albo.digitalizacion.model.Archivo;
import com.albo.digitalizacion.service.IArchivoService;

@Service
public class ArchivoServiceImpl implements IArchivoService {

	@Autowired
	private IArchivoDAO archivoDao;

	@Override
	public List<Archivo> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Archivo> findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Archivo saveOrUpdate(Archivo t) {
		return archivoDao.save(t);
	}

	@Override
	public String deleteById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer buscarTotalRegistrosPorRecinto(String recinto, LocalDateTime fechaFinalProceso) {
		return archivoDao.buscarTotalRegistrosPorRecinto(recinto, fechaFinalProceso);
	}

}
