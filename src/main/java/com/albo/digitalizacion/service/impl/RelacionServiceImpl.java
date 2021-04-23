package com.albo.digitalizacion.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.albo.digitalizacion.dao.IRelacionDAO;
import com.albo.digitalizacion.model.Relacion;
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

}
