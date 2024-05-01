/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import entity.Package;
import dao.packageDAO;
import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Efe
 */

@Named (value = "packageBean")
@SessionScoped
public class packageBean extends baseController<Package, packageDAO> implements Serializable, IController<Package> {
    
    packageBean() {
        
    }
    
    @Override
    public Package newEntity() {
        return new Package();
    }

    @Override
    public packageDAO newDAO() {
        return new packageDAO();
    }

    @Override
    public ArrayList<Package> select() {
        super.setList(super.getDao().getList());
        return super.getList();
    }

    @Override
    public void insert(Package entity) {
        super.getDao().insert(entity);
        super.setEntity(newEntity());
    }

    @Override
    public void update(Package entity) {
        super.getDao().update(entity);
        super.setEntity(newEntity());
    }

    @Override
    public void delete(Package entity) {
        super.getDao().delete(entity);
        super.setEntity(newEntity());
    }
}
