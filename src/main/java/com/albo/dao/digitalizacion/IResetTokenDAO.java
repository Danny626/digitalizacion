package com.albo.dao.digitalizacion;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.albo.model.digitalizacion.ResetToken;

@Repository
public interface IResetTokenDAO extends JpaRepository<ResetToken, Long> {

	// from token where token = :?
	ResetToken findByToken(String token);

}
