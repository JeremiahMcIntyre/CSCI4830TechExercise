/**
 */
package util;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import datamodel.SalesContact;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * @since JavaSE-1.8
 */
public class UtilDB {
   static SessionFactory sessionFactory = null;

   public static SessionFactory getSessionFactory() {
      if (sessionFactory != null) {
         return sessionFactory;
      }
      Configuration configuration = new Configuration().configure();
      StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
      sessionFactory = configuration.buildSessionFactory(builder.build());
      return sessionFactory;
   }

   public static List<SalesContact> listSalesContact() {
      List<SalesContact> resultList = new ArrayList<SalesContact>();

      Session session = getSessionFactory().openSession();
      Transaction tx = null;  // each process needs transaction and commit the changes in DB.

      try {
         tx = session.beginTransaction();
         List<?> salesContacts = session.createQuery("FROM SalesContact").list();
         for (Iterator<?> iterator = salesContacts.iterator(); iterator.hasNext();) {
            SalesContact salesContact = (SalesContact) iterator.next();
            resultList.add(salesContact);
         }
         tx.commit();
      } catch (HibernateException e) {
         if (tx != null)
            tx.rollback();
         e.printStackTrace();
      } finally {
         session.close();
      }
      return resultList;
   }

   public static List<SalesContact> listSalesContact(String searchBy, String searchValue) {
	    List<SalesContact> resultList = new ArrayList<>();

	    Session session = getSessionFactory().openSession();
	    Transaction tx = null;

	    try {
	        tx = session.beginTransaction();
	        List<?> salesContacts;
	        if ("areaCode".equals(searchBy)) {
	            // Retrieve sales contacts where the first 3 characters of the phone number match the keyword
	            Query query = session.createQuery("FROM SalesContact WHERE SUBSTRING(phoneNumber, 1, 3) = :keyword");
	            query.setParameter("keyword", searchValue);
	            salesContacts = query.list();
	        } else if ("zipCode".equals(searchBy)) {
	            // Retrieve sales contacts where the last 5 characters of the address match the keyword
	            Query query = session.createQuery("FROM SalesContact WHERE SUBSTRING(address, LENGTH(address) - 4) = :keyword");
	            query.setParameter("keyword", searchValue);
	            salesContacts = query.list();
	        } else {
	            // If searchBy is neither areaCode nor zipCode, return an empty list
	            salesContacts = new ArrayList<>();
	        }
	        for (Iterator<?> iterator = salesContacts.iterator(); iterator.hasNext();) {
	            SalesContact salesContact = (SalesContact) iterator.next();
	            resultList.add(salesContact);
	        }
	        tx.commit();
	    } catch (HibernateException e) {
	        if (tx != null)
	            tx.rollback();
	        e.printStackTrace();
	    } finally {
	        session.close();
	    }
	    return resultList;
	}

   public static void createSalesContacts(String name, String address, String phoneNumber, String email) {
      Session session = getSessionFactory().openSession();
      Transaction tx = null;
      try {
         tx = session.beginTransaction();
         session.save(new SalesContact(name, address, phoneNumber, email));
         tx.commit();
      } catch (HibernateException e) {
         if (tx != null)
            tx.rollback();
         e.printStackTrace();
      } finally {
         session.close();
      }
   }
}
