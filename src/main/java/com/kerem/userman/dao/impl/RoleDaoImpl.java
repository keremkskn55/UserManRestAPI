package com.kerem.userman.dao.impl;

import java.util.List;

import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import com.kerem.userman.dao.RoleDao;
import com.kerem.userman.model.Role;
import com.kerem.userman.model.User;

@Named("roleDaoImpl")
public class RoleDaoImpl implements RoleDao{
	
	private final EntityManagerFactory entityManagerFactory;

    public RoleDaoImpl() {
    	entityManagerFactory = Persistence.createEntityManagerFactory("userManagement");
    }

    public boolean add(Role entity) {
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
    
    public Role findByName(String roleName) {
    	EntityManager em = entityManagerFactory.createEntityManager();
    	TypedQuery<Role> query = em.createNamedQuery(Role.FIND_BY_NAME, Role.class);
    	
    	query.setParameter("name", roleName);
    	
    	try {
    		return query.getSingleResult();
    	} catch (NoResultException e) {
    		return null;
    	}
    }

	@Override
	public Role findById(int id) {
		EntityManager em = entityManagerFactory.createEntityManager();
		Role entity = em.find(Role.class, id);
		em.close();
		return entity;
	}

	@Override
	public List<Role> findAll() {
        EntityManager em = entityManagerFactory.createEntityManager();
        List<Role> roles = em.createQuery("FROM " + Role.class.getSimpleName(), Role.class).getResultList();
        em.close();
        return roles;
	}

	@Override
	public boolean update(Role entity) {
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

	@Override
	public boolean deleteById(int id) {
        EntityManager em = entityManagerFactory.createEntityManager();
    	EntityTransaction transaction = em.getTransaction();

        
        boolean isSuccesed = false;
        try {
        	
        	Role entity = em.find(Role.class, id);
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
