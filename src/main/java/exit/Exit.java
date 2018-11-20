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

public class Exit {

    private int maxNumberCar=3300000;
    private int getMaxNumberCar () { return maxNumberCar;}
    private void setMaxNumberCar (int maxNumberCar){this.maxNumberCar = maxNumberCar;}

    public static void main (String [] args){

        UniversalDAO universalDAO =FactoryDAO.getInstance().getUniversalDAO();
        universalDAO.clearAllTables();

        List<Route> routes = new ArrayList<>();
        List<Train> trains = new ArrayList<>();

        //create 3 train and 3 route

      for (int i=1; i<4; i++){

          if (i<4) {Route route = new Route("Route"+i,
                                           "Station"+i,
                                           "Station"+(1+i),
                                           "Station"+(2+i),
          LocalDateTime.of(2019, Month.JANUARY, 1, 7+i, 00, 00),
          LocalDateTime.of(2019, Month.JANUARY, 1, 12+i, 10, 30),
          LocalDateTime.of(2019, Month.JANUARY, 1, 17+i, 15, 30));
          routes.add(route);
          }
          else {
              Route route = new Route("Route"+i,
                      "Station"+(i-3),
                      "Station"+(i-1),
                      "Station"+i,
                      LocalDateTime.of(2019, Month.JANUARY, 1, 6+i, 00, 00),
                      LocalDateTime.of(2019, Month.JANUARY, 1, 10+i, 10, 30),
                      LocalDateTime.of(2019, Month.JANUARY, 1, 14+i, 15, 30));
                      routes.add(route);
          }
          Train train = new Train("Kyiv - Misto"+i, i,1, 1, 1, routes.get(i-1), 3300000);

          routes.get(i-1).setNumberTrainRoute(train.getNumberTrain());

          universalDAO.insertClass(routes.get(i-1)) ;

          train.setIdRouteTrain(routes.get(i-1).getIdRoute());

          trains.add(train);

          universalDAO.insertClass(train);

          train.getCars().forEach(car -> {

              universalDAO.insertClass(car);

              car.getPlaces().forEach(place -> universalDAO.insertClass(place));
          });

      }
       List<Place> placesPull = universalDAO.getAll(trains.get(0).getCars().get(0).getPlaces().get(0));

       placesPull.forEach(Place::printPlace);
    }
}
