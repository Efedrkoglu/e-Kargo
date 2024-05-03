/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;
import java.sql.*;
/**
 *
 * @author Efe
 */
public abstract class DbConnection {
    
    private Connection connection;
    
    public Connection connect() {
        if(this.connection == null) {
            try {
                Class.forName("org.postgresql.Driver");
                this.connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/eKargo", "postgres", "passwd");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        
        return this.connection;
    }
}
