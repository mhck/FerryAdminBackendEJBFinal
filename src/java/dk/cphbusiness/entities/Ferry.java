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
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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
@Table(name = "FERRY")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Ferry.findAll", query = "SELECT f FROM Ferry f"),
    @NamedQuery(name = "Ferry.findById", query = "SELECT f FROM Ferry f WHERE f.id = :id"),
    @NamedQuery(name = "Ferry.findByName", query = "SELECT f FROM Ferry f WHERE f.name = :name")})
public class Ferry implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Integer id;
    @Size(max = 40)
    @Column(name = "NAME")
    private String name;
    @JoinTable(name = "FERRY_HAS_HARBOUR", joinColumns = {
        @JoinColumn(name = "FERRY_ID", referencedColumnName = "ID")}, inverseJoinColumns = {
        @JoinColumn(name = "HARBOUR_ID", referencedColumnName = "ID")})
    @ManyToMany
    private Collection<Harbour> harbourCollection;
    @OneToMany(mappedBy = "ferryId")
    private Collection<FerryConfig> ferryConfigCollection;

    public Ferry() {
    }

    public Ferry(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlTransient
    public Collection<Harbour> getHarbourCollection() {
        return harbourCollection;
    }

    public void setHarbourCollection(Collection<Harbour> harbourCollection) {
        this.harbourCollection = harbourCollection;
    }

    @XmlTransient
    public Collection<FerryConfig> getFerryConfigCollection() {
        return ferryConfigCollection;
    }

    public void setFerryConfigCollection(Collection<FerryConfig> ferryConfigCollection) {
        this.ferryConfigCollection = ferryConfigCollection;
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
        if (!(object instanceof Ferry)) {
            return false;
        }
        Ferry other = (Ferry) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dk.cphbusiness.entities.Ferry[ id=" + id + " ]";
    }
    
}
