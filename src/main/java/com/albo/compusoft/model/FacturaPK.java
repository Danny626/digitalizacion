package com.albo.compusoft.model;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class FacturaPK implements Serializable {

	private static final long serialVersionUID = 1L;

	@Basic(optional = false)
	@Column(name = "E3_COD", nullable = false, length = 20)
	private String e3Cod;
	@Basic(optional = false)
	@Column(name = "E3OF_SERIE", nullable = false, length = 20)
	private String e3ofSerie;
	@Basic(optional = false)
	@Column(name = "E3OF_NORDEN", nullable = false, length = 20)
	private String e3ofNorden;
	@Basic(optional = false)
	@Column(name = "E3OF_CCONTROL", nullable = false, length = 20)
	private String e3ofCcontrol;
	@Basic(optional = false)
	@Column(name = "DOC_COD", nullable = false, length = 3)
	private String docCod;
	@Basic(optional = false)
	@Column(name = "FACT_NRO", nullable = false, length = 15)
	private String factNro;

	public FacturaPK() {
	}

	public FacturaPK(String e3Cod, String e3ofSerie, String e3ofNorden, String e3ofCcontrol, String docCod,
			String factNro) {
		this.e3Cod = e3Cod;
		this.e3ofSerie = e3ofSerie;
		this.e3ofNorden = e3ofNorden;
		this.e3ofCcontrol = e3ofCcontrol;
		this.docCod = docCod;
		this.factNro = factNro;
	}

	public String getE3Cod() {
		return e3Cod;
	}

	public void setE3Cod(String e3Cod) {
		this.e3Cod = e3Cod;
	}

	public String getE3ofSerie() {
		return e3ofSerie;
	}

	public void setE3ofSerie(String e3ofSerie) {
		this.e3ofSerie = e3ofSerie;
	}

	public String getE3ofNorden() {
		return e3ofNorden;
	}

	public void setE3ofNorden(String e3ofNorden) {
		this.e3ofNorden = e3ofNorden;
	}

	public String getE3ofCcontrol() {
		return e3ofCcontrol;
	}

	public void setE3ofCcontrol(String e3ofCcontrol) {
		this.e3ofCcontrol = e3ofCcontrol;
	}

	public String getDocCod() {
		return docCod;
	}

	public void setDocCod(String docCod) {
		this.docCod = docCod;
	}

	public String getFactNro() {
		return factNro;
	}

	public void setFactNro(String factNro) {
		this.factNro = factNro;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (e3Cod != null ? e3Cod.hashCode() : 0);
		hash += (e3ofSerie != null ? e3ofSerie.hashCode() : 0);
		hash += (e3ofNorden != null ? e3ofNorden.hashCode() : 0);
		hash += (e3ofCcontrol != null ? e3ofCcontrol.hashCode() : 0);
		hash += (docCod != null ? docCod.hashCode() : 0);
		hash += (factNro != null ? factNro.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof FacturaPK)) {
			return false;
		}
		FacturaPK other = (FacturaPK) object;
		if ((this.e3Cod == null && other.e3Cod != null) || (this.e3Cod != null && !this.e3Cod.equals(other.e3Cod))) {
			return false;
		}
		if ((this.e3ofSerie == null && other.e3ofSerie != null)
				|| (this.e3ofSerie != null && !this.e3ofSerie.equals(other.e3ofSerie))) {
			return false;
		}
		if ((this.e3ofNorden == null && other.e3ofNorden != null)
				|| (this.e3ofNorden != null && !this.e3ofNorden.equals(other.e3ofNorden))) {
			return false;
		}
		if ((this.e3ofCcontrol == null && other.e3ofCcontrol != null)
				|| (this.e3ofCcontrol != null && !this.e3ofCcontrol.equals(other.e3ofCcontrol))) {
			return false;
		}
		if ((this.docCod == null && other.docCod != null)
				|| (this.docCod != null && !this.docCod.equals(other.docCod))) {
			return false;
		}
		if ((this.factNro == null && other.factNro != null)
				|| (this.factNro != null && !this.factNro.equals(other.factNro))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.albo.model.compusoft.FacturaPK[ e3Cod=" + e3Cod + ", e3ofSerie=" + e3ofSerie + ", e3ofNorden="
				+ e3ofNorden + ", e3ofCcontrol=" + e3ofCcontrol + ", docCod=" + docCod + ", factNro=" + factNro + " ]";
	}

}
