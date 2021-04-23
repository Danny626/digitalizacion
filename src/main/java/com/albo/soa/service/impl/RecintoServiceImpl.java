package com.albo.soa.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.albo.soa.dao.IRecintoDAO;
import com.albo.soa.model.Recinto;
import com.albo.soa.service.IRecintoService;

@Service
public class RecintoServiceImpl implements IRecintoService {

	@Autowired
	private IRecintoDAO recintoDao;

	@Override
	public List<Recinto> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Recinto> findById(String id) {
		return recintoDao.findById(id);
	}

	@Override
	public Recinto saveOrUpdate(Recinto t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String deleteById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

}
