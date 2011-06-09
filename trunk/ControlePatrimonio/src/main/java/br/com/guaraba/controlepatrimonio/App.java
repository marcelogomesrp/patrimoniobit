package br.com.guaraba.controlepatrimonio;

import br.com.guaraba.controlepatrimonio.patrimonio.Patrimonio;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        Patrimonio pt = new Patrimonio();
        pt.setNome("teste5");
        App.persist(pt);

        //org.slf4j.impl.StaticLoggerBinder

    }

    public static void persist(Object object) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("PatrimonioPU");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        try {
            em.persist(object);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }
}
