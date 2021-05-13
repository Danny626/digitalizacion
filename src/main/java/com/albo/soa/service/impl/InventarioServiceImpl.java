package com.albo.soa.service.impl;

import java.time.LocalDateTime;
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
	public List<Inventario> buscarPorNroInventarioNoConsolidado(String invNro, String invRecinto,
			LocalDateTime fechaProcesoInicio, LocalDateTime fechaProcesoFin) {
		return inventarioDao.buscarPorNroInventarioNoConsolidado(invNro, invRecinto, fechaProcesoInicio,
				fechaProcesoFin);
	}

	@Override
	public List<Inventario> buscarPorNroInventarioConsolidado(String invNro, String invRecinto,
			LocalDateTime fechaProcesoInicio, LocalDateTime fechaProcesoFin, String consolidado) {
		return inventarioDao.buscarPorNroInventarioConsolidado(invNro, invRecinto, fechaProcesoInicio, fechaProcesoFin,
				consolidado);
	}

}
