/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import util.DbConnection;
import entity.Package;
import entity.shipment;
import entity.warehouse;
import java.sql.*;
import java.util.ArrayList;
/**
 *
 * @author Efe
 */
public class packageDAO extends DbConnection{
    
    private warehouseDAO wDao;
    private shipmentDAO sDao;

    public warehouseDAO getwDao() {
        if(this.wDao == null) {
            this.wDao = new warehouseDAO();
        }
        return wDao;
    }

    public void setwDao(warehouseDAO wDao) {
        this.wDao = wDao;
    }

    public shipmentDAO getsDao() {
        if(this.sDao == null) {
            this.sDao = new shipmentDAO();
        }
        return sDao;
    }

    public void setsDao(shipmentDAO sDao) {
        this.sDao = sDao;
    }
    
    
    
    public void insert(Package p) {
        try {
            Statement st = super.connect().createStatement();
            st.executeUpdate("insert into package values(default, " + p.getFromWarehouse().getWarehouse_id()
                            + ", " + p.getToWarehouse().getWarehouse_id() + ", " + p.getShipment().getShipment_id()
                            + ", '" + p.getContent() + "', " + p.getValue() + ", " + p.getWeight() + ")");
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void update(Package p) {
        try {
            Statement st = super.connect().createStatement();
            st.executeUpdate("update package set from_warehouse_id=" + p.getFromWarehouse().getWarehouse_id() 
                            + ", to_warehouse_id=" + p.getToWarehouse().getWarehouse_id() + ", shipment_id=" 
                            + p.getShipment().getShipment_id() + ", content='" + p.getContent() + "', value="
                            + p.getValue() + ", weight=" + p.getWeight() + " where package_id=" + p.getPackage_id());
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void delete(Package p) {
        try {
            Statement st = super.connect().createStatement();
            st.executeUpdate("delete from package where package_id=" + p.getPackage_id());
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    public ArrayList<Package> getList() {
        ArrayList<Package> list = new ArrayList<>();
        
        try {
            Statement st = super.connect().createStatement();
            ResultSet rs = st.executeQuery("select * from package order by package_id");
            
            while(rs.next()) {
                warehouse fromWarehouse = this.getwDao().getById(rs.getInt("from_warehouse_id"));
                warehouse toWarehouse = this.getwDao().getById(rs.getInt("to_warehouse_id"));
                shipment shipment = this.getsDao().getById(rs.getInt("shipment_id"));
                list.add(new Package(rs.getInt("package_id"), fromWarehouse, toWarehouse, shipment, rs.getString("content"), rs.getDouble("value"), rs.getDouble("weight")));
            }
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
        }
        
        return list;
    }
}
