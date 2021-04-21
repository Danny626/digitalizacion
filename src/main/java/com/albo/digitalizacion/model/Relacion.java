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

	@Column(name = "cns_tipo_doc1", nullable = false, length = 3)
	private String cnsTipoDoc1;

	@Column(name = "cns_adu_tra1", length = 3)
	private String cnsAduTra1;

	@Column(name = "cns_nro_tra1", length = 50)
	private String cnsNroTra1;

	@Column(name = "cns_emisor1", length = 35)
	private String cnsEmisor1;

	@JsonSerialize(using = ToStringSerializer.class)
	@Column(name = "cns_fecha_emi1")
	private LocalDateTime cnsFechaEmi1;

	@Column(name = "cns_tipo_doc2", length = 3)
	private String cnsTipoDoc2;

	@Column(name = "cns_adu_tra2", length = 3)
	private String cnsAduTra2;

	@Column(name = "cns_nro_tra2", length = 50)
	private String cnsNroTra2;

	@Column(name = "cns_emisor2", length = 35)
	private String cnsEmisor2;

	@JsonSerialize(using = ToStringSerializer.class)
	@Column(name = "cns_fecha_emi2")
	private LocalDateTime cnsFechaEmi2;

	@Column(name = "cns_estado", length = 1)
	private String cnsEstado;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCnsTipoDoc1() {
		return cnsTipoDoc1;
	}

	public void setCnsTipoDoc1(String cnsTipoDoc1) {
		this.cnsTipoDoc1 = cnsTipoDoc1;
	}

	public String getCnsAduTra1() {
		return cnsAduTra1;
	}

	public void setCnsAduTra1(String cnsAduTra1) {
		this.cnsAduTra1 = cnsAduTra1;
	}

	public String getCnsNroTra1() {
		return cnsNroTra1;
	}

	public void setCnsNroTra1(String cnsNroTra1) {
		this.cnsNroTra1 = cnsNroTra1;
	}

	public String getCnsEmisor1() {
		return cnsEmisor1;
	}

	public void setCnsEmisor1(String cnsEmisor1) {
		this.cnsEmisor1 = cnsEmisor1;
	}

	public LocalDateTime getCnsFechaEmi1() {
		return cnsFechaEmi1;
	}

	public void setCnsFechaEmi1(LocalDateTime cnsFechaEmi1) {
		this.cnsFechaEmi1 = cnsFechaEmi1;
	}

	public String getCnsTipoDoc2() {
		return cnsTipoDoc2;
	}

	public void setCnsTipoDoc2(String cnsTipoDoc2) {
		this.cnsTipoDoc2 = cnsTipoDoc2;
	}

	public String getCnsAduTra2() {
		return cnsAduTra2;
	}

	public void setCnsAduTra2(String cnsAduTra2) {
		this.cnsAduTra2 = cnsAduTra2;
	}

	public String getCnsNroTra2() {
		return cnsNroTra2;
	}

	public void setCnsNroTra2(String cnsNroTra2) {
		this.cnsNroTra2 = cnsNroTra2;
	}

	public String getCnsEmisor2() {
		return cnsEmisor2;
	}

	public void setCnsEmisor2(String cnsEmisor2) {
		this.cnsEmisor2 = cnsEmisor2;
	}

	public LocalDateTime getCnsFechaEmi2() {
		return cnsFechaEmi2;
	}

	public void setCnsFechaEmi2(LocalDateTime cnsFechaEmi2) {
		this.cnsFechaEmi2 = cnsFechaEmi2;
	}

	public String getCnsEstado() {
		return cnsEstado;
	}

	public void setCnsEstado(String cnsEstado) {
		this.cnsEstado = cnsEstado;
	}

}
