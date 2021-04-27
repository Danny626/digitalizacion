package com.albo.digitalizacion.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.albo.digitalizacion.dao.ITotalDAO;
import com.albo.digitalizacion.dto.TipoDocContGeneralDTO;
import com.albo.digitalizacion.model.Total;
import com.albo.digitalizacion.service.ITotalService;

@Service
public class TotalServiceImpl implements ITotalService {

	@Autowired
	private ITotalDAO totalDao;

	@Override
	public List<Total> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Total> findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Total saveOrUpdate(Total t) {
		return totalDao.save(t);
	}

	@Override
	public String deleteById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TipoDocContGeneralDTO> buscarDistintos(LocalDateTime fechaProceso) {
		return totalDao.buscarDistintos(fechaProceso);
	}

}
