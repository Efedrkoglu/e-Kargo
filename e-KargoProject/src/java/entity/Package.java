/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author Efe
 */
public class Package {
    
    private int package_id;
    private warehouse fromWarehouse;
    private warehouse toWarehouse;
    private shipment shipment;
    private String content;
    private double value;
    private double weight;
    
    public Package() {
        
    }

    public Package(int package_id, warehouse fromWarehouse, warehouse toWarehouse, shipment shipment, String content, double value, double weight) {
        this.package_id = package_id;
        this.fromWarehouse = fromWarehouse;
        this.toWarehouse = toWarehouse;
        this.shipment = shipment;
        this.content = content;
        this.value = value;
        this.weight = weight;
    }

    public int getPackage_id() {
        return package_id;
    }

    public void setPackage_id(int package_id) {
        this.package_id = package_id;
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

    @Override
    public String toString() {
        return "Package{" + "package_id=" + package_id + ", fromWarehouse_id=" + fromWarehouse.getWarehouse_id() + ", toWarehouse_id=" + toWarehouse.getWarehouse_id() + ", shipment_id=" + shipment.getShipment_id() + ", content=" + content + ", value=" + value + ", weight=" + weight + '}';
    }
    
    
}
