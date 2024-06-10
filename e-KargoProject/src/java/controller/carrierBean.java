/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import entity.carrier;
import dao.carrierDAO;
import jakarta.ejb.EJB;
import java.io.Serializable;
import java.util.ArrayList;
/**
 *
 * @author Efe
 */

@Named (value = "carrierBean")
@SessionScoped
public class carrierBean extends baseController<carrier> implements Serializable, IController<carrier> {
    
    @EJB
    private carrierDAO dao;
    
    private int page;
    
    public carrierBean() {
        
    }

    public carrierDAO getDao() {
        return dao;
    }

    public void setDao(carrierDAO dao) {
        this.dao = dao;
    }
    
    @Override
    public carrier newEntity() {
        return new carrier();
    }

    @Override
    public ArrayList<carrier> select() {
        super.setList(this.dao.getList());
        return super.getList();
    }

    @Override
    public void insert() {
        this.dao.insert(entity);
        super.clearForm();
    }

    @Override
    public void update() {
        this.dao.update(entity);
        super.clearForm();
    }

    @Override
    public void delete(carrier entity) {
        this.dao.delete(entity);
        super.clearForm();
    }
    
    public ArrayList<carrier> page() {
        return this.dao.getPage(this.getPage());
    }
    
    public int getPage() {
        if(this.page == 0)
            this.page = 1;
        
        return this.page;
    }
    
    public void setPage(int page) {
        if(page <= 0)
            page = 1;
        else if(page > this.dao.maxPage())
            page = this.dao.maxPage();
        
        this.page = page;
    }
}
