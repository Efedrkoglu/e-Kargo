/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import entity.location;
import dao.locationDAO;
import java.io.Serializable;
import java.util.ArrayList;
/**
 *
 * @author Efe
 */

@Named (value = "locationBean")
@SessionScoped
public class locationBean extends baseController<location, locationDAO> implements Serializable, IController<location> {

    private int page;
    
    public locationBean() {
        
    }
    
    @Override
    public location newEntity() {
        return new location();
    }

    @Override
    public locationDAO newDAO() {
        return new locationDAO();
    }

    @Override
    public ArrayList<location> select() {
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
    public void delete(location entity) {
        super.getDao().delete(entity);
        super.setEntity(newEntity());
    }
    
    public ArrayList<location> page() {
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
