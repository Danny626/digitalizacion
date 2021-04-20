package com.albo.model.digitalizacion;

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
@Table(name = "general", schema = "PUBLIC")
public class General implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id", nullable = false, unique = true)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "CNS_CODCONC", nullable = false, length = 15)
	private String CNS_CODCONC;

	@Column(name = "CNS_TIPODOC", length = 3)
	private String CNS_TIPODOC;

	@Column(name = "CNS_EMISOR", length = 35)
	private String CNS_EMISOR;

	@ManyToOne
	@JoinColumn(name = "CNS_NOMARCH", nullable = false, referencedColumnName = "NOM_ARCHIVO")
	private Archivo CNS_NOMARCH;

	@Column(name = "CNS_ADUTRA", length = 3)
	private String CNS_ADUTRA;

	@Column(name = "CNS_NROTRA", length = 50)
	private String CNS_NROTRA;

	@JsonSerialize(using = ToStringSerializer.class)
	@Column(name = "CNS_FECHA_EMI")
	private LocalDateTime CNS_FECHA_EMI;

	@JsonSerialize(using = ToStringSerializer.class)
	@Column(name = "CNS_FECHA_PRO")
	private LocalDateTime CNS_FECHA_PRO;

	@Column(name = "CNS_ESTADO", length = 1)
	private String CNS_ESTADO;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCNS_CODCONC() {
		return CNS_CODCONC;
	}

	public void setCNS_CODCONC(String cNS_CODCONC) {
		CNS_CODCONC = cNS_CODCONC;
	}

	public String getCNS_TIPODOC() {
		return CNS_TIPODOC;
	}

	public void setCNS_TIPODOC(String cNS_TIPODOC) {
		CNS_TIPODOC = cNS_TIPODOC;
	}

	public String getCNS_EMISOR() {
		return CNS_EMISOR;
	}

	public void setCNS_EMISOR(String cNS_EMISOR) {
		CNS_EMISOR = cNS_EMISOR;
	}

	public Archivo getCNS_NOMARCH() {
		return CNS_NOMARCH;
	}

	public void setCNS_NOMARCH(Archivo cNS_NOMARCH) {
		CNS_NOMARCH = cNS_NOMARCH;
	}

	public String getCNS_ADUTRA() {
		return CNS_ADUTRA;
	}

	public void setCNS_ADUTRA(String cNS_ADUTRA) {
		CNS_ADUTRA = cNS_ADUTRA;
	}

	public String getCNS_NROTRA() {
		return CNS_NROTRA;
	}

	public void setCNS_NROTRA(String cNS_NROTRA) {
		CNS_NROTRA = cNS_NROTRA;
	}

	public String getCNS_ESTADO() {
		return CNS_ESTADO;
	}

	public void setCNS_ESTADO(String cNS_ESTADO) {
		CNS_ESTADO = cNS_ESTADO;
	}

	public LocalDateTime getCNS_FECHA_EMI() {
		return CNS_FECHA_EMI;
	}

	public void setCNS_FECHA_EMI(LocalDateTime cNS_FECHA_EMI) {
		CNS_FECHA_EMI = cNS_FECHA_EMI;
	}

	public LocalDateTime getCNS_FECHA_PRO() {
		return CNS_FECHA_PRO;
	}

	public void setCNS_FECHA_PRO(LocalDateTime cNS_FECHA_PRO) {
		CNS_FECHA_PRO = cNS_FECHA_PRO;
	}

}
