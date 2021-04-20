package com.albo.model.digitalizacion;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

@Entity
@Table(name = "archivo", schema = "PUBLIC")
public class Archivo implements Serializable {

	private static final long serialVersionUID = 1L;

//	@Id
//	@Column(name = "id", nullable = false, unique = true)
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	private Long id;

	@Id
	@Column(name = "NOM_ARCHIVO", nullable = false, unique = true, length = 100)
	private String NOM_ARCHIVO;

	@Column(name = "ORIGEN", nullable = false, length = 100)
	private String ORIGEN;

	@Column(name = "DESTIN", length = 100)
	private String DESTIN;

	@JsonSerialize(using = ToStringSerializer.class)
	@Column(name = "FECPRO")
	private LocalDateTime FECPRO;

	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "CNS_NOMARCH")
	private List<General> generales = new ArrayList<General>();

	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "NOM_ARCHIVO")
	private List<Error> errores = new ArrayList<Error>();

	public String getNOM_ARCHIVO() {
		return NOM_ARCHIVO;
	}

	public void setNOM_ARCHIVO(String nOM_ARCHIVO) {
		NOM_ARCHIVO = nOM_ARCHIVO;
	}

	public String getORIGEN() {
		return ORIGEN;
	}

	public void setORIGEN(String oRIGEN) {
		ORIGEN = oRIGEN;
	}

	public String getDESTIN() {
		return DESTIN;
	}

	public void setDESTIN(String dESTIN) {
		DESTIN = dESTIN;
	}

	public LocalDateTime getFECPRO() {
		return FECPRO;
	}

	public void setFECPRO(LocalDateTime fECPRO) {
		FECPRO = fECPRO;
	}

	public List<General> getGenerales() {
		return generales;
	}

	public void setGenerales(List<General> generales) {
		this.generales = generales;
	}

	public List<Error> getErrores() {
		return errores;
	}

	public void setErrores(List<Error> errores) {
		this.errores = errores;
	}

}
