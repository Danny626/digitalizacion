package com.albo.model.soa;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "ROL", catalog = "", schema = "SOA")
public class RolSoa implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Basic(optional = false)
	@Column(name = "ROL_COD", nullable = false, length = 3)
	private String rolCod;
	@Column(name = "ROL_NOMBRE", length = 50)
	private String rolNombre;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "rolCod", fetch = FetchType.LAZY)
	private Set<UsuarioSoa> usuarioSet;

	public RolSoa() {
	}

	public RolSoa(String rolCod) {
		this.rolCod = rolCod;
	}

	public String getRolCod() {
		return rolCod;
	}

	public void setRolCod(String rolCod) {
		this.rolCod = rolCod;
	}

	public String getRolNombre() {
		return rolNombre;
	}

	public void setRolNombre(String rolNombre) {
		this.rolNombre = rolNombre;
	}

	public Set<UsuarioSoa> getUsuarioSet() {
		return usuarioSet;
	}

	public void setUsuarioSet(Set<UsuarioSoa> usuarioSet) {
		this.usuarioSet = usuarioSet;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (rolCod != null ? rolCod.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof RolSoa)) {
			return false;
		}
		RolSoa other = (RolSoa) object;
		if ((this.rolCod == null && other.rolCod != null)
				|| (this.rolCod != null && !this.rolCod.equals(other.rolCod))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.albo.model.soa.RolSoa[ rolCod=" + rolCod + " ]";
	}

}
