package com.ecard.core.dao.impl;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.ecard.core.dao.IGenericDao;
@Repository
public abstract class GenericDao<T extends Serializable> {

	@PersistenceContext
	private EntityManager entityManager;
	protected EntityManager getEntityManager() {
		return entityManager;
	}
	

	public void persist(Object entity) {
		getEntityManager().persist(entity);
	}

	public void delete(Object entity) {
		entityManager.remove(entity);
	}

    public void saveOrUpdate(Object entity) {
        entityManager.merge(entity);
    }
    
    /**
     * GetOrNull
     * @param query
     * @return
     */
    protected Object getOrNull(Query query) {
    	List<Object> results = query.getResultList();
        if (results.isEmpty()) {
        	return null;
        } else if (results.size() == 1) {
        	return results.get(0);
        }
        throw new NonUniqueResultException();
    }
    
    @SuppressWarnings("unchecked")
	public List< T > findAll(Class clazz){
        return entityManager.createQuery("from " + clazz.getName()) .getResultList();
     }
    
}
