package net.example.backend.dao;

import java.util.List;

/**
 *
 * @author andre
 */
public interface AbstractDao<E>{
    
    public List<E> fetchAll();
    
    public E save(E entity);
    
}
