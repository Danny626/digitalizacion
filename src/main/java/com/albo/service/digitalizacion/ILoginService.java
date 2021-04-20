package com.albo.service.digitalizacion;

import com.albo.model.digitalizacion.Usuario;

public interface ILoginService {

	Usuario verificarNombreUsuario(String usuario) throws Exception;

	int cambiarClave(String clave, String nombre) throws Exception;

}
