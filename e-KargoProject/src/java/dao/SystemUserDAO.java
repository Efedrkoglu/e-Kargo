/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import entity.SystemUser;
import jakarta.ejb.Local;
import jakarta.ejb.Stateless;
import java.io.Serializable;
import java.util.ArrayList;
import java.sql.*;

/**
 *
 * @author Efe
 */

@Local
@Stateless
public class SystemUserDAO extends BaseDAO<SystemUser> implements Serializable {

    @Override
    public ArrayList<SystemUser> getList() {
        ArrayList<SystemUser> list = new ArrayList<>();
        
        try {
            Statement st = super.connect().createStatement();
            ResultSet rs = st.executeQuery("select * from SystemUser order by id asc");
            
            while(rs.next()) {
                list.add(new SystemUser(rs.getInt("id"), rs.getString("userName"), rs.getString("password"), rs.getBoolean("admin")));
            }
        }
        catch(Exception ex) {
            System.out.println(ex.getMessage());
        }
        
        return list;
    }

    @Override
    public SystemUser getById(int id) {
        SystemUser systemUser = null;
        
        try {
            Statement st = super.connect().createStatement();
            ResultSet rs = st.executeQuery("select * from SystemUser where id=" + id);
            rs.next();
            
            systemUser = new SystemUser(rs.getInt("id"), rs.getString("userName"), rs.getString("password"), rs.getBoolean("admin"));
        }
        catch(Exception ex) {
            System.out.println(ex.getMessage());
        }
        
        return systemUser;
    }
    
}
