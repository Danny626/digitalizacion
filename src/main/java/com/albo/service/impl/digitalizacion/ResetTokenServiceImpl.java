package com.albo.service.impl.digitalizacion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.albo.dao.digitalizacion.IResetTokenDAO;
import com.albo.model.digitalizacion.ResetToken;
import com.albo.service.digitalizacion.IResetTokenService;

@Service
public class ResetTokenServiceImpl implements IResetTokenService {

	@Autowired
	private IResetTokenDAO dao;

	@Override
	public ResetToken findByToken(String token) {
		return dao.findByToken(token);
	}

	@Override
	public void guardar(ResetToken token) {
		dao.save(token);

	}

	@Override
	public void eliminar(ResetToken token) {
		dao.delete(token);
	}

}
