package dk.cphbusiness.controller;

import dk.cphbusiness.assembler.Assembler;
import cphbusiness.entities.Ferry;
import cphbusiness.entities.FerryConfig;
import cphbusiness.entities.Route;
import cphbusiness.entities.Schedule;
import ferry.contract.AdminContract;
import ferry.dto.DepartureDetail;
import ferry.dto.FerryConfigDetail;
import ferry.dto.FerryDetail;
import ferry.dto.FerryIdentifier;
import ferry.dto.RouteDetail;
import ferry.dto.ScheduleDetail;
import ferry.dto.ScheduleIdentifier;
import ferry.eto.DataAccessException;
import ferry.eto.NoSuchFerryException;
import ferry.eto.NoSuchHarbourException;
import ferry.eto.NoSuchScheduleException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@Stateless
public class FerryAdminManager implements AdminContract {

    private EntityManager em;
    private Assembler assembler;

    public void setEntityManager(EntityManager manager) {
        this.em = manager;
    }

    public FerryAdminManager() {
        if (em == null) {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("FerryAdminBackendEJBPU");
            em = emf.createEntityManager();
        }
        assembler = new Assembler();
    }

    public FerryAdminManager(EntityManager em) {
        this.em = em;
        assembler = new Assembler();
    }

    @Override
    public void delayFerry(FerryIdentifier fi, Date date, int i, int i1) throws NoSuchFerryException, DataAccessException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Collection<FerryDetail> showFerries() throws DataAccessException {
        List<Ferry> ferries = (List<Ferry>) em.createNamedQuery("Ferry.findAll").getResultList();
        Collection<FerryDetail> ferries2 = new ArrayList<>();
        ferries2.add(new FerryDetail(1, "test", null));
        return ferries2;
    }

    @Override
    public Collection<FerryConfigDetail> showFerryConfigs() {
        Collection<FerryConfig> ferryConfigs = (Collection<FerryConfig>) em.createNamedQuery("FerryConfig.findAll").getResultList();
        return assembler.getFerryConfigDetailFromFerryConfig(ferryConfigs);
    }

    @Override
    public void addSchedule(ScheduleDetail sd) throws DataAccessException, InvalidDateException {
        if (sd.getStartDate().before(new Date()) && sd.getEndDate().after(new Date()) && sd.getStartDate().before(sd.getEndDate())) {
            throw new InvalidDateException("Schdule date is before todays date");
        }
        for (DepartureDetail depature : sd.getDepartureDetail()) {
            if (depature.getDepartureDate().before(sd.getStartDate()) && depature.getDepartureDate().after(sd.getEndDate())) {
                throw new InvalidDateException("One of the depatures got a Date outside the Schdule range");
            }
        }
        try {
            em.getTransaction().begin();
            Schedule schedule = assembler.getScheduleFromScheduleDetail(sd);
            em.persist(schedule);
            em.getTransaction().commit();
            em.close();
        } catch (Exception ex) {
            throw new DataAccessException("Error saving the data");
        }
    }

    @Override
    public Collection<ScheduleDetail> showSchedules() throws NoSuchScheduleException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Collection<ScheduleDetail> showSchedulesForDate(Date date) throws NoSuchScheduleException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Collection<RouteDetail> showRoutes() throws NoSuchHarbourException {
        //Thread.currentThread().setContextClassLoader(Route.class.getClassLoader());
        List<Route> routes = em.createNamedQuery("Route.findAll").getResultList();
        return assembler.getRouteDetailFromRoutes(routes);
    }

    @Override
    public void assignFerryToSchedule(FerryIdentifier fi, ScheduleIdentifier si) throws DataAccessException, NoSuchFerryException, NoSuchScheduleException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
