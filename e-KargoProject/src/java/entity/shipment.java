/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.time.LocalDate;

/**
 *
 * @author Efe
 */
public class shipment {
    
    private int shipment_id;
    private LocalDate estimated_delivery;
    private LocalDate delivered_at;
    private customer customer;
    private location fromLocation;
    private location toLocation;
    private packageStatus packageStatus;
    private String trackingNumber;
    
    public shipment() {
        
    }

    public shipment(int shipment_id, LocalDate estimated_delivery, LocalDate delivered_at, customer customer, location fromLocation, location toLocation, packageStatus packageStatus, String trackingNumber) {
        this.shipment_id = shipment_id;
        this.estimated_delivery = estimated_delivery;
        this.delivered_at = delivered_at;
        this.customer = customer;
        this.fromLocation = fromLocation;
        this.toLocation = toLocation;
        this.packageStatus = packageStatus;
        this.trackingNumber = trackingNumber;
    }

    public int getShipment_id() {
        return shipment_id;
    }

    public void setShipment_id(int shipment_id) {
        this.shipment_id = shipment_id;
    }

    public LocalDate getEstimated_delivery() {
        return estimated_delivery;
    }

    public void setEstimated_delivery(LocalDate estimated_delivery) {
        this.estimated_delivery = estimated_delivery;
    }

    public LocalDate getDelivered_at() {
        return delivered_at;
    }

    public void setDelivered_at(LocalDate delivered_at) {
        this.delivered_at = delivered_at;
    }

    public customer getCustomer() {
        return customer;
    }

    public void setCustomer(customer customer) {
        this.customer = customer;
    }

    public location getFromLocation() {
        return fromLocation;
    }

    public void setFromLocation(location fromLocation) {
        this.fromLocation = fromLocation;
    }

    public location getToLocation() {
        return toLocation;
    }

    public void setToLocation(location toLocation) {
        this.toLocation = toLocation;
    }

    public packageStatus getPackageStatus() {
        return packageStatus;
    }

    public void setPackageStatus(packageStatus packageStatus) {
        this.packageStatus = packageStatus;
    }

    public String getTrackingNumber() {
        return trackingNumber;
    }

    public void setTrackingNumber(String trackingNumber) {
        this.trackingNumber = trackingNumber;
    }

    @Override
    public String toString() {
        return "shipment{" + "shipment_id=" + shipment_id + ", estimated_delivery=" + estimated_delivery + ", delivered_at=" + delivered_at + ", customer_id=" + customer.getCustomer_id() + ", fromLocation_id=" + fromLocation.getLocation_id() + ", toLocation_id=" + toLocation.getLocation_id() + ", packageStatus_id=" + packageStatus.getStatus_id() + ", trackingNumber=" + trackingNumber + '}';
    }
    
    
}
