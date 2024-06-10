/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.io.Serializable;

/**
 *
 * @author Efe
 */

@Entity
@Table(name = "Package")
public class Package implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @ManyToOne
    @JoinColumn(name = "from_warehouse_id", referencedColumnName = "ID")
    private warehouse fromWarehouse;
    
    @ManyToOne
    @JoinColumn(name = "to_warehouse_id", referencedColumnName = "ID")
    private warehouse toWarehouse;
    
    @ManyToOne
    @JoinColumn(name = "shipment_id", referencedColumnName = "ID")
    private shipment shipment;
    
    private String content;
    private double value;
    private double weight;
    
    public Package() {
        
    }

    public Package(int id, warehouse fromWarehouse, warehouse toWarehouse, shipment shipment, String content, double value, double weight) {
        this.id = id;
        this.fromWarehouse = fromWarehouse;
        this.toWarehouse = toWarehouse;
        this.shipment = shipment;
        this.content = content;
        this.value = value;
        this.weight = weight;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public warehouse getFromWarehouse() {
        return fromWarehouse;
    }

    public void setFromWarehouse(warehouse fromWarehouse) {
        this.fromWarehouse = fromWarehouse;
    }

    public warehouse getToWarehouse() {
        return toWarehouse;
    }

    public void setToWarehouse(warehouse toWarehouse) {
        this.toWarehouse = toWarehouse;
    }

    public shipment getShipment() {
        return shipment;
    }

    public void setShipment(shipment shipment) {
        this.shipment = shipment;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }
    
}
