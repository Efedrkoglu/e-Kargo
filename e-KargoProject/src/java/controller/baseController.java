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
public abstract class baseController <E>{
    
    protected E entity;
    protected ArrayList<E> list;
    
    private int formKontrol = 0;
    
    public abstract E newEntity();

    public E getEntity() {
        if(this.entity == null) {
            this.entity = newEntity();
        }
        return entity;
    }

    public void setEntity(E entity) {
        this.entity = entity;
    } 
    
    public ArrayList<E> getList() {
        return this.list;
    }
    
    public void setList(ArrayList<E> list) {
        this.list = list;
    }
    
    public int getFormKontrol() {
        return this.formKontrol;
    }
    
    public void clearForm() {
        this.formKontrol = 0;
        setEntity(newEntity());
    }
    
    public void setForm(E entity) {
        this.formKontrol = 1;
        setEntity(entity);
    }
}
