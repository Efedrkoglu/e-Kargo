/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import entity.employee;
import entity.warehouse;
import entity.location;
import util.DbConnection;
import java.sql.*;
import java.util.ArrayList;
/**
 *
 * @author Efe
 */
public class employeeDAO extends DbConnection{
    
    public void insert(employee e) {
        try {
            Statement st = super.connect().createStatement();
            st.executeUpdate("insert into employee values(" + e.getEmployee_id() + ", " + e.getWarehouse().getWarehouse_id()
                             + ", '" + e.getPhone_number() + "', '" + e.getEmail() + "', '" + e.getName() + "')");
        }
        catch(Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
   
    public void update(employee e) {
        try {
            Statement st = super.connect().createStatement();
            st.executeUpdate("update employee set warehouse_id=" + e.getWarehouse().getWarehouse_id() + ", phone_number='"
                             + e.getPhone_number() + "', email='" + e.getEmail() + "', name='" + e.getName() + "'"
                             + " where employee_id=" + e.getEmployee_id());
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
            ResultSet rs = st.executeQuery("");
        }
        catch(Exception ex) {
            System.out.println(ex.getMessage());
        }
        
        return list;
    }
}
