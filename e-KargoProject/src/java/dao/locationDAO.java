/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import entity.location;
import util.DbConnection;
import java.sql.*;
import java.util.ArrayList;
/**
 *
 * @author Efe
 */
public class locationDAO extends DbConnection{
    
    public location getById(int id) {
        location l = null;
        try {
            Statement st = super.connect().createStatement();
            ResultSet rs = st.executeQuery("select * from location where id =" + id);
            rs.next();
            
            l = new location(rs.getInt("location_id"), rs.getString("city"), rs.getString("country"));
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
        }
        
        return l;
    }
    
    public void insert(location l) {
        try {
            Statement st = super.connect().createStatement();
            st.executeUpdate("insert into location values(" + l.getLocation_id()+ ", '" + l.getCity() + 
                            "', '" + l.getCountry() + "')");
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void update(location l) {
        try {
            Statement st = super.connect().createStatement();
            st.executeUpdate("update location set city='" + l.getCity() + "', country='" +l.getCountry()
                            + "' where location_id=" + l.getLocation_id());
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void delete(location l) {
        try {
            Statement st = super.connect().createStatement();
            st.executeUpdate("delete from location where location_id=" + l.getLocation_id());
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    public ArrayList<location> getList() {
        ArrayList<location> list = new ArrayList<>();
        
        try {
            Statement st = super.connect().createStatement();
            ResultSet rs = st.executeQuery("select * from location order by location_id");
            
            while(rs.next()) {
                list.add(new location(rs.getInt("location_id"), rs.getString("city"), rs.getString("country")));
            }
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
        }
        
        return list;
    }
}
