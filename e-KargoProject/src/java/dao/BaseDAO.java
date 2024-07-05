/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import jakarta.ejb.Local;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.io.Serializable;
import java.util.ArrayList;
import util.DbConnection;

/**
 *
 * @author Efe
 */

@Local
@Stateless
public abstract class BaseDAO<E> extends DbConnection implements Serializable {
    
    @PersistenceContext(unitName = "e-KargoProjectPU")
    protected EntityManager em;
    
    public void insert(E entity) {
        this.em.persist(entity);
    }
    
    public void update(E entity) {
        this.em.merge(entity);
    }
    
    public void delete(E entity) {
        this.em.remove(this.em.merge(entity));
        this.em.flush();
    }
    
    public abstract ArrayList<E> getList();
    public abstract E getById(int id);
}
