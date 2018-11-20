package dao;

import org.hibernate.SessionFactory;

import javax.persistence.EntityManager;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

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
        String sqlRequest = "select t from "+nameTable+" t where t."+idTable+"<1000000";

        //select all row with id<1000000
        List <T> classes = (List <T>) entityManager.createQuery(sqlRequest, clazz).setMaxResults(1000000).getResultList();

        entityManager.close();
        return classes;
    }

    public  <T> void remove(T t){
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

    //  delete all data from table, which mapped out
    //  class with  annotation @Entity and/or @Table
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

                try { classes.add(Class.forName(nameFile)); }

                catch (ClassNotFoundException e) {System.out.println( nameFile + " does not appear to be a valid class.");}
            });
            } catch (IOException ioe){System.out.println("IOException"); ioe.printStackTrace();}

        Set<Class<?>> classesAnnotated = new HashSet<>();

        for (Class<?> clazz : classes) {

            if(clazz.getAnnotations().length > 1) {
                String anottationName0 = clazz.getAnnotations()[0].toString();
                String anottationName1 = clazz.getAnnotations()[1].toString();

                if (!clazz.isInterface()                    &&
                        !clazz.isAnnotation()               &&
                        !clazz.isEnum()                     &&
                        (anottationName0.contains("Entity") ||
                        (anottationName1.contains("Table")))) {

                    classesAnnotated.add(clazz);
                } } }

        EntityManager entityManager = sessionFactory.createEntityManager();

        for(Class<?> clazz : classesAnnotated) {

            entityManager.getTransaction().begin();
            //get string for hql request
            String nameClass = clazz.toString().substring(clazz.toString().indexOf(".") + 1);
            String nameTable = nameClass.toLowerCase();

            String sqlRequest = "delete from " + nameTable;
            entityManager.createQuery(sqlRequest).executeUpdate();
            entityManager.getTransaction().commit();
        }
        entityManager.close();
    }

}

