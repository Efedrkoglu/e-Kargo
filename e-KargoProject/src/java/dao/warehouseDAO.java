/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import entity.warehouse;
import entity.location;
import util.DbConnection;
import java.sql.*;
import java.util.ArrayList;
/**
 *
 * @author Efe
 */
public class warehouseDAO extends DbConnection{
    
    public void insert(warehouse w) {
        try {
            Statement st = super.connect().createStatement();
            st.executeUpdate("insert into warehouse values(" + w.getWarehouse_id() + ", " 
                            + w.getLocation().getLocation_id() + ")");
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
        } 
    }
    
    public void update(warehouse w) {
        try {
            Statement st = super.connect().createStatement();
            st.executeUpdate("update warehouse set location_id=" + w.getLocation().getLocation_id()
                            + " where warehouse_id=" + w.getWarehouse_id());
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void delete(warehouse w) {
        try {
            Statement st = super.connect().createStatement();
            st.executeUpdate("delete from warehouse where warehouse_id=" + w.getWarehouse_id());
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    public ArrayList<warehouse> getList() {
        ArrayList<warehouse> list = new ArrayList<>();
        
        try {
            Statement st = super.connect().createStatement();
            ResultSet rs = st.executeQuery("select warehouse.warehouse_id, location.* from warehouse join location"
                                        + " on warehouse.location_id = location.location_id order by warehouse.warehouse_id");
            
            while(rs.next()) {
                list.add(new warehouse(rs.getInt("warehouse_id"),
                        new location(rs.getInt("location_id"), rs.getString("city"), rs.getString("country"))));
            }
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
        }
        
        return list;
    }
}
