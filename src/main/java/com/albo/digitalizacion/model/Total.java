package com.albo.digitalizacion.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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

	@ManyToOne
	@JoinColumn(name = "tipo_documento", nullable = false, referencedColumnName = "codigo")
	private TipoDocumento tipoDocumento;

	@JsonSerialize(using = ToStringSerializer.class)
	@Column(name = "cns_fec_env")
	private LocalDateTime cnsFecEnv;

	@Column(name = "cns_estado", length = 1)
	private String cnsEstado;

	@Column(name = "cns_cantidad")
	private Long cnsCantidad;

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

	public Long getCnsCantidad() {
		return cnsCantidad;
	}

	public void setCnsCantidad(Long cnsCantidad) {
		this.cnsCantidad = cnsCantidad;
	}

	public TipoDocumento getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(TipoDocumento tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

}
