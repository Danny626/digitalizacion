package com.albo.model.digitalizacion;

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

	@Column(name = "CNS_CODCON", nullable = false, length = 15)
	private String CNS_CODCON;

	@Column(name = "CNS_TIPDOC", length = 3)
	private String CNS_TIPDOC;

	@JsonSerialize(using = ToStringSerializer.class)
	@Column(name = "CNS_FECENV")
	private LocalDateTime CNS_FECENV;

	@Column(name = "CNS_ESTADO", length = 1)
	private String CNS_ESTADO;

	@Column(name = "CNS_CANTIDAD")
	private Integer CNS_CANTIDAD;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCNS_CODCON() {
		return CNS_CODCON;
	}

	public void setCNS_CODCON(String cNS_CODCON) {
		CNS_CODCON = cNS_CODCON;
	}

	public String getCNS_TIPDOC() {
		return CNS_TIPDOC;
	}

	public void setCNS_TIPDOC(String cNS_TIPDOC) {
		CNS_TIPDOC = cNS_TIPDOC;
	}

	public LocalDateTime getCNS_FECENV() {
		return CNS_FECENV;
	}

	public void setCNS_FECENV(LocalDateTime cNS_FECENV) {
		CNS_FECENV = cNS_FECENV;
	}

	public String getCNS_ESTADO() {
		return CNS_ESTADO;
	}

	public void setCNS_ESTADO(String cNS_ESTADO) {
		CNS_ESTADO = cNS_ESTADO;
	}

	public Integer getCNS_CANTIDAD() {
		return CNS_CANTIDAD;
	}

	public void setCNS_CANTIDAD(Integer cNS_CANTIDAD) {
		CNS_CANTIDAD = cNS_CANTIDAD;
	}

}
