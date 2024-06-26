/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import entity.location;
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
public class warehouseDAO extends BaseDAO<warehouse> implements Serializable {
    
    private locationDAO lDao;

    public locationDAO getlDao() {
        if(this.lDao == null) {
            this.lDao = new locationDAO();
        }
        return lDao;
    }

    public void setlDao(locationDAO lDao) {
        this.lDao = lDao;
    }
    
    @Override
    public warehouse getById(int id) {
        warehouse w = null;
        
        try {
            Statement st = super.connect().createStatement();
            ResultSet rs = st.executeQuery("select * from warehouse where id=" + id);
            rs.next();
            
            location l = this.getlDao().getById(rs.getInt("location_id"));
            w = new warehouse(rs.getInt("id"), l);
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
        }
        
        return w;
    }
    
    @Override
    public ArrayList<warehouse> getList() {
        ArrayList<warehouse> list = new ArrayList<>();
        
        try {
            Statement st = super.connect().createStatement();
            ResultSet rs = st.executeQuery("select * from warehouse order by id");
            
            while(rs.next()) {
                location l = this.getlDao().getById(rs.getInt("location_id"));
                list.add(new warehouse(rs.getInt("id"), l));
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
            ResultSet rs = st.executeQuery("select count(id) as c from warehouse");
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
    
    public ArrayList<warehouse> getPage(int page) {
        ArrayList<warehouse> list = new ArrayList<>();
        
        try {
            Statement st = super.connect().createStatement();
            ResultSet rs = st.executeQuery("select * from warehouse order by id offset " + ((page - 1) * 10) + " limit 10");
            
            while(rs.next()) {
                location l = this.getlDao().getById(rs.getInt("location_id"));
                list.add(new warehouse(rs.getInt("id"), l));
            }
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
        }
        
        return list;
    }
}
