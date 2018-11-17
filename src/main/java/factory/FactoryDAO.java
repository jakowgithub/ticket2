package factory;

import dao.UniversalDAO;
import org.hibernate.SessionFactory;

import javax.persistence.Persistence;

public class FactoryDAO {
    public static final FactoryDAO factory = new FactoryDAO();
    private SessionFactory sessionFactory;
//    private PlaceDAO placeDAO;
    private UniversalDAO universalDAO;

    private FactoryDAO (){
        sessionFactory=(SessionFactory) Persistence.createEntityManagerFactory( "org.hibernate.tutorial.jpa" );
    }
    public static FactoryDAO getInstance(){
        return factory;
    }

//    public synchronized PlaceDAO getPlaceDAO(){
//
//        if(placeDAO!=null) {return placeDAO;}
//
//        placeDAO = new PlaceDAO (sessionFactory);
//
//        return placeDAO;
//    }
    public synchronized UniversalDAO  getUniversalDAO(){

        if(universalDAO!=null) {return universalDAO;}

        universalDAO = new UniversalDAO(sessionFactory);

        return  universalDAO;
    }

}
