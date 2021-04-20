package com.albo.compusoft.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

@Entity
@Table(name = "FACTURA", catalog = "", schema = "COMPUSOFT")
public class Factura implements Serializable {

	private static final long serialVersionUID = 1L;

	@EmbeddedId
	protected FacturaPK facturaPK;
	@Basic(optional = false)
	@Column(name = "RUC_COD", nullable = false, length = 20)
	private String rucCod;
	@Basic(optional = false)
	@Column(name = "RUC_COD1", nullable = false, length = 20)
	private String rucCod1;
	@Basic(optional = false)
	@Column(name = "PLAC_COD", nullable = false, length = 15)
	private String placCod;
	@Basic(optional = false)
	@Column(name = "DEST_COD", nullable = false, length = 15)
	private String destCod;
	@Basic(optional = false)
	@Column(name = "FACT_TIPO", nullable = false, length = 1)
	private String factTipo;
	@Basic(optional = false)

	@Column(name = "FACT_FECHA", nullable = false)
	@JsonSerialize(using = ToStringSerializer.class)
	private LocalDateTime factFecha;

	@Column(name = "FACT_TC", precision = 13, scale = 5)
	private BigDecimal factTc;
	@Column(name = "FACT_CLIE", length = 50)
	private String factClie;
	@Basic(optional = false)
	@Column(name = "FACT_MONTOCIF", nullable = false, precision = 13, scale = 2)
	private BigDecimal factMontocif;
	@Basic(optional = false)

	@Column(name = "FACT_FECHAIA", nullable = false)
	@JsonSerialize(using = ToStringSerializer.class)
	private LocalDateTime factFechaia;

	@Column(name = "FACT_UNIDAD")
	private Long factUnidad;
	@Basic(optional = false)
	@Column(name = "FACT_PESOTN", nullable = false, precision = 13, scale = 2)
	private BigDecimal factPesotn;
	@Basic(optional = false)
	@Column(name = "FACT_CTD", nullable = false, precision = 13, scale = 2)
	private BigDecimal factCtd;
	@Basic(optional = false)
	@Column(name = "FACT_NROPRES", nullable = false, length = 50)
	private String factNropres;
	@Column(name = "FACT_NROREG", length = 20)
	private String factNroreg;
	@Column(name = "FACT_VALRE", precision = 13, scale = 2)
	private BigDecimal factValre;
	@Column(name = "FACT_DONA", length = 2)
	private String factDona;
	@Column(name = "FACT_ESTADO", length = 3)
	private String factEstado;
	@Column(name = "TIFA_COD", length = 3)
	private String tifaCod;
	@Column(name = "RUDE_COD", length = 15)
	private String rudeCod;
	@Column(name = "ALMA_DOC_COD", length = 3)
	private String almaDocCod;
	@Column(name = "ALMA_DR", length = 15)
	private String almaDr;
	@Column(name = "ALMA_NRO", length = 15)
	private String almaNro;
	@Column(name = "FACT_ALFA1", length = 50)
	private String factAlfa1;
	@Basic(optional = false)
	@Column(name = "FACT_ALFA2", nullable = false, length = 50)
	private String factAlfa2;
	@Column(name = "FACT_NUM1", precision = 13, scale = 2)
	private BigDecimal factNum1;
	@Column(name = "FACT_NUM2", precision = 13, scale = 2)
	private BigDecimal factNum2;

	@Column(name = "FACT_FECHA1")
	@JsonSerialize(using = ToStringSerializer.class)
	private LocalDateTime factFecha1;

	@Column(name = "FACT_FECHA2")
	@JsonSerialize(using = ToStringSerializer.class)
	private LocalDateTime factFecha2;

	@Column(name = "HOJA_GESTION")
	private Long hojaGestion;
	@Column(name = "HOJA_CORR")
	private Long hojaCorr;
	@Column(name = "FACT_LOTE", length = 50)
	private String factLote;
	@Column(name = "FACT_ALFA3", length = 50)
	private String factAlfa3;
	@Column(name = "FACT_ALFA4", length = 50)
	private String factAlfa4;
	@Column(name = "FACT_ALFA5", length = 50)
	private String factAlfa5;
	@Column(name = "FACT_ALFA6", length = 50)
	private String factAlfa6;
	@Column(name = "FACT_NUM3", precision = 13, scale = 2)
	private BigDecimal factNum3;
	@Column(name = "FACT_NUM4", precision = 13, scale = 2)
	private BigDecimal factNum4;

