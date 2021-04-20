package com.albo.service.soa;

import com.albo.model.soa.Inventario;
import com.albo.service.digitalizacion.IService;

public interface IInventarioService extends IService<Inventario, String> {

	Inventario buscarPorParte(String invParte, String gestion);

}
