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

    public static void main (String [] args){

        UniversalDAO universalDAO =FactoryDAO.getInstance().getUniversalDAO();

        List<Route> routes = new ArrayList<>();
        List<Train> trains = new ArrayList<>();

        //create 5 train and 5 route
      for (int i=1; i<6; i++){

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
          Train train = new Train("Kyiv - Misto"+i, i,1, 1, 1, routes.get(i-1));

          routes.get(i-1).setNumberTrainRoute(train.getNumberTrain());

          universalDAO.insertClass(routes.get(i-1)) ;

          train.setIdRouteTrain(routes.get(i-1).getIdRoute());

          trains.add(train);

          universalDAO.insertClass(train);

          train.getCars().forEach(car -> { universalDAO.insertClass(car);

                                  car.getPlaces().forEach(place -> universalDAO.insertClass(place));
          });

      }

//        List<Place> places = new ArrayList<>();
//
//        for (int i=1; i<6; i++){
//
//            Place place = new Place();
//            place.setNumberPlace(i);
//            place.setNumberCar(7);
//            place.setNumberTrain(12);
//            place.setPricePlace(10*i);
//            place.setIsReal(true);
//            place.setTypeCarPlace("Platzkart");
//            place.setRemarkPlace("remark"+i);
//            places.add(place);
//
//            universalDAO.insertClass(place);
//        }

//        List<Place> placesPull = universalDAO.getAll(places.get(1));

        List<Place> placesPull = universalDAO.getAll(trains.get(0).getCars().get(0).getPlaces().get(0));
        placesPull.forEach(Place::printPlace);
    }
}
