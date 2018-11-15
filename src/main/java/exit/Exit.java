package exit;

import dao.PlaceDAO;
import entity.Place;
import factory.FactoryDAO;

import java.util.ArrayList;
import java.util.List;

public class Exit {

    public static void main (String [] args){

        PlaceDAO placeDAO = FactoryDAO.getInstance().getPlaceDAO();
//        PlaceDAO placeDAO = Factory.getInstance().getPlaceDAO();
        List<Place> places = new ArrayList<>();

        for (int i=1; i<6; i++){

            Place place = new Place();
            place.setNumberPlace(i);
            place.setNumberCar(i);
            place.setNumberTrain(i);
            place.setPricePlace(10*i);
            place.setIsReal(true);
            place.setTypeCarPlace("Platzkart");
            place.setRemarkPlace("remark"+i);
            places.add(place);

        placeDAO.insertPlace(place);
        }
        List<Place> placesPull = placeDAO.getAllPlaces();
        placesPull.forEach(PlaceDAO::printPlace);
    }
}