	@Column(name = "FACT_FECHA3")
	@JsonSerialize(using = ToStringSerializer.class)
	private LocalDateTime factFecha3;

	@Column(name = "FACT_FECHA4")
	@JsonSerialize(using = ToStringSerializer.class)
	private LocalDateTime factFecha4;

	public Factura() {
	}

	public Factura(FacturaPK facturaPK) {
		this.facturaPK = facturaPK;
	}

	public Factura(FacturaPK facturaPK, String rucCod, String rucCod1, String placCod, String destCod, String factTipo,
			LocalDateTime factFecha, BigDecimal factMontocif, LocalDateTime factFechaia, BigDecimal factPesotn,
			BigDecimal factCtd, String factNropres, String factAlfa2) {
		this.facturaPK = facturaPK;
		this.rucCod = rucCod;
		this.rucCod1 = rucCod1;
		this.placCod = placCod;
		this.destCod = destCod;
		this.factTipo = factTipo;
		this.factFecha = factFecha;
		this.factMontocif = factMontocif;
		this.factFechaia = factFechaia;
		this.factPesotn = factPesotn;
		this.factCtd = factCtd;
		this.factNropres = factNropres;
		this.factAlfa2 = factAlfa2;
	}

	public Factura(String e3Cod, String e3ofSerie, String e3ofNorden, String e3ofCcontrol, String docCod,
			String factNro) {
		this.facturaPK = new FacturaPK(e3Cod, e3ofSerie, e3ofNorden, e3ofCcontrol, docCod, factNro);
	}

	public FacturaPK getFacturaPK() {
		return facturaPK;
	}

	public void setFacturaPK(FacturaPK facturaPK) {
		this.facturaPK = facturaPK;
	}

	public String getRucCod() {
		return rucCod;
	}

	public void setRucCod(String rucCod) {
		this.rucCod = rucCod;
	}

	public String getRucCod1() {
		return rucCod1;
	}

	public void setRucCod1(String rucCod1) {
		this.rucCod1 = rucCod1;
	}

	public String getPlacCod() {
		return placCod;
	}

	public void setPlacCod(String placCod) {
		this.placCod = placCod;
	}

	public String getDestCod() {
		return destCod;
	}

	public void setDestCod(String destCod) {
		this.destCod = destCod;
	}

	public String getFactTipo() {
		return factTipo;
	}

	public void setFactTipo(String factTipo) {
		this.factTipo = factTipo;
	}

	public BigDecimal getFactTc() {
		return factTc;
	}

	public void setFactTc(BigDecimal factTc) {
		this.factTc = factTc;
	}

	public String getFactClie() {
		return factClie;
	}

	public void setFactClie(String factClie) {
		this.factClie = factClie;
	}

	public BigDecimal getFactMontocif() {
		return factMontocif;
	}

	public void setFactMontocif(BigDecimal factMontocif) {
		this.factMontocif = factMontocif;
	}

	public Long getFactUnidad() {
		return factUnidad;
	}

	public void setFactUnidad(Long factUnidad) {
		this.factUnidad = factUnidad;
	}

	public BigDecimal getFactPesotn() {
		return factPesotn;
	}

	public void setFactPesotn(BigDecimal factPesotn) {
		this.factPesotn = factPesotn;
	}

	public BigDecimal getFactCtd() {
		return factCtd;
	}

	public void setFactCtd(BigDecimal factCtd) {
		this.factCtd = factCtd;
	}

	public String getFactNropres() {
		return factNropres;
	}

	public void setFactNropres(String factNropres) {
		this.factNropres = factNropres;
	}

	public String getFactNroreg() {
		return factNroreg;
	}

	public void setFactNroreg(String factNroreg) {
		this.factNroreg = factNroreg;
	}

	public BigDecimal getFactValre() {
		return factValre;
	}

	public void setFactValre(BigDecimal factValre) {
		this.factValre = factValre;
	}

	public String getFactDona() {
		return factDona;
	}

	public void setFactDona(String factDona) {
		this.factDona = factDona;
	}

	public String getFactEstado() {
		return factEstado;
	}

	public void setFactEstado(String factEstado) {
		this.factEstado = factEstado;
	}

	public String getTifaCod() {
		return tifaCod;
	}

	public void setTifaCod(String tifaCod) {
		this.tifaCod = tifaCod;
	}

	public String getRudeCod() {
		return rudeCod;
	}

	public void setRudeCod(String rudeCod) {
		this.rudeCod = rudeCod;
	}

