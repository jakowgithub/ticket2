package dao;

import org.hibernate.SessionFactory;

import javax.persistence.EntityManager;
import java.io.File;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    //  return HashSet<>() classes in current package Class.out.production.classes.entity
    //  with  annotation @Entity or @Table
    public  <T> Set<Class<?>> getClassesInPackage () {

// https://github.com/ronmamo/reflections
        Set<Class<?>> classes = new HashSet<>();

        String directoryString = "/"+
                                 System.getProperty("user.dir") .replace("\\","/")+
                                 "/out/production/classes/entity";

        File directory = new File(directoryString);

        if (directory.exists()) {
// Get the list of the files contained in the package
            String[] files = directory.list();
            for (String fileName : files) {
// We are only interested in .class files
                if (fileName.endsWith(".class")) {
// Remove the .class extension
                    fileName = fileName.substring(0, fileName.length() - 6);
                    try {classes.add(Class.forName("entity."+fileName));}
                    catch (ClassNotFoundException e) {System.out.println("entity." + fileName + " does not appear to be a valid class.");}
                }}}
        else {System.out.println(directoryString + " does not appear to exist as a valid package on the file system.");}

        Set<Class<?>> classesAnnotated = new HashSet<>();

        for (Class<?> clazz : classes) {

            if(clazz.getAnnotations().length > 1) {
                String anottationName0 = clazz.getAnnotations()[0].toString();
                String anottationName1 = clazz.getAnnotations()[1].toString();

                if (!clazz.isInterface()                &&
                    !clazz.isAnnotation()               &&
                    !clazz.isEnum()                     &&
                    (anottationName0.contains("Entity") ||
                    (anottationName1.contains("Table")))) {

                    classesAnnotated.add(clazz);
                } } }
        return classesAnnotated;
    }

}
