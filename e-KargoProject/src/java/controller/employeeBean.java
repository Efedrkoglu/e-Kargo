/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import entity.employee;
import dao.employeeDAO;
import java.io.Serializable;
import java.util.ArrayList;
/**
 *
 * @author Efe
 */

@Named (value = "employeeBean")
@SessionScoped
public class employeeBean extends baseController<employee, employeeDAO> implements Serializable, IController<employee> {
    
    public employeeBean() {
        
    }
    
    @Override
    public employee newEntity() {
        return new employee();
    }

    @Override
    public employeeDAO newDAO() {
        return new employeeDAO();
    }

    @Override
    public ArrayList<employee> select() {
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
    public void delete(employee entity) {
        super.getDao().delete(entity);
        super.setEntity(newEntity());
    }
}
