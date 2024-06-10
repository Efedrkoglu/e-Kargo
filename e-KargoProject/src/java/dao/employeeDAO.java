/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import entity.employee;
import entity.warehouse;
import jakarta.ejb.Local;
import jakarta.ejb.Stateless;
import java.io.Serializable;
import util.DbConnection;
import java.sql.*;
import java.util.ArrayList;
/**
 *
 * @author Efe
 */

@Local
@Stateless
public class employeeDAO extends BaseDAO<employee> implements Serializable {
    
    private warehouseDAO wDao;

    public warehouseDAO getwDao() {
        if(this.wDao == null) {
            this.wDao = new warehouseDAO();
        }
        return wDao;
    }

    public void setwDao(warehouseDAO wDao) {
        this.wDao = wDao;
    }
    
    @Override
    public ArrayList<employee> getList() {
        ArrayList<employee> list = new ArrayList<>();
        
        try {
            Statement st = super.connect().createStatement();
            ResultSet rs = st.executeQuery("select * from employee order by id");
            
            while(rs.next()) {
                warehouse w = this.getwDao().getById(rs.getInt("warehouse_id"));
                list.add(new employee(rs.getInt("id"), w, rs.getString("phone_number"), rs.getString("email"), rs.getString("name")));
            }
        }
        catch(Exception ex) {
            System.out.println(ex.getMessage());
        }
        
        return list;
    }
    
    public int maxPage() {
        int maxPage = 1;
        try {
            Statement st = super.connect().createStatement();
            ResultSet rs = st.executeQuery("select count(id) as c from employee");
            rs.next();
            int count = rs.getInt("c");
            maxPage = (int)Math.ceil((double)count / (double)10);
        }
        catch(Exception ex) {
            System.out.println(ex.getMessage());
        }
        
        if(maxPage == 0)
            maxPage = 1;
        
        return maxPage;
    }
    
    public ArrayList<employee> getPage(int page) {
        ArrayList<employee> list = new ArrayList<>();
        
        try {
            Statement st = super.connect().createStatement();
            ResultSet rs = st.executeQuery("select * from employee order by id offset " + ((page - 1) * 10) + " limit 10");
            
            while(rs.next()) {
                warehouse w = this.getwDao().getById(rs.getInt("warehouse_id"));
                list.add(new employee(rs.getInt("id"), w, rs.getString("phone_number"), rs.getString("email"), rs.getString("name")));
            }
        }
        catch(Exception ex) {
            System.out.println(ex.getMessage());
        }
        
        return list;
    }

    @Override
    public employee getById(int id) {
        return null;
    }
}
