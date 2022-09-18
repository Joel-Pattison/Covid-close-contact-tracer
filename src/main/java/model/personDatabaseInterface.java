package model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

public class personDatabaseInterface {
	private ArrayList<person> queryResult = new ArrayList<person>();
	
    private static final EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence.createEntityManagerFactory("FinalProject");
    
    public void add(person person) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction et = null;
        
        try {
            et = em.getTransaction();
            et.begin();
            em.persist(person);
            et.commit();
        } catch (Exception ex) {
            if (et != null) {
                et.rollback();
            }
            ex.printStackTrace();
        } finally {
            em.close();
        }
    }
    
    public void remove(int id) {
    	EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction et = null;
        person person = null;
 
        try {
            et = em.getTransaction();
            et.begin();
            person = em.find(person.class, id);
            em.remove(person);
            et.commit();
        } catch (Exception ex) {
            if (et != null) {
                et.rollback();
            }
            ex.printStackTrace();
        } finally {
            em.close();
        }
    }
    
    @SuppressWarnings("unchecked")
	public ArrayList<person> queryAll() {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        Query query = em.createQuery("FROM person");
        queryResult = (ArrayList<person>) query.getResultList();
        //List<person> resultList = query.getResultList();
        em.close();
		return queryResult;
    }
	
    public static person getPersonByID(int id) {
    	EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
    	
    	String query = "SELECT p FROM person p WHERE p.personId = :persID";
    	
    	TypedQuery<person> tq = em.createQuery(query, person.class);
    	tq.setParameter("persID", id);
    	
    	person tempPerson = null;
    	try {
    		tempPerson = tq.getSingleResult();
    	}
    	catch(NoResultException ex) {
    		ex.printStackTrace();
    	}
    	finally {
    		em.close();
    	}
    	
    	return tempPerson;
    }
    
    public static person getPersonByName(String name) {
    	EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
    	
    	String query = "SELECT p FROM person p WHERE p.name.firstName = :persName";
    	
    	TypedQuery<person> tq = em.createQuery(query, person.class);
    	tq.setParameter("persName", name);
    	
    	person tempPerson = null;
    	try {
    		tempPerson = tq.getSingleResult();
    	}
    	catch(NoResultException ex) {
    		ex.printStackTrace();
    	}
    	finally {
    		em.close();
    	}
    	
    	return tempPerson;
    }
}
