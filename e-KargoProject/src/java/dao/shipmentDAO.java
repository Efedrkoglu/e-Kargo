/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import entity.customer;
import entity.location;
import entity.packageStatus;
import entity.shipment;
import util.DbConnection;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
/**
 *
 * @author Efe
 */
public class shipmentDAO extends DbConnection{
    
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
    
    public shipment getById(int id) {
        shipment s = null;
        
        try {
            Statement st = super.connect().createStatement();
            ResultSet rs = st.executeQuery("select * from shipment where shipment_id=" + id);
            rs.next();
            
            Date estimatedDeliveryFromDb = rs.getDate("estimated_delivery");
            Date deliveredAtFromDb = rs.getDate("delivered_at");
            LocalDate estimatedDelivery = estimatedDeliveryFromDb.toLocalDate();
            LocalDate deliveredAt = deliveredAtFromDb.toLocalDate();

            customer c = this.getcDao().getById(rs.getInt("customer_id"));
            location fromL = this.getlDao().getById(rs.getInt("from_location_id"));
            location toL = this.getlDao().getById(rs.getInt("to_location_id"));
            packageStatus ps = this.getPsDao().getById(rs.getInt("package_status_id"));
            
            s = new shipment(rs.getInt("shipment_id"), estimatedDelivery, deliveredAt, c, fromL, toL, ps, rs.getString("tracking_number"));
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
        }
        
        return s;
    }
        
    public void insert(shipment s) {
        try {
            Statement st = super.connect().createStatement();
            st.executeUpdate("insert into shipment values(default, '" + s.getEstimated_delivery() + "', '"
                            + s.getDelivered_at() + "', " + s.getCustomer().getCustomer_id() + ", " + s.getFromLocation().getLocation_id()
                            + ", " + s.getToLocation().getLocation_id() + ", " + s.getPackageStatus().getStatus_id() + ", '"
                            + s.getTrackingNumber() + "')");
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void update(shipment s) {
        try {
            Statement st = super.connect().createStatement();
            st.executeUpdate("update shipment set estimated_delivery='" + s.getEstimated_delivery() + "', delivered_at='" 
                            + s.getDelivered_at() + "', customer_id=" + s.getCustomer().getCustomer_id() + ", from_location_id="
                            + s.getFromLocation().getLocation_id() + ", to_location_id=" + s.getToLocation().getLocation_id() 
                            + ", package_status_id=" + s.getPackageStatus().getStatus_id() + ", tracking_number='" + s.getTrackingNumber()
                            + "' where shipment_id=" + s.getShipment_id());
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void delete(shipment s) {
        try {
            Statement st = super.connect().createStatement();
            st.executeUpdate("delete from shipment where shipment_id=" + s.getShipment_id());
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    public ArrayList<shipment> getList() {
        ArrayList<shipment> list = new ArrayList<>();
        
        try {
            Statement st = super.connect().createStatement();
            ResultSet rs = st.executeQuery("select * from shipment order by shipment_id");
            
            while(rs.next()) {
                Date estimatedDeliveryFromDb = rs.getDate("estimated_delivery");
                Date deliveredAtFromDb = rs.getDate("delivered_at");
                LocalDate estimatedDelivery = estimatedDeliveryFromDb.toLocalDate();
                LocalDate deliveredAt = deliveredAtFromDb.toLocalDate();
                
                customer c = this.getcDao().getById(rs.getInt("customer_id"));
                location fromL = this.getlDao().getById(rs.getInt("from_location_id"));
                location toL = this.getlDao().getById(rs.getInt("to_location_id"));
                packageStatus ps = this.getPsDao().getById(rs.getInt("package_status_id"));
                
                list.add(new shipment(rs.getInt("shipment_id"), estimatedDelivery, deliveredAt, c, fromL, toL, ps, rs.getString("tracking_number")));
            }
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
        }
        
        return list;
    }
}
