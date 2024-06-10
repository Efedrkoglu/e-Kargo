/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import entity.location;
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
public class locationDAO extends BaseDAO<location> implements Serializable {
    
    @Override
    public location getById(int id) {
        location l = null;
        try {
            Statement st = super.connect().createStatement();
            ResultSet rs = st.executeQuery("select * from location where id =" + id);
            rs.next();
            
            l = new location(rs.getInt("id"), rs.getString("city"), rs.getString("country"));
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
        }
        
        return l;
    }
    
    @Override
    public ArrayList<location> getList() {
        ArrayList<location> list = new ArrayList<>();
        
        try {
            Statement st = super.connect().createStatement();
            ResultSet rs = st.executeQuery("select * from location order by id");
            
            while(rs.next()) {
                list.add(new location(rs.getInt("id"), rs.getString("city"), rs.getString("country")));
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
            ResultSet rs = st.executeQuery("select count(id) as c from location");
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
    
    public ArrayList<location> getPage(int page) {
        ArrayList<location> list = new ArrayList<>();
        
        try {
            Statement st = super.connect().createStatement();
            ResultSet rs = st.executeQuery("select * from location order by id offset " + ((page - 1) * 10) + " limit 10");
            
            while(rs.next()) {
                list.add(new location(rs.getInt("id"), rs.getString("city"), rs.getString("country")));
            }
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
        }
        
        return list;
    }
}
