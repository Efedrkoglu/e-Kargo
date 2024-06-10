/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import util.DbConnection;
import entity.packageStatus;
import jakarta.ejb.Local;
import jakarta.ejb.Stateless;
import java.io.Serializable;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author Efe
 */

@Local
@Stateless
public class packageStatusDAO extends BaseDAO<packageStatus> implements Serializable {
    
    @Override
    public packageStatus getById(int id) {
        packageStatus ps = null;
        
        try {
            Statement st = super.connect().createStatement();
            ResultSet rs = st.executeQuery("select * from packageStatus where id=" + id);
            rs.next();
            
            ps = new packageStatus(rs.getInt("id"), rs.getString("description"));
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
        }
        
        return ps;
    }
    
    @Override
    public ArrayList<packageStatus> getList() {
        ArrayList<packageStatus> list = new ArrayList<>();
        
        try {
            Statement st = super.connect().createStatement();
            ResultSet rs = st.executeQuery("select * from packageStatus order by id");
            
            while(rs.next()) {
                list.add(new packageStatus(rs.getInt("id"), rs.getString("description")));
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
            ResultSet rs = st.executeQuery("select count(id) as c from packageStatus");
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
    
    public ArrayList<packageStatus> getPage(int page) {
        ArrayList<packageStatus> list = new ArrayList<>();
        
        try {
            Statement st = super.connect().createStatement();
            ResultSet rs = st.executeQuery("select * from packageStatus order by id offset " + ((page - 1) * 10) + " limit 10");
            
            while(rs.next()) {
                list.add(new packageStatus(rs.getInt("id"), rs.getString("description")));
            }
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
        }
        
        return list;
    }
}
