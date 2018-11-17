package dao;

import org.hibernate.SessionFactory;

import javax.persistence.EntityManager;
import java.util.List;

public class UniversalDAO   {

    private SessionFactory sessionFactory;
    public UniversalDAO (SessionFactory sessionFactory){ this.sessionFactory = sessionFactory; }

    public <T> void  insertClass  (T t){
        EntityManager entityManager =sessionFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(t);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public <T> void updatePlace(T t){
        EntityManager entityManager =sessionFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.merge(t);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public <T> List<T> getAll(T t){
        EntityManager entityManager = sessionFactory.createEntityManager();
        entityManager.getTransaction().begin();

        //get string for hql request
        Class clazz=t.getClass();
        String nameClass = clazz.toString().substring(clazz.toString().indexOf(".")+1);
        String nameTable = nameClass.toLowerCase();
        String idTable = "id"+nameClass;
        String sqlRequest = "select t from "+nameTable+" t where t."+idTable+"<100";
        System.out.println(sqlRequest);
        System.out.println(clazz);

        //select all row with id<100
        List <T> classes = (List <T>) entityManager.createQuery(sqlRequest, clazz).setMaxResults(100).getResultList();
        entityManager.close();
        return classes;
    }


}
