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
@Table(name = "TRAVELING_ENTITY")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TravelingEntity.findAll", query = "SELECT t FROM TravelingEntity t"),
    @NamedQuery(name = "TravelingEntity.findById", query = "SELECT t FROM TravelingEntity t WHERE t.id = :id"),
    @NamedQuery(name = "TravelingEntity.findByWeight", query = "SELECT t FROM TravelingEntity t WHERE t.weight = :weight"),
    @NamedQuery(name = "TravelingEntity.findByRegNo", query = "SELECT t FROM TravelingEntity t WHERE t.regNo = :regNo"),
    @NamedQuery(name = "TravelingEntity.findByVehicleLength", query = "SELECT t FROM TravelingEntity t WHERE t.vehicleLength = :vehicleLength")})
public class TravelingEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Integer id;
    @Column(name = "WEIGHT")
    private Integer weight;
    @Size(max = 40)
    @Column(name = "REG_NO")
    private String regNo;
    @Column(name = "VEHICLE_LENGTH")
    private Integer vehicleLength;
    @ManyToMany(mappedBy = "travelingEntityCollection")
    private Collection<Reservation> reservationCollection;
    @OneToMany(mappedBy = "travelingEntityId")
    private Collection<Price> priceCollection;
    @JoinColumn(name = "DTYPE", referencedColumnName = "DTYPE")
    @ManyToOne
    private TravelingEntityType dtype;

    public TravelingEntity() {
    }

    public TravelingEntity(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public String getRegNo() {
        return regNo;
    }

    public void setRegNo(String regNo) {
        this.regNo = regNo;
    }

    public Integer getVehicleLength() {
        return vehicleLength;
    }

    public void setVehicleLength(Integer vehicleLength) {
        this.vehicleLength = vehicleLength;
    }

    @XmlTransient
    public Collection<Reservation> getReservationCollection() {
        return reservationCollection;
    }

    public void setReservationCollection(Collection<Reservation> reservationCollection) {
        this.reservationCollection = reservationCollection;
    }

    @XmlTransient
    public Collection<Price> getPriceCollection() {
        return priceCollection;
    }

    public void setPriceCollection(Collection<Price> priceCollection) {
        this.priceCollection = priceCollection;
    }

    public TravelingEntityType getDtype() {
        return dtype;
    }

    public void setDtype(TravelingEntityType dtype) {
        this.dtype = dtype;
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
        if (!(object instanceof TravelingEntity)) {
            return false;
        }
        TravelingEntity other = (TravelingEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dk.cphbusiness.entities.TravelingEntity[ id=" + id + " ]";
    }
    
}
