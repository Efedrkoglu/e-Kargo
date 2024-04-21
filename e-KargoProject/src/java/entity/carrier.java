/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author Efe
 */
public class carrier {
    
    private int carrier_id;
    private shipment shipment;
    private String phone_number;
    private String email;
    private String name;

    public carrier() {
        
    }

    public carrier(int carrier_id, shipment shipment, String phone_number, String email, String name) {
        this.carrier_id = carrier_id;
        this.shipment = shipment;
        this.phone_number = phone_number;
        this.email = email;
        this.name = name;
    }

    public int getCarrier_id() {
        return carrier_id;
    }

    public void setCarrier_id(int carrier_id) {
        this.carrier_id = carrier_id;
    }

    public shipment getShipment() {
        return shipment;
    }

    public void setShipment(shipment shipment) {
        this.shipment = shipment;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "carrier{" + "carrier_id=" + carrier_id + ", shipment_id=" + shipment.getShipment_id() + ", phone_number=" + phone_number + ", email=" + email + ", name=" + name + '}';
    }
    
    
}
