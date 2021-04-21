package com.albo.digitalizacion.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

@Entity
@Table(name = "total", schema = "PUBLIC")
public class Total implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id", nullable = false, unique = true)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "cns_cod_con", nullable = false, length = 15)
	private String cnsCodCon;

	@Column(name = "cns_tip_doc", length = 3)
	private String cnsTipDoc;

	@JsonSerialize(using = ToStringSerializer.class)
	@Column(name = "cns_fec_env")
	private LocalDateTime cnsFecEnv;

	@Column(name = "cns_estado", length = 1)
	private String cnsEstado;

	@Column(name = "cns_cantidad")
	private Integer cnsCantidad;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCnsCodCon() {
		return cnsCodCon;
	}

	public void setCnsCodCon(String cnsCodCon) {
		this.cnsCodCon = cnsCodCon;
	}

	public String getCnsTipDoc() {
		return cnsTipDoc;
	}

	public void setCnsTipDoc(String cnsTipDoc) {
		this.cnsTipDoc = cnsTipDoc;
	}

	public LocalDateTime getCnsFecEnv() {
		return cnsFecEnv;
	}

	public void setCnsFecEnv(LocalDateTime cnsFecEnv) {
		this.cnsFecEnv = cnsFecEnv;
	}

	public String getCnsEstado() {
		return cnsEstado;
	}

	public void setCnsEstado(String cnsEstado) {
		this.cnsEstado = cnsEstado;
	}

	public Integer getCnsCantidad() {
		return cnsCantidad;
	}

	public void setCnsCantidad(Integer cnsCantidad) {
		this.cnsCantidad = cnsCantidad;
	}

}
