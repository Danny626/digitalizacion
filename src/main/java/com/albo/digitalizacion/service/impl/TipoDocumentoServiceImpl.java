package com.albo.digitalizacion.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.albo.digitalizacion.dao.ITipoDocumentoDAO;
import com.albo.digitalizacion.model.TipoDocumento;
import com.albo.digitalizacion.service.ITipoDocumentoService;

@Service
public class TipoDocumentoServiceImpl implements ITipoDocumentoService {

	@Autowired
	private ITipoDocumentoDAO tipoDocumentoDao;

	@Override
	public List<TipoDocumento> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<TipoDocumento> findById(String id) {
		return tipoDocumentoDao.findById(id);
	}

	@Override
	public TipoDocumento saveOrUpdate(TipoDocumento t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String deleteById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

}
