/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import util.DbConnection;
import entity.packageStatus;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author Efe
 */
public class packageStatusDAO extends DbConnection{
    
    public packageStatus getById(int id) {
        packageStatus ps = null;
        
        try {
            Statement st = super.connect().createStatement();
            ResultSet rs = st.executeQuery("select * from packageStatus where status_id=" + id);
            rs.next();
            
            ps = new packageStatus(rs.getInt("status_id"), rs.getString("description"));
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
        }
        
        return ps;
    }
    
    public void insert(packageStatus ps) {
        try {
            Statement st = super.connect().createStatement();
            st.executeUpdate("insert into packageStatus values(default, '" + ps.getDescription() + "')");
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void update(packageStatus ps) {
        try {
            Statement st = super.connect().createStatement();
            st.executeUpdate("update packageStatus set description='" + ps.getDescription() + "'"
                            + " where status_id=" + ps.getStatus_id());
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void delete(packageStatus ps) {
        try {
            Statement st = super.connect().createStatement();
            st.executeUpdate("delete from packageStatus where status_id=" + ps.getStatus_id());
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    public ArrayList<packageStatus> getList() {
        ArrayList<packageStatus> list = new ArrayList<>();
        
        try {
            Statement st = super.connect().createStatement();
            ResultSet rs = st.executeQuery("select * from packageStatus order by status_id");
            
            while(rs.next()) {
                list.add(new packageStatus(rs.getInt("status_id"), rs.getString("description")));
            }
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
        }
        
        return list;
    }
    
}
