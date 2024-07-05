/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import entity.Document;
import java.util.ArrayList;
import util.DbConnection;
import java.sql.*;

/**
 *
 * @author Efe
 */

public class DocumentDAO extends DbConnection {
    
    public void insert(Document d) {
        try {
            Statement st = super.connect().createStatement();
            st.executeUpdate("insert into documents values(default, '" + d.getFileName() + "', '" + d.getFilePath() + "', "
                            + "'" + d.getFileType() + "')");
        }
        catch(Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public void update(Document d) {
        try {
            Statement st = super.connect().createStatement();
            st.executeUpdate("update documents set fileName=" + d.getFileName() + ", filePath=" + d.getFilePath()
                            + ", fileType=" + d.getFileType() + " where id=" + d.getId());
        }
        catch(Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public void delete(Document d) {
        try {
            Statement st = super.connect().createStatement();
            st.executeUpdate("delete from documents where id=" + d.getId());
        }
        catch(Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public ArrayList<Document> getList() {
        ArrayList<Document> list = new ArrayList<>();
        
        try {
            Statement st = super.connect().createStatement();
            ResultSet rs = st.executeQuery("select * from documents order by id");
            
            while(rs.next()) {
                list.add(new Document(rs.getInt("id"), rs.getString("fileName"), rs.getString("filePath"), rs.getString("fileType")));
            }
        }
        catch(Exception ex) {
            ex.printStackTrace();
        }
        
        return list;
    }
    
    public Document getById(int id) {
        return new Document();
    }
}
