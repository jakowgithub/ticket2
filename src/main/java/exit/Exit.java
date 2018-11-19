package exit;

import dao.UniversalDAO;
import entity.Route;
import entity.Train;
import factory.FactoryDAO;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Exit {

    public static void main (String [] args){

        UniversalDAO universalDAO =FactoryDAO.getInstance().getUniversalDAO();

        List<Route> routes = new ArrayList<>();
        List<Train> trains = new ArrayList<>();

        //clear all data in table if exist annotations @entity or @table
        Set<Class<?>> annotatedClasses = universalDAO.getClassesInPackage();

        // search no-argument construktor
        for (Class<?> clazz: annotatedClasses) {
            Constructor[] constuctors = clazz.getDeclaredConstructors();
            Constructor constructor = null;

            for (int i = 0; i < constuctors.length; i++) {

                if (0 == constuctors[i].getGenericParameterTypes().length) {
                    constructor = constuctors[i];
                    constructor.setAccessible(true);
                    break;
                }
            }
            try {
                List<?> cleaningClasses = universalDAO.getAll(constructor.newInstance());

                if (cleaningClasses.size()>0) universalDAO.remove(cleaningClasses.get(0));

            } catch (InstantiationException e) { e.printStackTrace();
            } catch (IllegalAccessException e) { e.printStackTrace();
            } catch (InvocationTargetException e) { e.printStackTrace(); }
        }

//        //create 3 train and 3 route

//      for (int i=1; i<4; i++){
//
//          if (i<4) {Route route = new Route("Route"+i,
//                                           "Station"+i,
//                                           "Station"+(1+i),
//                                           "Station"+(2+i),
//          LocalDateTime.of(2019, Month.JANUARY, 1, 7+i, 00, 00),
//          LocalDateTime.of(2019, Month.JANUARY, 1, 12+i, 10, 30),
//          LocalDateTime.of(2019, Month.JANUARY, 1, 17+i, 15, 30));
//          routes.add(route);
//          }
//          else {
//              Route route = new Route("Route"+i,
//                      "Station"+(i-3),
//                      "Station"+(i-1),
//                      "Station"+i,
//                      LocalDateTime.of(2019, Month.JANUARY, 1, 6+i, 00, 00),
//                      LocalDateTime.of(2019, Month.JANUARY, 1, 10+i, 10, 30),
//                      LocalDateTime.of(2019, Month.JANUARY, 1, 14+i, 15, 30));
//                      routes.add(route);
//          }
//          Train train = new Train("Kyiv - Misto"+i, i,1, 1, 1, routes.get(i-1));
//
//
//          routes.get(i-1).setNumberTrainRoute(train.getNumberTrain());
//
//          universalDAO.insertClass(routes.get(i-1)) ;
//
//          train.setIdRouteTrain(routes.get(i-1).getIdRoute());
//
//          trains.add(train);
//
//          universalDAO.insertClass(train);
//
//          train.getCars().forEach(car -> {
//              universalDAO.insertClass(car);
//
//              car.getPlaces().forEach(place -> universalDAO.insertClass(place));
//          });
//
//      }
//       List<Place> placesPull = universalDAO.getAll(trains.get(0).getCars().get(0).getPlaces().get(0));
//
//       placesPull.forEach(Place::printPlace);
    }
}
