/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author Efe
 */
public class warehouse {
    private int warehouse_id;
    private location location;
    
    public warehouse() {
        
    }

    public warehouse(int warehouse_id, location location) {
        this.warehouse_id = warehouse_id;
        this.location = location;
    }

    public int getWarehouse_id() {
        return warehouse_id;
    }

    public void setWarehouse_id(int warehouse_id) {
        this.warehouse_id = warehouse_id;
    }

    public location getLocation() {
        return location;
    }

    public void setLocation(location location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "warehouse{" + "warehouse_id=" + warehouse_id + ", location_id=" + location.getLocation_id() + '}';
    }
    
    
}
