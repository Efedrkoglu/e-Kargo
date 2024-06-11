/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.SystemUserDAO;
import entity.SystemUser;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Efe
 */

@Named
@SessionScoped
public class UsersBean extends baseController<SystemUser> implements Serializable, IController<SystemUser> {
    
    @EJB
    private SystemUserDAO dao;
    
    @Override
    public SystemUser newEntity() {
        return new SystemUser();
    }

    @Override
    public ArrayList<SystemUser> select() {
        return this.dao.getList();
    }

    @Override
    public void insert() {
        this.dao.insert(this.entity);
        super.clearForm();
    }

    @Override
    public void update() {
        this.dao.update(this.entity);
        super.clearForm();
    }

    @Override
    public void delete(SystemUser entity) {
        this.dao.delete(entity);
        super.clearForm();
    }
    
}
