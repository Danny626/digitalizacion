package com.albo.soa.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.albo.soa.dao.IDocArchivoDAO;
import com.albo.soa.model.DocArchivo;
import com.albo.soa.model.DocArchivoPK;
import com.albo.soa.service.IDocArchivoService;

@Service
public class DocArchivoServiceImpl implements IDocArchivoService {

	@Autowired
	private IDocArchivoDAO docArchivoDao;

	@Override
	public List<DocArchivo> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<DocArchivo> findById(DocArchivoPK id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DocArchivo saveOrUpdate(DocArchivo t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String deleteById(DocArchivoPK id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DocArchivo buscarPorNroSalida(String nroSalida, String codRecinto, Integer gestion) {
		return docArchivoDao.buscarPorNroSalida(nroSalida, codRecinto, gestion);
	}

}
