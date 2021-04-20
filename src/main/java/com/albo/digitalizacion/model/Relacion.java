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
@Table(name = "relacion", schema = "PUBLIC")
public class Relacion implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id", nullable = false, unique = true)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "CNS_TIPODOC1", nullable = false, length = 3)
	private String CNS_TIPODOC1;

	@Column(name = "CNS_ADUTRA1", length = 3)
	private String CNS_ADUTRA1;

	@Column(name = "CNS_NROTRA1", length = 50)
	private String CNS_NROTRA1;

	@Column(name = "CNS_EMISOR1", length = 35)
	private String CNS_EMISOR1;

	@JsonSerialize(using = ToStringSerializer.class)
	@Column(name = "CNS_FECHAEMI1")
	private LocalDateTime CNS_FECHAEMI1;

	@Column(name = "CNS_TIPODOC2", length = 3)
	private String CNS_TIPODOC2;

	@Column(name = "CNS_ADUTRA2", length = 3)
	private String CNS_ADUTRA2;

	@Column(name = "CNS_NROTRA2", length = 50)
	private String CNS_NROTRA2;

	@Column(name = "CNS_EMISOR2", length = 35)
	private String CNS_EMISOR2;

	@JsonSerialize(using = ToStringSerializer.class)
	@Column(name = "CNS_FECHAEMI2")
	private LocalDateTime CNS_FECHAEMI2;

	@Column(name = "CNS_ESTADO", length = 1)
	private String CNS_ESTADO;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCNS_TIPODOC1() {
		return CNS_TIPODOC1;
	}

	public void setCNS_TIPODOC1(String cNS_TIPODOC1) {
		CNS_TIPODOC1 = cNS_TIPODOC1;
	}

	public String getCNS_ADUTRA1() {
		return CNS_ADUTRA1;
	}

	public void setCNS_ADUTRA1(String cNS_ADUTRA1) {
		CNS_ADUTRA1 = cNS_ADUTRA1;
	}

	public String getCNS_NROTRA1() {
		return CNS_NROTRA1;
	}

	public void setCNS_NROTRA1(String cNS_NROTRA1) {
		CNS_NROTRA1 = cNS_NROTRA1;
	}

	public String getCNS_EMISOR1() {
		return CNS_EMISOR1;
	}

	public void setCNS_EMISOR1(String cNS_EMISOR1) {
		CNS_EMISOR1 = cNS_EMISOR1;
	}

	public LocalDateTime getCNS_FECHAEMI1() {
		return CNS_FECHAEMI1;
	}

	public void setCNS_FECHAEMI1(LocalDateTime cNS_FECHAEMI1) {
		CNS_FECHAEMI1 = cNS_FECHAEMI1;
	}

	public String getCNS_TIPODOC2() {
		return CNS_TIPODOC2;
	}

	public void setCNS_TIPODOC2(String cNS_TIPODOC2) {
		CNS_TIPODOC2 = cNS_TIPODOC2;
	}

	public String getCNS_ADUTRA2() {
		return CNS_ADUTRA2;
	}

	public void setCNS_ADUTRA2(String cNS_ADUTRA2) {
		CNS_ADUTRA2 = cNS_ADUTRA2;
	}

	public String getCNS_NROTRA2() {
		return CNS_NROTRA2;
	}

	public void setCNS_NROTRA2(String cNS_NROTRA2) {
		CNS_NROTRA2 = cNS_NROTRA2;
	}

	public String getCNS_EMISOR2() {
		return CNS_EMISOR2;
	}

	public void setCNS_EMISOR2(String cNS_EMISOR2) {
		CNS_EMISOR2 = cNS_EMISOR2;
	}

	public LocalDateTime getCNS_FECHAEMI2() {
		return CNS_FECHAEMI2;
	}

	public void setCNS_FECHAEMI2(LocalDateTime cNS_FECHAEMI2) {
		CNS_FECHAEMI2 = cNS_FECHAEMI2;
	}

	public String getCNS_ESTADO() {
		return CNS_ESTADO;
	}

	public void setCNS_ESTADO(String cNS_ESTADO) {
		CNS_ESTADO = cNS_ESTADO;
	}

}
