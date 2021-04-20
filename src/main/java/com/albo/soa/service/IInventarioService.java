package com.albo.soa.service;

import com.albo.digitalizacion.service.IService;
import com.albo.soa.model.Inventario;

public interface IInventarioService extends IService<Inventario, String> {

	Inventario buscarPorParte(String invParte, String gestion);

	Inventario buscarPorNroInventario(String invNro, String gestion, String invAduana);

}
