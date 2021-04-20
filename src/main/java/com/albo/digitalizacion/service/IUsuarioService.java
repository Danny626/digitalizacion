package com.albo.digitalizacion.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.albo.digitalizacion.model.Usuario;

public interface IUsuarioService extends IService<Usuario, Long> {

	Page<Usuario> listarPageable(Pageable pageable);

	Usuario listarPorUsername(String username);

	Page<Usuario> buscarXUsername(Pageable pageable, String username);

//	byte[] leerArchivo(String pathFoto);
}
