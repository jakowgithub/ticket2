package dao;

import entity.Place;
import org.hibernate.SessionFactory;

import javax.persistence.EntityManager;
import java.util.List;

public class PlaceDAO {
    private SessionFactory sessionFactory;

    public PlaceDAO (SessionFactory sessionFactory){ this.sessionFactory = sessionFactory; }

    public void insertPlace(Place place){
        EntityManager entityManager =sessionFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(place);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public void updatePlace(Place place){
        EntityManager entityManager =sessionFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.merge(place);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public List<Place> getAllPlaces(){
        EntityManager entityManager = sessionFactory.createEntityManager();
        entityManager.getTransaction().begin();
        List<Place> places = entityManager.createQuery("from Place", Place.class).getResultList();

        entityManager.close();
        return places;
    }

    public static void printPlace (Place place) {
        System.out.println(place.getIdPlace()+" "+
                           place.getNumberPlace()+" "+
                           place.getTypeCarPlace()+" "+
                           place.getIsReal()
                );
    }
}
