package dao;

import org.hibernate.SessionFactory;

public class PlaceDAO {
    private SessionFactory sessionFactory;

    public PlaceDAO (SessionFactory sessionFactory){ this.sessionFactory = sessionFactory; }

//    public void insertPlace(Place place){
//        EntityManager entityManager =sessionFactory.createEntityManager();
//        entityManager.getTransaction().begin();
//        entityManager.persist(place);
//        entityManager.getTransaction().commit();
//        entityManager.close();
//    }
//
//    public void updatePlace(Place place){
//        EntityManager entityManager =sessionFactory.createEntityManager();
//        entityManager.getTransaction().begin();
//        entityManager.merge(place);
//        entityManager.getTransaction().commit();
//        entityManager.close();
//    }
//
//    public List<Place> getAllPlaces(){
//        EntityManager entityManager = sessionFactory.createEntityManager();
//        entityManager.getTransaction().begin();
//        List<Place> places = entityManager.createQuery("select p from place p where p.idPlace<100" , Place.class).setMaxResults(100).getResultList();
//
////          EXAMPL
////        public List findWithName(String name) {
////            return em.createQuery(
////                    "SELECT c FROM Customer c WHERE c.name LIKE :custName")
////                    .setParameter("custName", name)
////                    .setMaxResults(10)
////                    .getResultList();
////        }
//
//        entityManager.close();
//        return places;
//    }

}
