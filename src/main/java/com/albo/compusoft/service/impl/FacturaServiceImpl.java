package com.albo.compusoft.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.albo.compusoft.dao.IFacturaDAO;
import com.albo.compusoft.model.Factura;
import com.albo.compusoft.model.FacturaPK;
import com.albo.compusoft.service.IFacturaService;

@Service
public class FacturaServiceImpl implements IFacturaService {

	@Autowired
	private IFacturaDAO facturaDao;

	@Override
	public List<Factura> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Factura> findById(FacturaPK id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Factura saveOrUpdate(Factura t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String deleteById(FacturaPK id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Factura buscarPorNroReg(String e3Cod, String factNroreg, LocalDateTime fechaInicioProceso,
			LocalDateTime fechaFinProceso) {
		return facturaDao.buscarPorNroReg(e3Cod, factNroreg, fechaInicioProceso, fechaFinProceso);
	}

}
