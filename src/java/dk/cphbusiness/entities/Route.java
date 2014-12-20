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
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author mhck
 */
@Entity
@Table(name = "ROUTE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Route.findAll", query = "SELECT r FROM Route r"),
    @NamedQuery(name = "Route.findById", query = "SELECT r FROM Route r WHERE r.id = :id"),
    @NamedQuery(name = "Route.findByDuration", query = "SELECT r FROM Route r WHERE r.duration = :duration")})
public class Route implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Integer id;
    @Column(name = "DURATION")
    private Integer duration;
    @JoinColumn(name = "ID_ORIGIN", referencedColumnName = "ID")
    @ManyToOne
    private Harbour idOrigin;
    @JoinColumn(name = "ID_DESTINATION", referencedColumnName = "ID")
    @ManyToOne
    private Harbour idDestination;
    @OneToMany(mappedBy = "routeId")
    private Collection<Departure> departureCollection;
    @OneToMany(mappedBy = "routeId")
    private Collection<Price> priceCollection;

    public Route(Integer id, Integer duration, Harbour idOrigin, Harbour idDestination, Collection<Departure> departureCollection, Collection<Price> priceCollection) {
        this.id = id;
        this.duration = duration;
        this.idOrigin = idOrigin;
        this.idDestination = idDestination;
        this.departureCollection = departureCollection;
        this.priceCollection = priceCollection;
    }
    
    public Route() {
    }

    public Route(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Harbour getIdOrigin() {
        return idOrigin;
    }

    public void setIdOrigin(Harbour idOrigin) {
        this.idOrigin = idOrigin;
    }

    public Harbour getIdDestination() {
        return idDestination;
    }

    public void setIdDestination(Harbour idDestination) {
        this.idDestination = idDestination;
    }

    @XmlTransient
    public Collection<Departure> getDepartureCollection() {
        return departureCollection;
    }

    public void setDepartureCollection(Collection<Departure> departureCollection) {
        this.departureCollection = departureCollection;
    }

    @XmlTransient
    public Collection<Price> getPriceCollection() {
        return priceCollection;
    }

    public void setPriceCollection(Collection<Price> priceCollection) {
        this.priceCollection = priceCollection;
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
        if (!(object instanceof Route)) {
            return false;
        }
        Route other = (Route) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dk.cphbusiness.entities.Route[ id=" + id + " ]";
    }
    
}
