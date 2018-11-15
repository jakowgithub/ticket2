package factory;

import dao.PlaceDAO;
import org.hibernate.SessionFactory;

import javax.persistence.Persistence;

public class FactoryDAO {
    public static final FactoryDAO factory = new FactoryDAO();
    private SessionFactory sessionFactory;
    private PlaceDAO placeDAO;

    private FactoryDAO (){
        sessionFactory=(SessionFactory) Persistence
                .createEntityManagerFactory( "org.hibernate.tutorial.jpa" );
    }
    public static FactoryDAO getInstance(){
        return factory;
    }

    public synchronized PlaceDAO getPlaceDAO(){

        if(placeDAO!=null) {return placeDAO;}

        placeDAO = new PlaceDAO (sessionFactory);

        return placeDAO;
    }

}
