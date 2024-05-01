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
import java.util.ArrayList;

/**
 *
 * @author Efe
 */

@Named (value = "packageStatusBean")
@SessionScoped
public class packageStatusBean extends baseController<packageStatus, packageStatusDAO> implements Serializable, IController<packageStatus>{

    packageStatusBean() {
        
    }

    @Override
    public packageStatus newEntity() {
        return new packageStatus();
    }

    @Override
    public packageStatusDAO newDAO() {
        return new packageStatusDAO();
    }
    
    @Override
    public ArrayList<packageStatus> select() {
        super.setList(super.getDao().getList());
        return super.getList();
    }

    @Override
    public void insert(packageStatus entity) {
        super.getDao().insert(entity);
        super.setEntity(newEntity());
    }

    @Override
    public void update(packageStatus entity) {
        super.getDao().update(entity);
        super.setEntity(newEntity());
    }

    @Override
    public void delete(packageStatus entity) {
        super.getDao().delete(entity);
        super.setEntity(newEntity());
    }

}
