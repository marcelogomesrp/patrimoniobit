package br.com.guaraba.controlepatrimonio;

import br.com.guaraba.controlepatrimonio.instituicao.Instituicao;
import br.com.guaraba.controlepatrimonio.patrimonio.Patrimonio;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JOptionPane;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        Instituicao hemocentro = new Instituicao("Hemocentro de Ribeirão Preto", "HEMO");
        Instituicao usp = new Instituicao("Universidade de São Paulo", "USP");
        Instituicao hc = new Instituicao("Hospital das clinicas de Ribeirão Preto", "HCRP");
        App.persist(hemocentro);
        App.persist(usp);
        App.persist(hc);
        Patrimonio pt = new Patrimonio();
        pt.setNome("teste5");
        //App.persist(pt);

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
            JOptionPane.showMessageDialog(null, "Erro ao persistir objeto " + e.getMessage());
        } finally {
            em.close();
        }
    }
}
