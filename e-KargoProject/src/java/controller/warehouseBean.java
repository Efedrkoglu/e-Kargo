/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import entity.warehouse;
import dao.warehouseDAO;
import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Efe
 */

@Named (value = "warehouseBean")
@SessionScoped
public class warehouseBean extends baseController<warehouse, warehouseDAO> implements Serializable, IController<warehouse> {
    
    public warehouseBean() {
        
    }
    
    @Override
    public warehouse newEntity() {
        return new warehouse();
    }

    @Override
    public warehouseDAO newDAO() {
        return new warehouseDAO();
    }

    @Override
    public ArrayList<warehouse> select() {
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
    public void delete(warehouse entity) {
        super.getDao().delete(entity);
        super.setEntity(newEntity());
    }
    
}
