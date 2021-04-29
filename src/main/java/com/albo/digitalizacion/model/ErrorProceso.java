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
@Table(name = "error", schema = "public")
public class ErrorProceso implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id", nullable = false, unique = true)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "e3_cod", nullable = false, length = 5)
	private String e3Cod;

	@ManyToOne
	@JoinColumn(name = "tipo_documento", nullable = false, referencedColumnName = "codigo")
	private TipoDocumento tipoDocumento;

	@ManyToOne
	@JoinColumn(name = "tipo_error", nullable = false, referencedColumnName = "cod_error")
	private TipoError tipoError;

	@ManyToOne
	@JoinColumn(name = "archivo", nullable = false, referencedColumnName = "id")
	private Archivo archivo;

	@JsonSerialize(using = ToStringSerializer.class)
	@Column(name = "fec_pro")
	private LocalDateTime fecPro;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getE3Cod() {
		return e3Cod;
	}

	public void setE3Cod(String e3Cod) {
		this.e3Cod = e3Cod;
	}

	public TipoError getTipoError() {
		return tipoError;
	}

	public void setTipoError(TipoError tipoError) {
		this.tipoError = tipoError;
	}

	public LocalDateTime getFecPro() {
		return fecPro;
	}

	public void setFecPro(LocalDateTime fecPro) {
		this.fecPro = fecPro;
	}

	public Archivo getArchivo() {
		return archivo;
	}

	public void setArchivo(Archivo archivo) {
		this.archivo = archivo;
	}

	public TipoDocumento getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(TipoDocumento tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

}
