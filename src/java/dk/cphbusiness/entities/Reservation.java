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
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
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
@Table(name = "RESERVATION")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Reservation.findAll", query = "SELECT r FROM Reservation r"),
    @NamedQuery(name = "Reservation.findById", query = "SELECT r FROM Reservation r WHERE r.id = :id"),
    @NamedQuery(name = "Reservation.findByReservationNumber", query = "SELECT r FROM Reservation r WHERE r.reservationNumber = :reservationNumber"),
    @NamedQuery(name = "Reservation.findByHasArrived", query = "SELECT r FROM Reservation r WHERE r.hasArrived = :hasArrived")})
public class Reservation implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Integer id;
    @Size(max = 80)
    @Column(name = "RESERVATION_NUMBER")
    private String reservationNumber;
    @Column(name = "HAS_ARRIVED")
    private Boolean hasArrived;
    @JoinTable(name = "RESERVATION_HAS_TRAVELING_ENTITY", joinColumns = {
        @JoinColumn(name = "RESERVATION_ID", referencedColumnName = "ID")}, inverseJoinColumns = {
        @JoinColumn(name = "TRAVELING_ENTITY_ID", referencedColumnName = "ID")})
    @ManyToMany
    private Collection<TravelingEntity> travelingEntityCollection;
    @JoinColumn(name = "BOOKER_ID", referencedColumnName = "ID")
    @ManyToOne
    private Booker bookerId;
    @JoinColumn(name = "DEPARTURE_ID", referencedColumnName = "ID")
    @ManyToOne
    private Departure departureId;

    public Reservation() {
    }

    public Reservation(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getReservationNumber() {
        return reservationNumber;
    }

    public void setReservationNumber(String reservationNumber) {
        this.reservationNumber = reservationNumber;
    }

    public Boolean getHasArrived() {
        return hasArrived;
    }

    public void setHasArrived(Boolean hasArrived) {
        this.hasArrived = hasArrived;
    }

    @XmlTransient
    public Collection<TravelingEntity> getTravelingEntityCollection() {
        return travelingEntityCollection;
    }

    public void setTravelingEntityCollection(Collection<TravelingEntity> travelingEntityCollection) {
        this.travelingEntityCollection = travelingEntityCollection;
    }

    public Booker getBookerId() {
        return bookerId;
    }

    public void setBookerId(Booker bookerId) {
        this.bookerId = bookerId;
    }

    public Departure getDepartureId() {
        return departureId;
    }

    public void setDepartureId(Departure departureId) {
        this.departureId = departureId;
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
        if (!(object instanceof Reservation)) {
            return false;
        }
        Reservation other = (Reservation) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dk.cphbusiness.entities.Reservation[ id=" + id + " ]";
    }
    
}
