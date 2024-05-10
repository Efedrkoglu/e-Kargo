/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.customerDAO;
import entity.customer;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Efe
 */

@Named (value = "customerBean")
@SessionScoped
public class customerBean extends baseController<customer, customerDAO> implements Serializable, IController<customer> {
      
    private int page;
    
    public customerBean() {
        
    }

    @Override
    public customer newEntity() {
        return new customer();
    }

    @Override
    public customerDAO newDAO() {
        return new customerDAO();
    }
    
    @Override
    public ArrayList<customer> select() {
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
    public void delete(customer entity) {
        System.out.println("sa");
        super.getDao().delete(entity);
        super.setEntity(newEntity());
    }

    public ArrayList<customer> page() {
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
