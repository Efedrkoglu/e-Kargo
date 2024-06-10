/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import entity.Package;
import entity.shipment;
import entity.warehouse;
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
public class packageDAO extends BaseDAO<Package> implements Serializable {
    
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
    
    @Override
    public ArrayList<Package> getList() {
        ArrayList<Package> list = new ArrayList<>();
        
        try {
            Statement st = super.connect().createStatement();
            ResultSet rs = st.executeQuery("select * from package order by id");
            
            while(rs.next()) {
                warehouse fromWarehouse = this.getwDao().getById(rs.getInt("from_warehouse_id"));
                warehouse toWarehouse = this.getwDao().getById(rs.getInt("to_warehouse_id"));
                shipment shipment = this.getsDao().getById(rs.getInt("shipment_id"));
                list.add(new Package(rs.getInt("id"), fromWarehouse, toWarehouse, shipment, rs.getString("content"), rs.getDouble("value"), rs.getDouble("weight")));
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
            ResultSet rs = st.executeQuery("select count(id) as c from package");
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
    
    public ArrayList<Package> getPage(int page) {
        ArrayList<Package> list = new ArrayList<>();
        
        try {
            Statement st = super.connect().createStatement();
            ResultSet rs = st.executeQuery("select * from package order by id offset " + ((page - 1) * 10) + " limit 10");
            
            while(rs.next()) {
                warehouse fromWarehouse = this.getwDao().getById(rs.getInt("from_warehouse_id"));
                warehouse toWarehouse = this.getwDao().getById(rs.getInt("to_warehouse_id"));
                shipment shipment = this.getsDao().getById(rs.getInt("shipment_id"));
                list.add(new Package(rs.getInt("id"), fromWarehouse, toWarehouse, shipment, rs.getString("content"), rs.getDouble("value"), rs.getDouble("weight")));
            }
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
        }
        
        return list;
    }

    @Override
    public Package getById(int id) {
        return null;
    }
}
