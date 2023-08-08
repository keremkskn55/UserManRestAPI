package com.kerem.userman.dao.impl;

import java.util.List;

import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.transaction.Transactional;

import com.kerem.userman.dao.UserDao;
import com.kerem.userman.model.User;

@Named("userDaoImpl")
public class UserDaoImpl implements UserDao{
	
	private final EntityManagerFactory entityManagerFactory;

    public UserDaoImpl() {
    	entityManagerFactory = Persistence.createEntityManagerFactory("userManagement");
    }
    
    public User findById(int id) {
		EntityManager em = entityManagerFactory.createEntityManager();
		User entity = em.find(User.class, id);
		em.close();
		return entity;
    }
    
    public boolean add(User entity) {
    	EntityManager em = entityManagerFactory.createEntityManager();
    	EntityTransaction transaction = em.getTransaction();
    	
    	boolean isSuccesed = false;
    	
    	try {
    		transaction.begin();
            em.persist(entity);
            transaction.commit();
            isSuccesed = true;
        } catch (Exception e) {
        	if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
        }
    	finally {
        	em.close();
        }
    	return isSuccesed;
    }
    
    public List<User> findAll() {
        EntityManager em = entityManagerFactory.createEntityManager();
        List<User> users = em.createQuery("FROM " + User.class.getSimpleName(), User.class).getResultList();
        em.close();
        return users;
    }
    
    public boolean update(User entity) {
        EntityManager em = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        
        boolean isSuccesed = false;
        try {
        	transaction.begin();
            em.merge(entity);
            transaction.commit();
            isSuccesed = true;
           
        } catch (Exception e) {
        	System.out.println(e.getMessage());
        } finally {
            em.close();
        }
        return isSuccesed;
    }
    
    public boolean deleteById(int id) {
        EntityManager em = entityManagerFactory.createEntityManager();
    	EntityTransaction transaction = em.getTransaction();

        
        boolean isSuccesed = false;
        try {
        	
        	User entity = em.find(User.class, id);
            if (entity != null) {
            	transaction.begin();
                em.remove(entity);
                transaction.commit();
                isSuccesed = true;
            }

        } catch (Exception e) {
        	System.out.println(e.getMessage());
        	if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
        } finally {
            em.close();
        }
        
        return isSuccesed;
    }
}