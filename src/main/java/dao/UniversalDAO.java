package dao;

import entity.Route;
import org.hibernate.SessionFactory;

import javax.persistence.EntityManager;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

public class UniversalDAO   {

    private SessionFactory sessionFactory;
    public UniversalDAO (SessionFactory sessionFactory){ this.sessionFactory = sessionFactory; }

    public SessionFactory getSessionFactory () {return sessionFactory;}

    public <T> void  insertClass  (T t){
        EntityManager entityManager =sessionFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(t);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public <T> void updateClass(T t){
        EntityManager entityManager =sessionFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.merge(t);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public <T> List <T> getAll(T t){
        EntityManager entityManager = sessionFactory.createEntityManager();
        entityManager.getTransaction().begin();

        //get string for hql request
        Class clazz=t.getClass();
        String nameClass = clazz.toString().substring(clazz.toString().indexOf(".")+1);
        String nameTable = nameClass.toLowerCase();
        String idTable = "id"+nameClass;
        String sqlRequest = "select t from " + nameTable + " t where t." + idTable + "<10000";

        //select all row with id<10000
        List <T> classes =  (List <T>) entityManager.createQuery(sqlRequest, clazz)
                                                    .setMaxResults(10000)
                                                    .getResultList();

        entityManager.close();
        return classes;
    }

    public  <T> void clearOneTable(T t){
        EntityManager entityManager = sessionFactory.createEntityManager();

        entityManager.getTransaction().begin();

        //get string for hql request
        Class clazz = t.getClass();
        String nameClass = clazz.toString().substring(clazz.toString().indexOf(".")+1);
        String nameTable = nameClass.toLowerCase();
        String idTable = "id"+nameClass;

        String sqlRequest = "delete from " + nameTable;
        entityManager.createQuery(sqlRequest).executeUpdate();
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    // in project delete all data from firs 100 table, which mapped out class
    //  with  annotation @Entity and/or @Table
    public  void clearAllTables (){

        Set<Class<?>> classes = new HashSet<>();
        Path startDir = Paths.get(System.getProperty("user.dir"));

        try (Stream <Path> pathes = Files.find(	startDir, Integer.MAX_VALUE,
                (p, a) -> (a.isRegularFile()) &&
                          (p.toString().substring (p.toString().lastIndexOf(System.getProperty("file.separator"))+1)
                            .endsWith("class")))) {

            // first 100 are processed
            pathes.limit(100).forEach( file->{
                //split() don`t work
                String nameFileTMP1 = file.toString().substring(0, file.toString().length()-6);
                String nameFileNeeded = nameFileTMP1.substring(nameFileTMP1.lastIndexOf("\\")+1);
                String nameFileTMP2 = nameFileTMP1.substring(0, nameFileTMP1.length()-nameFileNeeded.length()-1);
                String nameFolder = nameFileTMP2.substring(nameFileTMP2.lastIndexOf("\\")+1);
                String nameFile = nameFolder + "." + nameFileNeeded;

                try { classes.add(Class.forName(nameFile));

                } catch (ClassNotFoundException e) {System.out.println( nameFile + " does not appear to be a valid class.");}
            });
            } catch (IOException ioe){System.out.println("IOException"); ioe.printStackTrace();}

        Set<Class<?>> classesAnnotated = new HashSet<>();

        for (Class<?> clazz : classes) {

            if(clazz.getAnnotations().length > 1) {
                String anottationName0 = clazz.getAnnotations()[0].toString();
                String anottationName1 = clazz.getAnnotations()[1].toString();

                if (!clazz.isInterface()                &&
                    !clazz.isAnnotation()               &&
                    !clazz.isEnum()                     &&
                   (anottationName0.contains("Entity")  ||
                   (anottationName1.contains("Table")))) {

                    classesAnnotated.add(clazz);
                } } }

       // EntityManager entityManager = sessionFactory.createEntityManager();

        for(Class<?> clazz : classesAnnotated) {

            EntityManager entityManager = sessionFactory.createEntityManager();
            //if class annotated @Entity and @Table but absent table in database
            try {
                entityManager.getTransaction().begin();

                //get string for hql request
            String nameTable = clazz.toString()
                                    .substring(clazz.toString().indexOf(".") + 1)
                                    .toLowerCase();

            String sqlRequest = "delete from " + nameTable;

            entityManager.createQuery(sqlRequest)
                         .executeUpdate();

            entityManager.getTransaction().commit();
            }
            catch (IllegalArgumentException iae) {
                System.out.println("class annotated @Entity and @Table, but absent table in database, IllegalArgumentException "+iae);
            }
           catch (IllegalStateException ise)  {
                System.out.println("class annotated @Entity and @Table, but absent table in database, IllegalStateException "+ise);
            }
            finally {entityManager.close();
            }
        }
       // entityManager.close();
    }
    public  List <Route> getRouteStartStation (String startStation){
        EntityManager entityManager = sessionFactory.createEntityManager();
        entityManager.getTransaction().begin();
        //select first 100 row
        List <Route> routes =  entityManager.createQuery("select r from route r where r.station1 =:station or r.station2 =:station", Route.class)
                                             .setParameter("station", startStation)
                                             .setMaxResults(100)
                                             .getResultList();
        entityManager.close();

        return routes;
    }

    public  List <Route> getRouteTerminalStation (String terminalStation){
        EntityManager entityManager = sessionFactory.createEntityManager();
        entityManager.getTransaction().begin();
        //select first 100 row
        List <Route> routes =  entityManager.createQuery("select r from route r where r.station2 =:station or r.station3 =:station", Route.class)
                .setParameter("station", terminalStation)
                .setMaxResults(100)
                .getResultList();
        entityManager.close();

        return routes;
    }
    public  List <Route> getRouteStartTerminalStation (String startStation, String terminalStation){
        EntityManager entityManager = sessionFactory.createEntityManager();
        entityManager.getTransaction().begin();
        //select first 100 row
        List <Route> routes =  entityManager.createQuery(
                        "select r " +
                           "from route r " +
                           "where (r.station1 =:start and r.station2 =:terminal) or " +
                                 "(r.station1 =:start and r.station3 =:terminal) or " +
                                 "(r.station2 =:start and r.station3 =:terminal)",
                                   Route.class
        )
                .setParameter("start", startStation)
                .setParameter("terminal",terminalStation)
                .setMaxResults(100)
                .getResultList();
        entityManager.close();

        return routes;
    }
    public  List <Route> getRoute_StartTerminalStationDeparture (String startStation, String terminalStation, LocalDateTime departure){
        EntityManager entityManager = sessionFactory.createEntityManager();
        entityManager.getTransaction().begin();
        //select first 100 row
        List <Route> routes =  entityManager.createQuery(
                "select r " +
                        "from route r " +
                        "where ((r.station1 =:start and r.station2 =:terminal) or " +
                               "(r.station1 =:start and r.station3 =:terminal) or " +
                               "(r.station2 =:start and r.station3 =:terminal)) and " +
                               "(r.arrivalStation1 <=: departure) ",
                Route.class
        )
                .setParameter("start", startStation)
                .setParameter("terminal",terminalStation)
                .setParameter("departure",departure)
                .setMaxResults(100)
                .getResultList();
        entityManager.close();

        return routes;
    }

}



