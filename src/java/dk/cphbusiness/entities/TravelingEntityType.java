/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dk.cphbusiness.entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author mhck
 */
@Entity
@Table(name = "TRAVELING_ENTITY_TYPE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TravelingEntityType.findAll", query = "SELECT t FROM TravelingEntityType t"),
    @NamedQuery(name = "TravelingEntityType.findById", query = "SELECT t FROM TravelingEntityType t WHERE t.id = :id"),
    @NamedQuery(name = "TravelingEntityType.findByDtype", query = "SELECT t FROM TravelingEntityType t WHERE t.dtype = :dtype")})
public class TravelingEntityType implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Integer id;
    @Size(max = 40)
    @Column(name = "DTYPE")
    private String dtype;
    @OneToMany(mappedBy = "dtype")
    private Collection<TravelingEntity> travelingEntityCollection;

    public TravelingEntityType() {
    }

    public TravelingEntityType(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDtype() {
        return dtype;
    }

    public void setDtype(String dtype) {
        this.dtype = dtype;
    }

    @XmlTransient
    public Collection<TravelingEntity> getTravelingEntityCollection() {
        return travelingEntityCollection;
    }

    public void setTravelingEntityCollection(Collection<TravelingEntity> travelingEntityCollection) {
        this.travelingEntityCollection = travelingEntityCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TravelingEntityType)) {
            return false;
        }
        TravelingEntityType other = (TravelingEntityType) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dk.cphbusiness.entities.TravelingEntityType[ id=" + id + " ]";
    }
    
}
