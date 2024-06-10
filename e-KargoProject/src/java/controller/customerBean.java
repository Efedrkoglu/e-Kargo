/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.customerDAO;
import entity.customer;
import jakarta.ejb.EJB;
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
public class customerBean extends baseController<customer> implements Serializable, IController<customer> {
    
    @EJB
    private customerDAO dao;
    
    private int page;
    
    public customerBean() {
        
    }

    public customerDAO getDao() {
        return dao;
    }

    public void setDao(customerDAO dao) {
        this.dao = dao;
    }

    @Override
    public customer newEntity() {
        return new customer();
    }
    
    @Override
    public ArrayList<customer> select() {
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
    public void delete(customer entity) {
        this.dao.delete(entity);
        super.clearForm();
    }

    public ArrayList<customer> page() {
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
