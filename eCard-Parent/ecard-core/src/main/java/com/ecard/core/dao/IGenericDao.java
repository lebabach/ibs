package com.ecard.core.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.NonUniqueResultException;
import javax.persistence.Query;

public interface  IGenericDao<T extends Serializable> {
	public void persist(Object entity) ;

	public void delete(Object entity) ;

    public void saveOrUpdate(Object entity) ;
    
    public List<T> findAll(Class clazz) ;
}
