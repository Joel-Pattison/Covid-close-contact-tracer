package model;

import java.time.LocalDate;
import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class contactDatabaseInterface {
	private ArrayList<contact> queryResult = new ArrayList<contact>();
	
    private static final EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence.createEntityManagerFactory("FinalProject");
    
    public void addContact(contact contact) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction et = null;
        
        try {
            et = em.getTransaction();
            et.begin();
            em.persist(contact);
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
	public ArrayList<contact> queryAll() {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        Query query = em.createQuery("FROM contact");
        queryResult = (ArrayList<contact>) query.getResultList();
        //List<person> resultList = query.getResultList();
        em.close();
		return queryResult;
    }
	
	public ArrayList<contact> queryGetSinceDate(LocalDate date) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        Query query = em.createQuery("FROM contact C WHERE C.date > :date");
        query.setParameter("date", date);
        queryResult = (ArrayList<contact>) query.getResultList();
        //List<person> resultList = query.getResultList();
        em.close();
		return queryResult;
	}
	
	public void removeAllWithId(int ID) {
		EntityTransaction et = null;
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        Query query = em.createQuery("DELETE FROM contact c WHERE c.contact1id = :ID OR person2 = :ID");
        query.setParameter("ID", ID);
        et = em.getTransaction();
        et.begin();
        query.executeUpdate();
        et.commit();
        em.close();
    }
}
