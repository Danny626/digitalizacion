package com.albo.digitalizacion.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.albo.digitalizacion.dao.IErrorProcesoDAO;
import com.albo.digitalizacion.model.ErrorProceso;
import com.albo.digitalizacion.service.IErrorProcesoService;

@Service
public class ErrorProcesoServiceImpl implements IErrorProcesoService {

	@Autowired
	private IErrorProcesoDAO errorDao;

	@Override
	public List<ErrorProceso> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<ErrorProceso> findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ErrorProceso saveOrUpdate(ErrorProceso t) {
		return errorDao.save(t);
	}

	@Override
	public String deleteById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer buscarTotalRegistrosError(LocalDateTime fechaFinalProceso, String recinto) {
		return errorDao.buscarTotalRegistrosError(fechaFinalProceso, recinto);
	}

}
