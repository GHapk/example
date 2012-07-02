package net.example.backend.dao;

import java.lang.reflect.Method;
import java.util.List;
import net.example.backend.annotation.Dao;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Class for default sql methods every database object needs to have
 * entities which uses this class needs to have primary key id
 * 
 * @author andre
 */
public class AbstractDaoImpl<E> implements AbstractDao<E> {

    /* Hibernate session factory */
    protected SessionFactory sessionFactory;
    @SuppressWarnings("rawtypes")
    private Class entityClass;
    private String tableName;

    /* inject the hibernate session factory */
    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    /**
     * Read the entity class from the annotation
     * 
     * @return Class 
     */
    private Class getEntityClass() {
        if (this.entityClass == null) {
            this.entityClass = ((Dao) this.getClass().getAnnotation(
                    Dao.class)).entityClass();
        }
        return this.entityClass;
    }

    /**
     * 
     * @return database table name
     */
    @SuppressWarnings("unchecked")
    protected String getRawTableName() {
        if (this.tableName == null) {
            this.tableName = ((javax.persistence.Table) this.getEntityClass().getAnnotation(javax.persistence.Table.class)).name();
        }
        return this.tableName;
    }

    /**
     * 
     * @return hibernate table name (Class name)
     */
    protected String getHibernateTableName() {
        return this.getEntityClass().getSimpleName();
    }

    /**
     * Default save function
     * 
     * @param entity entity that should be saved
     * @return 
     */
    @Override
    public E save(E entity) {
        try {
            Method getId = this.getEntityClass().getMethod("getId");
            Integer id = (Integer) getId.invoke(entity, (Object[]) null);
            if(id == null){
                this.sessionFactory.getCurrentSession().save(entity);
            }else{
                this.sessionFactory.getCurrentSession().update(entity);
            }
            return entity;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    /**
     * Reads all database entries for this entity
     * 
     * @return java.util.List collected database entries
     */
    @Override
    public List<E> fetchAll(){
        try{
        return (List<E>) this.sessionFactory.getCurrentSession().createQuery("from "
				+ this.getHibernateTableName()).list();
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
