/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import entity.employee;
import entity.warehouse;
import util.DbConnection;
import java.sql.*;
import java.util.ArrayList;
/**
 *
 * @author Efe
 */
public class employeeDAO extends DbConnection{
    
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
    
    public void insert(employee e) {
        try {
            Statement st = super.connect().createStatement();
            st.executeUpdate("insert into employee values(default, " + e.getWarehouse().getWarehouse_id()
                            + ", '" + e.getPhone_number() + "', '" + e.getEmail() + "', '" + e.getName() + "')");
        }
        catch(Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public void update(employee e) {
        try {
            Statement st = super.connect().createStatement();
            st.executeUpdate("update employee set warehouse_id=" + e.getWarehouse().getWarehouse_id() + ", "
                            + "phone_number='" + e.getPhone_number() + "', email='" + e.getEmail() + "', "
                            + "name='" + e.getName() + "' where employee_id=" + e.getEmployee_id());
        }
        catch(Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public void delete(employee e) {
        try {
            Statement st = super.connect().createStatement();
            st.executeUpdate("delete from employee where employee_id=" + e.getEmployee_id());
        }
        catch(Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public ArrayList<employee> getList() {
        ArrayList<employee> list = new ArrayList<>();
        
        try {
            Statement st = super.connect().createStatement();
            ResultSet rs = st.executeQuery("select * from employee order by employee_id");
            
            while(rs.next()) {
                warehouse w = this.getwDao().getById(rs.getInt("warehouse_id"));
                list.add(new employee(rs.getInt("employee_id"), w, rs.getString("phone_number"), rs.getString("email"), rs.getString("name")));
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
            ResultSet rs = st.executeQuery("select count(employee_id) as c from employee");
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
            ResultSet rs = st.executeQuery("select * from employee order by employee_id offset " + ((page - 1) * 10) + " limit 10");
            
            while(rs.next()) {
                warehouse w = this.getwDao().getById(rs.getInt("warehouse_id"));
                list.add(new employee(rs.getInt("employee_id"), w, rs.getString("phone_number"), rs.getString("email"), rs.getString("name")));
            }
        }
        catch(Exception ex) {
            System.out.println(ex.getMessage());
        }
        
        return list;
    }
}
