package com.albo.service.digitalizacion;

import com.albo.model.digitalizacion.ResetToken;

public interface IResetTokenService {

	ResetToken findByToken(String token);

	void guardar(ResetToken token);

	void eliminar(ResetToken token);

}
