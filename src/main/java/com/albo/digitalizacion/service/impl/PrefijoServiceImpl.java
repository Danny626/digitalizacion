package com.albo.digitalizacion.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.albo.digitalizacion.dao.IPrefijoDAO;
import com.albo.digitalizacion.model.Prefijo;
import com.albo.digitalizacion.service.IPrefijoService;

@Service
public class PrefijoServiceImpl implements IPrefijoService {

	@Autowired
	private IPrefijoDAO prefijoDao;

	@Override
	public List<Prefijo> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Prefijo> findById(String id) {
		return prefijoDao.findById(id);
	}

	@Override
	public Prefijo saveOrUpdate(Prefijo t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String deleteById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

}
