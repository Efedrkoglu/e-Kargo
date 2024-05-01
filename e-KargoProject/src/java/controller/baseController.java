/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.util.ArrayList;

/**
 *
 * @author Efe
 */
public abstract class baseController <E, D>{
    
    private E entity;
    private D dao;
    private ArrayList<E> list;
    
    public abstract E newEntity();
    public abstract D newDAO();

    public E getEntity() {
        if(this.entity == null) {
            this.entity = newEntity();
        }
        return entity;
    }

    public void setEntity(E entity) {
        this.entity = entity;
    }

    public D getDao() {
        if(this.dao == null) {
            this.dao = newDAO();
        }
        return dao;
    }

    public void setDao(D dao) {
        this.dao = dao;
    }  
    
    public ArrayList<E> getList() {
        return this.list;
    }
    
    public void setList(ArrayList<E> list) {
        this.list = list;
    }
}
