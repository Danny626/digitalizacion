package com.albo.compusoft.service;

import java.time.LocalDateTime;
import java.util.List;

import com.albo.compusoft.model.Factura;
import com.albo.compusoft.model.FacturaPK;
import com.albo.digitalizacion.service.IService;

public interface IFacturaService extends IService<Factura, FacturaPK> {

	List<Factura> buscarPorNroReg(String e3Cod, String factNroreg, LocalDateTime fechaInicioProceso,
			LocalDateTime fechaFinProceso);

}
