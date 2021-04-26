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
@Table(name = "general", schema = "PUBLIC")
public class General implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id", nullable = false, unique = true)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "cns_cod_conc", nullable = false, length = 15)
	private String cnsCodConc;

	@Column(name = "cns_emisor", length = 35)
	private String cnsEmisor;

	@ManyToOne
	@JoinColumn(name = "tipo_documento", nullable = false, referencedColumnName = "codigo")
	private TipoDocumento tipoDocumento;

	@ManyToOne
	@JoinColumn(name = "cns_arch", nullable = false, referencedColumnName = "id")
	private Archivo archivo;

	@Column(name = "cns_adu_tra", length = 3)
	private String cnsAduTra;

	@Column(name = "cns_nro_tra", length = 50)
	private String cnsNroTra;

	@JsonSerialize(using = ToStringSerializer.class)
	@Column(name = "cns_fecha_emi")
	private LocalDateTime cnsFechaEmi;

	@JsonSerialize(using = ToStringSerializer.class)
	@Column(name = "cns_fecha_pro")
	private LocalDateTime cnsFechaPro;

	@Column(name = "cns_estado", length = 1)
	private String cnsEstado;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCnsCodConc() {
		return cnsCodConc;
	}

	public void setCnsCodConc(String cnsCodConc) {
		this.cnsCodConc = cnsCodConc;
	}

	public String getCnsEmisor() {
		return cnsEmisor;
	}

	public void setCnsEmisor(String cnsEmisor) {
		this.cnsEmisor = cnsEmisor;
	}

	public Archivo getArchivo() {
		return archivo;
	}

	public void setArchivo(Archivo archivo) {
		this.archivo = archivo;
	}

	public String getCnsAduTra() {
		return cnsAduTra;
	}

	public void setCnsAduTra(String cnsAduTra) {
		this.cnsAduTra = cnsAduTra;
	}

	public String getCnsNroTra() {
		return cnsNroTra;
	}

	public void setCnsNroTra(String cnsNroTra) {
		this.cnsNroTra = cnsNroTra;
	}

	public LocalDateTime getCnsFechaEmi() {
		return cnsFechaEmi;
	}

	public void setCnsFechaEmi(LocalDateTime cnsFechaEmi) {
		this.cnsFechaEmi = cnsFechaEmi;
	}

	public LocalDateTime getCnsFechaPro() {
		return cnsFechaPro;
	}

	public void setCnsFechaPro(LocalDateTime cnsFechaPro) {
		this.cnsFechaPro = cnsFechaPro;
	}

	public String getCnsEstado() {
		return cnsEstado;
	}

	public void setCnsEstado(String cnsEstado) {
		this.cnsEstado = cnsEstado;
	}

	public TipoDocumento getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(TipoDocumento tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}
}
