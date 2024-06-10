/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import util.DbConnection;
import entity.customer;
import jakarta.ejb.Local;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.io.Serializable;
import java.sql.*;
import java.util.ArrayList;
/**
 *
 * @author Efe
 */

@Local
@Stateless
public class customerDAO extends BaseDAO<customer> implements Serializable {

    @Override
    public customer getById(int id) {
        customer c = null;
        
        try {
            Statement st = super.connect().createStatement();
            ResultSet rs = st.executeQuery("select * from customer where id=" + id);
            rs.next();
            
            c = new customer(rs.getInt("id"), rs.getString("phone_number"), rs.getString("email"), rs.getString("name"));
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
        }
        
        return c;
    }
    

    @Override
    public ArrayList<customer> getList() {
        ArrayList<customer> list = new ArrayList<>();
        
        try {
            Statement st = super.connect().createStatement();
            ResultSet rs = st.executeQuery("select * from customer order by id");
            
            while(rs.next()) {
                list.add(new customer(rs.getInt("id"), rs.getString("phone_number"),
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
            ResultSet rs = st.executeQuery("select count(id) as c from customer");
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
            ResultSet rs = st.executeQuery("select * from customer order by id offset " + ((page - 1) * 10) + " limit 10");
            
            while(rs.next()) {
                list.add(new customer(rs.getInt("id"), rs.getString("phone_number"),
                                rs.getString("email"), rs.getString("name")));
            }
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
        }
        
        return list;
    }
}
