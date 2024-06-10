/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import entity.location;
import dao.locationDAO;
import jakarta.ejb.EJB;
import java.io.Serializable;
import java.util.ArrayList;
/**
 *
 * @author Efe
 */

@Named (value = "locationBean")
@SessionScoped
public class locationBean extends baseController<location> implements Serializable, IController<location> {
    
    @EJB
    private locationDAO dao;
    
    private int page;
    
    public locationBean() {
        
    }

    public locationDAO getDao() {
        return dao;
    }

    public void setDao(locationDAO dao) {
        this.dao = dao;
    }
    
    @Override
    public location newEntity() {
        return new location();
    }

    @Override
    public ArrayList<location> select() {
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
    public void delete(location entity) {
        this.dao.delete(entity);
        super.clearForm();
    }
    
    public ArrayList<location> page() {
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
