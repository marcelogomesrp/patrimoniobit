package br.com.guaraba.controlepatrimonio;

import br.com.guaraba.controlepatrimonio.instituicao.Instituicao;
import br.com.guaraba.controlepatrimonio.instituicao.InstituicaoDao;
import br.com.guaraba.controlepatrimonio.patrimonio.Patrimonio;
import java.io.File;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JOptionPane;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.FileSystemResource;

/**
 * Hello world!
 *
 */
public class App 
{
    static Logger logger = Logger.getLogger(App.class);

    public static void main( String[] args )
    {
        
        PropertyConfigurator.configure("log4j.properties");
        logger.info("?Inicializando...?");
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



//        File file = new File("applicationContext.xml");
//        FileSystemResource fileResource = new FileSystemResource( file );
//        XmlBeanFactory mFactory = new XmlBeanFactory( fileResource );
//

        
        
//        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        ApplicationContext applicationContext   = new ClassPathXmlApplicationContext("applicationContext.xml");

        //ApplicationContext applicationContext = new ClassPathXmlApplicationContext(file.getAbsolutePath());




        //InstituicaoDao instituicaoDao = (InstituicaoDao) mFactory.getBean("instituicaoDao");
        InstituicaoDao instituicaoDao = (InstituicaoDao) applicationContext.getBean("instituicaoDao");

        Instituicao spring = new Instituicao("spring", "spring");
        //EntityManagerFactory emf = (EntityManagerFactory) mFactory.getBean("entityManagerFactory");


        instituicaoDao.salvar(spring);

       // System.out.println("Path: " + fileResource.getPath());
        
//        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
//        InstituicaoDao instituicaoDao = (InstituicaoDao) context.getBean("instituicaoDao");
        //org.apache.commons.logging.

        
    }

    public static void persist(Object object, EntityManagerFactory emf) {        
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
