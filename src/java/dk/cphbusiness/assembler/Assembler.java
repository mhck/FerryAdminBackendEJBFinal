package dk.cphbusiness.assembler;
import dk.cphbusiness.entities.Departure;
import dk.cphbusiness.entities.Ferry;
import dk.cphbusiness.entities.FerryConfig;
import dk.cphbusiness.entities.Harbour;
import dk.cphbusiness.entities.Price;
import dk.cphbusiness.entities.Schedule;
import dk.cphbusiness.entities.Route;
import ferry.dto.DepartureDetail;
import ferry.dto.FerryConfigDetail;
import ferry.dto.HarbourSummary;
import ferry.dto.RouteDetail;
import ferry.dto.ScheduleDetail;
import java.util.ArrayList;
import java.util.Collection;

/**
 *
 * @author mhck
 */
public class Assembler {
    public Collection<RouteDetail> getRouteDetailFromRoutes(Collection<Route> routes) {
        Collection<RouteDetail> resultList = new ArrayList<>();
        for (Route route : routes) {    
            ArrayList<Price> priceList = (ArrayList<Price>) route.getPriceCollection();
            int priceAmount = priceList.get(0).getAmount().intValue(); // TODO
            HarbourSummary originHarbour = new HarbourSummary(route.getIdOrigin().getName(), route.getIdOrigin().getId());
            HarbourSummary destinationHarbour = new HarbourSummary(route.getIdOrigin().getName(), route.getIdOrigin().getId());
            RouteDetail routeDetail = new RouteDetail(route.getDuration(), destinationHarbour, originHarbour, priceAmount, route.getId());
            resultList.add(routeDetail);
        }
        return resultList;
    }
    
    public Route getRouteFromRouteDetail(RouteDetail routeDetail) {
        return new Route(routeDetail.getID(), routeDetail.getDuration(), getHarbourFromHarbourSummary(routeDetail.getHabourOrigin()), getHarbourFromHarbourSummary(routeDetail.getHabourOrigin()), new ArrayList<Departure>(), new ArrayList<Price>());
    }
    
    public Harbour getHarbourFromHarbourSummary(HarbourSummary harbourSummary) {
        return new Harbour(harbourSummary.getId(), harbourSummary.getName(), new ArrayList<Ferry>(), new ArrayList<Route>(), new ArrayList<Route>());
    }
    
    public Collection<FerryConfigDetail> getFerryConfigDetailFromFerryConfig(Collection<FerryConfig> ferryConfigs) {
        Collection<FerryConfigDetail> resultList = new ArrayList<>();
        for (FerryConfig ferryConfig : ferryConfigs) {
            ArrayList<Departure> departures = (ArrayList<Departure>) ferryConfig.getDepartureCollection();
            resultList.add(new FerryConfigDetail(ferryConfig.getPeopleCapacity(), ferryConfig.getVehicleCapacity(), ferryConfig.getWeightCapacity(), ferryConfig.getId()));
        }
        return resultList;
    }
    
    public FerryConfig getFerryConfigFromFerryConfigDetail(FerryConfigDetail ferryConfigDetail) {
        return new FerryConfig(ferryConfigDetail.getId(), ferryConfigDetail.getPeopleCapacity(), ferryConfigDetail.getVehicleCapacity(), ferryConfigDetail.getWeightCapacity());
    }
    
    public DepartureDetail getDepartureDetailFromDeparture(Departure departure) {
        Collection<Route> routes = new ArrayList<>();
        //routes.add(departure.getRouteId());
        ArrayList<RouteDetail> routeDetail = (ArrayList<RouteDetail>) getRouteDetailFromRoutes(routes);
        ArrayList<FerryConfigDetail> ferryConfig = (ArrayList<FerryConfigDetail>) getFerryConfigDetailFromFerryConfig(departure.getFerryConfigCollection());
        DepartureDetail departureDetail = new DepartureDetail(Integer.parseInt(departure.getDepartureTime()), departure.getDepartureDate(), departure.getId(), routeDetail.get(0), ferryConfig.get(0));
        return departureDetail;
    }
    
    public Collection<Departure> getDepartureFromDepartureDetail(Collection<DepartureDetail> departureDetails) {
        ArrayList<Departure> result = new ArrayList<>();
        for (DepartureDetail departureDetail : departureDetails) {
            ArrayList<FerryConfig> ferryConfigs = new ArrayList<FerryConfig>();
            ferryConfigs.add(getFerryConfigFromFerryConfigDetail(departureDetail.getConfig()));
            Departure departure = new Departure(departureDetail.getId(), Integer.toString(departureDetail.getDepartureTime()), departureDetail.getDepartureDate(), ferryConfigs, getRouteFromRouteDetail(departureDetail.getRouteDetail()), null);
            result.add(departure);
        }
        return result;
    }

    public Schedule getScheduleFromScheduleDetail(ScheduleDetail sd) {
        return new Schedule(sd.getID(), sd.getStartDate(), sd.getEndDate(), getDepartureFromDepartureDetail(sd.getDepartureDetail()));
    }
}