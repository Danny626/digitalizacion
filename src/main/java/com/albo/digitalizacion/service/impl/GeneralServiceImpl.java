package com.albo.digitalizacion.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.albo.digitalizacion.dao.IGeneralDAO;
import com.albo.digitalizacion.model.General;
import com.albo.digitalizacion.model.TipoDocumento;
import com.albo.digitalizacion.service.IGeneralService;

@Service
public class GeneralServiceImpl implements IGeneralService {

	@Autowired
	private IGeneralDAO generalDao;

	@Override
	public List<General> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<General> findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public General saveOrUpdate(General t) {
		return generalDao.save(t);
	}

	@Override
	public String deleteById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<General> buscarExistente(String cnsCodConc, String cnsEmisor, TipoDocumento tipoDocumento,
			String nombreArchivoDestino, String cnsAduTra, String cnsNroTra, String recinto) {

		Optional<General> optGeneral = Optional.ofNullable(generalDao.buscarExistente(cnsCodConc, cnsEmisor,
				tipoDocumento, nombreArchivoDestino, cnsAduTra, cnsNroTra, recinto));

		return optGeneral;
	}

	@Override
	public Integer buscarTotalRegistrosGeneral(LocalDateTime fechaFinalProceso, String recinto) {
		return generalDao.buscarTotalRegistrosGeneral(fechaFinalProceso, recinto);
	}

	@Override
	public List<General> listarxFecha(LocalDateTime fechaFinalProceso) {
		return generalDao.listarxFecha(fechaFinalProceso);
	}

}
