package com.albo.soa.service;

import java.time.LocalDateTime;

import com.albo.digitalizacion.service.IService;
import com.albo.soa.model.Inventario;

public interface IInventarioService extends IService<Inventario, String> {

	Inventario buscarPorParte(String invParte, String gestion);

	Inventario buscarPorNroInventario(String invNro, String invRecinto, LocalDateTime fechaProcesoInicio,
			LocalDateTime fechaProcesoFin);

	Inventario buscarPorNroInvPrueba(String invNro);

}
