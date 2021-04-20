package com.albo.soa.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.albo.soa.dao.IInventarioDAO;
import com.albo.soa.model.Inventario;
import com.albo.soa.service.IInventarioService;

@Service
public class InventarioServiceImpl implements IInventarioService {

	@Autowired
	private IInventarioDAO inventarioDao;

	@Override
	public List<Inventario> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Inventario> findById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Inventario saveOrUpdate(Inventario t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String deleteById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Inventario buscarPorParte(String invParte, String gestion) {
		return inventarioDao.buscarPorParte(invParte, gestion);
	}

	@Override
	public Inventario buscarPorNroInventario(String invNro, String gestion, String invAduana) {
		return inventarioDao.buscarPorNroInventario(invNro, gestion, invAduana);
	}

}
