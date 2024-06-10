/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import entity.carrier;
import entity.shipment;
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
public class carrierDAO extends BaseDAO<carrier> implements Serializable {
    
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
    
    @Override
    public ArrayList<carrier> getList() {
        ArrayList<carrier> list = new ArrayList<>();
        
        try {
            Statement st = super.connect().createStatement();
            ResultSet rs = st.executeQuery("select * from carrier order by id");
            
            while(rs.next()) {
                shipment s = this.getsDao().getById(rs.getInt("shipment_id"));
                list.add(new carrier(rs.getInt("id"), s, rs.getString("phone_number"), rs.getString("email"), rs.getString("name")));
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
            ResultSet rs = st.executeQuery("select count(id) as c from carrier");
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
            ResultSet rs = st.executeQuery("select * from carrier order by id offset " + ((page - 1) * 10) + " limit 10");
            
            while(rs.next()) {
                shipment s = this.getsDao().getById(rs.getInt("shipment_id"));
                list.add(new carrier(rs.getInt("id"), s, rs.getString("phone_number"), rs.getString("email"), rs.getString("name")));
            }
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
        }
        
        return list;
    }

    @Override
    public carrier getById(int id) {
        return null;
    }
}
