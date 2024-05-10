/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import entity.carrier;
import entity.shipment;
import util.DbConnection;
import java.sql.*;
import java.util.ArrayList;
/**
 *
 * @author Efe
 */
public class carrierDAO extends DbConnection{
    
    private shipmentDAO sDao;

    public shipmentDAO getsDao() {
        if(this.sDao == null) {
            this.sDao = new shipmentDAO();
        }
        return sDao;
    }

    public void setsDao(shipmentDAO sDao) {
        this.sDao = sDao;
    }
    
    public void insert(carrier c){
        try {
            Statement st = super.connect().createStatement();
            st.executeUpdate("insert into carrier values(default, " + c.getShipment().getShipment_id()
                            + ", '" + c.getPhone_number() + "', '" + c.getEmail() + "', '" + c.getName() + "')");
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void update(carrier c) {
        try {
            Statement st = super.connect().createStatement();
            st.executeUpdate("update carrier set carrying_shipment_id=" + c.getShipment().getShipment_id() + ", phone_number='"
                            + c.getPhone_number() + "', email='" + c.getEmail() + "', name='" + c.getName() 
                            + "' where carrier_id=" + c.getCarrier_id());
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void delete(carrier c) {
        try {
            Statement st = super.connect().createStatement();
            st.executeUpdate("delete from carrier where carrier_id=" + c.getCarrier_id());
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    public ArrayList<carrier> getList() {
        ArrayList<carrier> list = new ArrayList<>();
        
        try {
            Statement st = super.connect().createStatement();
            ResultSet rs = st.executeQuery("select * from carrier order by carrier_id");
            
            while(rs.next()) {
                shipment s = this.getsDao().getById(rs.getInt("carrying_shipment_id"));
                list.add(new carrier(rs.getInt("carrier_id"), s, rs.getString("phone_number"), rs.getString("email"), rs.getString("name")));
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
            ResultSet rs = st.executeQuery("select count(carrier_id) as c from carrier");
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
    
    public ArrayList<carrier> getPage(int page) {
        ArrayList<carrier> list = new ArrayList<>();
        
        try {
            Statement st = super.connect().createStatement();
            ResultSet rs = st.executeQuery("select * from carrier order by carrier_id offset " + ((page - 1) * 10) + " limit 10");
            
            while(rs.next()) {
                shipment s = this.getsDao().getById(rs.getInt("carrying_shipment_id"));
                list.add(new carrier(rs.getInt("carrier_id"), s, rs.getString("phone_number"), rs.getString("email"), rs.getString("name")));
            }
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
        }
        
        return list;
    }
}
