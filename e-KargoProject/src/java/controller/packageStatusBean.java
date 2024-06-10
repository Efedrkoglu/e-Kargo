/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import java.io.Serializable;
import entity.packageStatus;
import dao.packageStatusDAO;
import jakarta.ejb.EJB;
import java.util.ArrayList;

/**
 *
 * @author Efe
 */

@Named (value = "packageStatusBean")
@SessionScoped
public class packageStatusBean extends baseController<packageStatus> implements Serializable, IController<packageStatus>{
    
    @EJB
    private packageStatusDAO dao;
    
    private int page;
    
    public packageStatusBean() {
        
    }

    public packageStatusDAO getDao() {
        return dao;
    }

    public void setDao(packageStatusDAO dao) {
        this.dao = dao;
    }

    @Override
    public packageStatus newEntity() {
        return new packageStatus();
    }
    
    @Override
    public ArrayList<packageStatus> select() {
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
    public void delete(packageStatus entity) {
        this.dao.delete(entity);
        super.clearForm();
    }
    
    public ArrayList<packageStatus> page() {
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
