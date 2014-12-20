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
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author mhck
 */
@Entity
@Table(name = "FERRY_CONFIG")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "FerryConfig.findAll", query = "SELECT f FROM FerryConfig f"),
    @NamedQuery(name = "FerryConfig.findById", query = "SELECT f FROM FerryConfig f WHERE f.id = :id"),
    @NamedQuery(name = "FerryConfig.findByPeopleCapacity", query = "SELECT f FROM FerryConfig f WHERE f.peopleCapacity = :peopleCapacity"),
    @NamedQuery(name = "FerryConfig.findByVehicleCapacity", query = "SELECT f FROM FerryConfig f WHERE f.vehicleCapacity = :vehicleCapacity"),
    @NamedQuery(name = "FerryConfig.findByWeightCapacity", query = "SELECT f FROM FerryConfig f WHERE f.weightCapacity = :weightCapacity")})
public class FerryConfig implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "PEOPLE_CAPACITY")
    private int peopleCapacity;
    @Basic(optional = false)
    @NotNull
    @Column(name = "VEHICLE_CAPACITY")
    private int vehicleCapacity;
    @Basic(optional = false)
    @NotNull
    @Column(name = "WEIGHT_CAPACITY")
    private int weightCapacity;
    @ManyToMany(mappedBy = "ferryConfigCollection")
    private Collection<Departure> departureCollection;
    @JoinColumn(name = "FERRY_ID", referencedColumnName = "ID")
    @ManyToOne
    private Ferry ferryId;

    public FerryConfig() {
    }

    public FerryConfig(Integer id) {
        this.id = id;
    }

    public FerryConfig(Integer id, int peopleCapacity, int vehicleCapacity, int weightCapacity) {
        this.id = id;
        this.peopleCapacity = peopleCapacity;
        this.vehicleCapacity = vehicleCapacity;
        this.weightCapacity = weightCapacity;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getPeopleCapacity() {
        return peopleCapacity;
    }

    public void setPeopleCapacity(int peopleCapacity) {
        this.peopleCapacity = peopleCapacity;
    }

    public int getVehicleCapacity() {
        return vehicleCapacity;
    }

    public void setVehicleCapacity(int vehicleCapacity) {
        this.vehicleCapacity = vehicleCapacity;
    }

    public int getWeightCapacity() {
        return weightCapacity;
    }

    public void setWeightCapacity(int weightCapacity) {
        this.weightCapacity = weightCapacity;
    }

    @XmlTransient
    public Collection<Departure> getDepartureCollection() {
        return departureCollection;
    }

    public void setDepartureCollection(Collection<Departure> departureCollection) {
        this.departureCollection = departureCollection;
    }

    public Ferry getFerryId() {
        return ferryId;
    }

    public void setFerryId(Ferry ferryId) {
        this.ferryId = ferryId;
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
        if (!(object instanceof FerryConfig)) {
            return false;
        }
        FerryConfig other = (FerryConfig) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dk.cphbusiness.entities.FerryConfig[ id=" + id + " ]";
    }
    
}
