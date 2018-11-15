package factory;

import dao.PlaceDAO;
import org.hibernate.SessionFactory;

import javax.persistence.Persistence;

public class Factory {
    public static final Factory factory = new Factory();
    private SessionFactory sessionFactory;
    private PlaceDAO placeDAO;

    private Factory(){
        sessionFactory=(SessionFactory) Persistence.createEntityManagerFactory( "org.hibernate.tutorial.jpa" );
    }

    public static Factory getInstance(){
        return factory;
    }

    public synchronized PlaceDAO getPlaceDAO(){
        if(placeDAO!=null) {
            return placeDAO;
        }
        placeDAO =new PlaceDAO(sessionFactory);
        return placeDAO;
    }
}
