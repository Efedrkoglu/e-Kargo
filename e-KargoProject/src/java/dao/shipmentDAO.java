/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import entity.customer;
import entity.location;
import entity.packageStatus;
import entity.shipment;
import jakarta.ejb.Local;
import jakarta.ejb.Stateless;
import java.io.Serializable;
import util.DbConnection;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
/**
 *
 * @author Efe
 */

@Local
@Stateless
public class shipmentDAO extends BaseDAO<shipment> implements Serializable {
    
    private customerDAO cDao;
    private locationDAO lDao;
    private packageStatusDAO psDao;

    public customerDAO getcDao() {
        if(this.cDao == null) {
            this.cDao = new customerDAO();
        }
        return cDao;
    }

    public void setcDao(customerDAO cDao) {
        this.cDao = cDao;
    }

    public locationDAO getlDao() {
        if(this.lDao == null) {
            this.lDao = new locationDAO();
        }
        return lDao;
    }

    public void setlDao(locationDAO lDao) {
        this.lDao = lDao;
    }

    public packageStatusDAO getPsDao() {
        if(this.psDao == null) {
            this.psDao = new packageStatusDAO();
        }
        return psDao;
    }

    public void setPsDao(packageStatusDAO psDao) {
        this.psDao = psDao;
    }
    
    @Override
    public shipment getById(int id) {
        shipment s = null;
        
        try {
            Statement st = super.connect().createStatement();
            ResultSet rs = st.executeQuery("select * from shipment where id=" + id);
            rs.next();
            
            Date estimatedDeliveryFromDb = rs.getDate("estimated_delivery");
            Date deliveredAtFromDb = rs.getDate("delivered_at");
            LocalDate estimatedDelivery = estimatedDeliveryFromDb.toLocalDate();
            LocalDate deliveredAt = deliveredAtFromDb.toLocalDate();

            customer c = this.getcDao().getById(rs.getInt("customer_id"));
            location fromL = this.getlDao().getById(rs.getInt("from_location_id"));
            location toL = this.getlDao().getById(rs.getInt("to_location_id"));
            packageStatus ps = this.getPsDao().getById(rs.getInt("package_status_id"));
            
            s = new shipment(rs.getInt("id"), estimatedDelivery, deliveredAt, c, fromL, toL, ps, rs.getString("trackingNumber"));
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
        }
        
        return s;
    }
    
    @Override
    public ArrayList<shipment> getList() {
        ArrayList<shipment> list = new ArrayList<>();
        
        try {
            Statement st = super.connect().createStatement();
            ResultSet rs = st.executeQuery("select * from shipment order by id");
            
            while(rs.next()) {
                Date estimatedDeliveryFromDb = rs.getDate("estimated_delivery");
                Date deliveredAtFromDb = rs.getDate("delivered_at");
                LocalDate estimatedDelivery = estimatedDeliveryFromDb.toLocalDate();
                LocalDate deliveredAt = deliveredAtFromDb.toLocalDate();
                
                customer c = this.getcDao().getById(rs.getInt("customer_id"));
                location fromL = this.getlDao().getById(rs.getInt("from_location_id"));
                location toL = this.getlDao().getById(rs.getInt("to_location_id"));
                packageStatus ps = this.getPsDao().getById(rs.getInt("package_status_id"));
                
                list.add(new shipment(rs.getInt("id"), estimatedDelivery, deliveredAt, c, fromL, toL, ps, rs.getString("trackingNumber")));
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
            ResultSet rs = st.executeQuery("select count(id) as c from shipment");
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
    
    public ArrayList<shipment> getPage(int page) {
        ArrayList<shipment> list = new ArrayList<>();
        
        try {
            Statement st = super.connect().createStatement();
            ResultSet rs = st.executeQuery("select * from shipment order by id offset " + ((page - 1) * 10) + " limit 10");
            
            while(rs.next()) {
                Date estimatedDeliveryFromDb = rs.getDate("estimated_delivery");
                Date deliveredAtFromDb = rs.getDate("delivered_at");
                LocalDate estimatedDelivery = estimatedDeliveryFromDb.toLocalDate();
                LocalDate deliveredAt = deliveredAtFromDb.toLocalDate();
                
                customer c = this.getcDao().getById(rs.getInt("customer_id"));
                location fromL = this.getlDao().getById(rs.getInt("from_location_id"));
                location toL = this.getlDao().getById(rs.getInt("to_location_id"));
                packageStatus ps = this.getPsDao().getById(rs.getInt("package_status_id"));
                
                list.add(new shipment(rs.getInt("id"), estimatedDelivery, deliveredAt, c, fromL, toL, ps, rs.getString("trackingNumber")));
            }
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
        }
        
        return list;
    }
}
