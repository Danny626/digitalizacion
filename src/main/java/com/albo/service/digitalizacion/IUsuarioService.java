package com.albo.service.digitalizacion;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.albo.model.digitalizacion.Usuario;

public interface IUsuarioService extends IService<Usuario, Long> {

	Page<Usuario> listarPageable(Pageable pageable);

	Usuario listarPorUsername(String username);

	Page<Usuario> buscarXUsername(Pageable pageable, String username);

//	byte[] leerArchivo(String pathFoto);
}
