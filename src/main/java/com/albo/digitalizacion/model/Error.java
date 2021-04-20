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
@Table(name = "error", schema = "PUBLIC")
public class Error implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id", nullable = false, unique = true)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "E3_COD", nullable = false, length = 5)
	private String E3_COD;

	@Column(name = "TIPO_OP", length = 2)
	private String TIPO_OP;

	@Column(name = "TIPO_ARCH", length = 3)
	private String TIPO_ARCH;

	@ManyToOne
	@JoinColumn(name = "TIPO_ERROR", nullable = false, referencedColumnName = "COD_ERROR")
	private TipoError TIPO_ERROR;

	@ManyToOne
	@JoinColumn(name = "NOM_ARCHIVO", nullable = false, referencedColumnName = "NOM_ARCHIVO")
	private Archivo NOM_ARCHIVO;

	@JsonSerialize(using = ToStringSerializer.class)
	@Column(name = "FECPRO")
	private LocalDateTime FECPRO;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getE3_COD() {
		return E3_COD;
	}

	public void setE3_COD(String e3_COD) {
		E3_COD = e3_COD;
	}

	public String getTIPO_OP() {
		return TIPO_OP;
	}

	public void setTIPO_OP(String tIPO_OP) {
		TIPO_OP = tIPO_OP;
	}

	public String getTIPO_ARCH() {
		return TIPO_ARCH;
	}

	public void setTIPO_ARCH(String tIPO_ARCH) {
		TIPO_ARCH = tIPO_ARCH;
	}

	public LocalDateTime getFECPRO() {
		return FECPRO;
	}

	public void setFECPRO(LocalDateTime fECPRO) {
		FECPRO = fECPRO;
	}

	public TipoError getTIPO_ERROR() {
		return TIPO_ERROR;
	}

	public void setTIPO_ERROR(TipoError tIPO_ERROR) {
		TIPO_ERROR = tIPO_ERROR;
	}

	public Archivo getNOM_ARCHIVO() {
		return NOM_ARCHIVO;
	}

	public void setNOM_ARCHIVO(Archivo nOM_ARCHIVO) {
		NOM_ARCHIVO = nOM_ARCHIVO;
	}

}
