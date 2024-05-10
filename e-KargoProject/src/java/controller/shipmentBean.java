/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import entity.shipment;
import dao.shipmentDAO;
import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Efe
 */

@Named (value = "shipmentBean")
@SessionScoped
public class shipmentBean extends baseController<shipment, shipmentDAO> implements Serializable, IController<shipment> {
    
    private int page;
    
    public shipmentBean() {
        
    }
    
    @Override
    public shipment newEntity() {
        return new shipment();
    }

    @Override
    public shipmentDAO newDAO() {
        return new shipmentDAO();
    }

    @Override
    public ArrayList<shipment> select() {
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
    public void delete(shipment entity) {
        super.getDao().delete(entity);
        super.setEntity(newEntity());
    }
    
    public ArrayList<shipment> page() {
        return super.getDao().getPage(this.getPage());
    }
    
    public int getPage() {
        if(this.page == 0)
            this.page = 1;
        
        return this.page;
    }
    
    public void setPage(int page) {
        if(page <= 0)
            page = 1;
        else if(page > this.getDao().maxPage())
            page = this.getDao().maxPage();
        
        this.page = page;
    }
}
