/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dk.cphbusiness.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author mhck
 */
@Entity
@Table(name = "DEPARTURE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Departure.findAll", query = "SELECT d FROM Departure d"),
    @NamedQuery(name = "Departure.findById", query = "SELECT d FROM Departure d WHERE d.id = :id"),
    @NamedQuery(name = "Departure.findByDepartureTime", query = "SELECT d FROM Departure d WHERE d.departureTime = :departureTime"),
    @NamedQuery(name = "Departure.findByDepartureDate", query = "SELECT d FROM Departure d WHERE d.departureDate = :departureDate")})
public class Departure implements Serializable {
    @ManyToMany(mappedBy = "departureCollection")
    private Collection<Schedule> scheduleCollection;
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Integer id;
    @Size(max = 4)
    @Column(name = "DEPARTURE_TIME")
    private String departureTime;
    @Column(name = "DEPARTURE_DATE")
    @Temporal(TemporalType.DATE)
    private Date departureDate;
    @JoinTable(name = "DEPARTURE_HAS_CONFIGS", joinColumns = {
        @JoinColumn(name = "DEPARTURE_ID", referencedColumnName = "ID")}, inverseJoinColumns = {
        @JoinColumn(name = "FERRY_CONFIG_ID", referencedColumnName = "ID")})
    @ManyToMany
    private Collection<FerryConfig> ferryConfigCollection;
    @JoinColumn(name = "ROUTE_ID", referencedColumnName = "ID")
    @ManyToOne
    private Route route;
    @OneToMany(mappedBy = "departureId")
    private Collection<Reservation> reservationCollection;

    public Departure(Integer id, String departureTime, Date departureDate, Collection<FerryConfig> ferryConfigCollection, Route route, Collection<Reservation> reservationCollection) {
        this.id = id;
        this.departureTime = departureTime;
        this.departureDate = departureDate;
        this.ferryConfigCollection = ferryConfigCollection;
        this.route = route;
        this.reservationCollection = reservationCollection;
    }
    
    public Departure() {
    }

    public Departure(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    public Date getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(Date departureDate) {
        this.departureDate = departureDate;
    }

    @XmlTransient
    public Collection<FerryConfig> getFerryConfigCollection() {
        return ferryConfigCollection;
    }

    public void setFerryConfigCollection(Collection<FerryConfig> ferryConfigCollection) {
        this.ferryConfigCollection = ferryConfigCollection;
    }

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    @XmlTransient
    public Collection<Reservation> getReservationCollection() {
        return reservationCollection;
    }

    public void setReservationCollection(Collection<Reservation> reservationCollection) {
        this.reservationCollection = reservationCollection;
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
        if (!(object instanceof Departure)) {
            return false;
        }
        Departure other = (Departure) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dk.cphbusiness.entities.Departure[ id=" + id + " ]";
    }
}