	public String getAlmaDocCod() {
		return almaDocCod;
	}

	public void setAlmaDocCod(String almaDocCod) {
		this.almaDocCod = almaDocCod;
	}

	public String getAlmaDr() {
		return almaDr;
	}

	public void setAlmaDr(String almaDr) {
		this.almaDr = almaDr;
	}

	public String getAlmaNro() {
		return almaNro;
	}

	public void setAlmaNro(String almaNro) {
		this.almaNro = almaNro;
	}

	public String getFactAlfa1() {
		return factAlfa1;
	}

	public void setFactAlfa1(String factAlfa1) {
		this.factAlfa1 = factAlfa1;
	}

	public String getFactAlfa2() {
		return factAlfa2;
	}

	public void setFactAlfa2(String factAlfa2) {
		this.factAlfa2 = factAlfa2;
	}

	public BigDecimal getFactNum1() {
		return factNum1;
	}

	public void setFactNum1(BigDecimal factNum1) {
		this.factNum1 = factNum1;
	}

	public BigDecimal getFactNum2() {
		return factNum2;
	}

	public void setFactNum2(BigDecimal factNum2) {
		this.factNum2 = factNum2;
	}

	public Long getHojaGestion() {
		return hojaGestion;
	}

	public void setHojaGestion(Long hojaGestion) {
		this.hojaGestion = hojaGestion;
	}

	public Long getHojaCorr() {
		return hojaCorr;
	}

	public void setHojaCorr(Long hojaCorr) {
		this.hojaCorr = hojaCorr;
	}

	public String getFactLote() {
		return factLote;
	}

	public void setFactLote(String factLote) {
		this.factLote = factLote;
	}

	public String getFactAlfa3() {
		return factAlfa3;
	}

	public void setFactAlfa3(String factAlfa3) {
		this.factAlfa3 = factAlfa3;
	}

	public String getFactAlfa4() {
		return factAlfa4;
	}

	public void setFactAlfa4(String factAlfa4) {
		this.factAlfa4 = factAlfa4;
	}

	public String getFactAlfa5() {
		return factAlfa5;
	}

	public void setFactAlfa5(String factAlfa5) {
		this.factAlfa5 = factAlfa5;
	}

	public void setFactAlfa5(com.albo.soa.model.PesajePK key) {
		this.factAlfa5 = key != null ? key.getPsjGestion().toString() + key.getPsjCod().toString() : "";
	}

	public String getFactAlfa6() {
		return factAlfa6;
	}

	public void setFactAlfa6(String factAlfa6) {
		this.factAlfa6 = factAlfa6;
	}

	public BigDecimal getFactNum3() {
		return factNum3;
	}

	public void setFactNum3(BigDecimal factNum3) {
		this.factNum3 = factNum3;
	}

	public BigDecimal getFactNum4() {
		return factNum4;
	}

	public void setFactNum4(BigDecimal factNum4) {
		this.factNum4 = factNum4;
	}

	public LocalDateTime getFactFecha() {
		return factFecha;
	}

	public void setFactFecha(LocalDateTime factFecha) {
		this.factFecha = factFecha;
	}

	public LocalDateTime getFactFechaia() {
		return factFechaia;
	}

	public void setFactFechaia(LocalDateTime factFechaia) {
		this.factFechaia = factFechaia;
	}

	public LocalDateTime getFactFecha1() {
		return factFecha1;
	}

	public void setFactFecha1(LocalDateTime factFecha1) {
		this.factFecha1 = factFecha1;
	}

	public LocalDateTime getFactFecha2() {
		return factFecha2;
	}

	public void setFactFecha2(LocalDateTime factFecha2) {
		this.factFecha2 = factFecha2;
	}

	public LocalDateTime getFactFecha3() {
		return factFecha3;
	}

	public void setFactFecha3(LocalDateTime factFecha3) {
		this.factFecha3 = factFecha3;
	}

	public LocalDateTime getFactFecha4() {
		return factFecha4;
	}

	public void setFactFecha4(LocalDateTime factFecha4) {
		this.factFecha4 = factFecha4;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (facturaPK != null ? facturaPK.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof Factura)) {
			return false;
		}
		Factura other = (Factura) object;
		if ((this.facturaPK == null && other.facturaPK != null)
				|| (this.facturaPK != null && !this.facturaPK.equals(other.facturaPK))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.albo.model.compusoft.Factura[ facturaPK=" + facturaPK + " ]";
	}

}
