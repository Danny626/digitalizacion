package com.albo.digitalizacion.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.albo.digitalizacion.dao.ITipoErrorDAO;
import com.albo.digitalizacion.dto.CantidadTipoErrorDTO;
import com.albo.digitalizacion.model.TipoError;
import com.albo.digitalizacion.service.ITipoErrorService;

@Service
public class TipoErrorServiceImpl implements ITipoErrorService {

	@Autowired
	private ITipoErrorDAO tipoErrorDao;

	@Override
	public List<TipoError> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<TipoError> findById(String id) {
		return tipoErrorDao.findById(id);
	}

	@Override
	public TipoError saveOrUpdate(TipoError t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String deleteById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CantidadTipoErrorDTO> buscarTotalPorTipoError(LocalDateTime fechaFinalProceso, String recinto) {
		return tipoErrorDao.buscarTotalPorTipoError(fechaFinalProceso, recinto);
	}

}
