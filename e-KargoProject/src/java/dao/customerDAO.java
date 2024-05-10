/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import util.DbConnection;
import entity.customer;
import java.sql.*;
import java.util.ArrayList;
/**
 *
 * @author Efe
 */
public class customerDAO extends DbConnection{
    
    public customer getById(int id) {
        customer c = null;
        
        try {
            Statement st = super.connect().createStatement();
            ResultSet rs = st.executeQuery("select * from customer where customer_id=" + id);
            rs.next();
            
            c = new customer(rs.getInt("customer_id"), rs.getString("phone_number"), rs.getString("email"), rs.getString("name"));
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
        }
        
        return c;
    }
    
    public void insert(customer c) {
        try {
            Statement st = super.connect().createStatement();
            st.executeUpdate("insert into customer values(default, '" + c.getPhone_number()
                            + "', '" + c.getEmail() + "', '" + c.getName() + "')");
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void update(customer c) {
        try {
            Statement st = super.connect().createStatement();
            st.executeUpdate("update customer set phone_number='" + c.getPhone_number() + "', email='" + c.getEmail() 
                            + "', name='" + c.getName() + "' where customer_id=" + c.getCustomer_id());
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void delete(customer c) {
        try {
            Statement st = super.connect().createStatement();
            st.executeUpdate("delete from customer where customer_id=" + c.getCustomer_id());
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    public ArrayList<customer> getList() {
        ArrayList<customer> list = new ArrayList<>();
        
        try {
            Statement st = super.connect().createStatement();
            ResultSet rs = st.executeQuery("select * from customer order by customer_id");
            
            while(rs.next()) {
                list.add(new customer(rs.getInt("customer_id"), rs.getString("phone_number"),
                                rs.getString("email"), rs.getString("name")));
            }
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
        }
        
        return list;
    }
    
    public int maxPage() {
        int maxPage = 1;
        try {
            Statement st = super.connect().createStatement();
            ResultSet rs = st.executeQuery("select count(customer_id) as c from customer");
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
    
    public ArrayList<customer> getPage(int page) {
        ArrayList<customer> list = new ArrayList<>();
        
        try {
            Statement st = super.connect().createStatement();
            ResultSet rs = st.executeQuery("select * from customer order by customer_id offset " + ((page - 1) * 10) + " limit 10");
            
            while(rs.next()) {
                list.add(new customer(rs.getInt("customer_id"), rs.getString("phone_number"),
                                rs.getString("email"), rs.getString("name")));
            }
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
        }
        
        return list;
    }
}
