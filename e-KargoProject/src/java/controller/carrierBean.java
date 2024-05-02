/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import entity.carrier;
import dao.carrierDAO;
import java.io.Serializable;
import java.util.ArrayList;
/**
 *
 * @author Efe
 */

@Named (value = "carrierBean")
@SessionScoped
public class carrierBean extends baseController<carrier, carrierDAO> implements Serializable, IController<carrier> {
    
    public carrierBean() {
        
    }
    
    @Override
    public carrier newEntity() {
        return new carrier();
    }

    @Override
    public carrierDAO newDAO() {
        return new carrierDAO();
    }

    @Override
    public ArrayList<carrier> select() {
        super.setList(super.getDao().getList());
        return super.getList();
    }

    @Override
    public void insert() {
        super.getDao().insert(entity);
        super.setEntity(newEntity());
    }

    @Override
    public void update() {
        super.getDao().update(entity);
        super.setEntity(newEntity());
    }

    @Override
    public void delete(carrier entity) {
        super.getDao().delete(entity);
        super.setEntity(newEntity());
    }
}
