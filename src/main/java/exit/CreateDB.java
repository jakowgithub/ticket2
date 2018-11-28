package exit;

import dao.UniversalDAO;
import entity.Place;
import entity.Route;
import entity.Train;
import factory.FactoryDAO;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

public class CreateDB {
    public static int maxNumberCar=3300000;
    public static int getMaxNumberCar () { return maxNumberCar;}
    public static void setMaxNumberCar (int maxNC){maxNumberCar = maxNC;}

    public CreateDB() {}

    public static void createDataBase (){

        UniversalDAO universalDAO = FactoryDAO.getInstance().getUniversalDAO();

        universalDAO.clearAllTables();

        List<Route> routes = new ArrayList<>();
        List<Train> trains = new ArrayList<>();

        //create 5 train (6 Car = 3 Platzkart + 2 Cupe + 1SV) and 5 route
        for (int i=1; i<6; i++){

            if (i<4) {Route route = new Route("Route"+i,
                    "Station"+i,
                    "Station"+(1+i),
                    "Station"+(2+i),
                    LocalDateTime.of(2019, Month.JANUARY, 1, 7+i, 5, 30),
                    LocalDateTime.of(2019, Month.JANUARY, 1, 12+i, 10, 30),
                    LocalDateTime.of(2019, Month.JANUARY, 1, 17+i, 15, 30));
                routes.add(route);
            }
            else     { Route route = new Route("Route"+i,
                    "Station"+(i-3),
                    "Station"+(i-1),
                    "Station"+i,
                    LocalDateTime.of(2019, Month.JANUARY, 1, 6+i, 5, 30),
                    LocalDateTime.of(2019, Month.JANUARY, 1, 10+i, 10, 30),
                    LocalDateTime.of(2019, Month.JANUARY, 1, 14+i, 15, 30));
                routes.add(route);
            }
            Train train = new Train("Kyiv - Misto"+i, i,3, 2, 1, routes.get(i-1), getMaxNumberCar());

            setMaxNumberCar(getMaxNumberCar()+train.getCareSV()+train.getCareCupe()+train.getCarePlatzkart());

            routes.get(i-1).setNumberTrainRoute(train.getNumberTrain());

            universalDAO.insertClass(routes.get(i-1)) ;

            train.setIdRouteTrain(routes.get(i-1).getIdRoute());

            trains.add(train);

            universalDAO.insertClass(train);

            routes.get(i-1).setTrain(train);

            universalDAO.updateClass(routes.get(i-1));

            train.getCars().forEach(car -> {

                universalDAO.insertClass(car);

                car.getPlaces().forEach(place -> universalDAO.insertClass(place));
            });

        }
        List<Place> placesPull = universalDAO.getAll(trains.get(0).getCars().get(0).getPlaces().get(0));
        placesPull.forEach(Place::printPlace);

        List<Route> routesPull = universalDAO.getRouteStartTerminalStation("Station2", "Station3");
        routesPull.forEach(System.out::println);

        universalDAO.getSessionFactory().close();
    }
}
