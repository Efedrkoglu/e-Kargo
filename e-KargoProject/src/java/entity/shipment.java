/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.Month;

/**
 *
 * @author Efe
 */

@Entity
@Table(name = "shipment")
public class shipment implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    private LocalDate estimated_delivery;
    private LocalDate delivered_at;
    
    @ManyToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "ID")
    private customer customer;
    
    @ManyToOne
    @JoinColumn(name = "from_location_id", referencedColumnName = "ID")
    private location fromLocation;
    
    @ManyToOne
    @JoinColumn(name = "to_location_id", referencedColumnName = "ID")
    private location toLocation;
    
    @ManyToOne
    @JoinColumn(name = "package_status_id", referencedColumnName = "ID")
    private packageStatus packageStatus;
    
    private String trackingNumber;
    
    public shipment() {
        
    }

    public shipment(int id, LocalDate estimated_delivery, LocalDate delivered_at, customer customer, location fromLocation, location toLocation, packageStatus packageStatus, String trackingNumber) {
        this.id = id;
        this.estimated_delivery = estimated_delivery;
        this.delivered_at = delivered_at;
        this.customer = customer;
        this.fromLocation = fromLocation;
        this.toLocation = toLocation;
        this.packageStatus = packageStatus;
        this.trackingNumber = trackingNumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEstimated_delivery() {
        if(this.estimated_delivery == null) {
            this.estimated_delivery = LocalDate.of(1,1,1);
        }
        return estimated_delivery.toString();
    }

    public void setEstimated_delivery(String estimated_delivery) {
        this.estimated_delivery = LocalDate.parse(estimated_delivery);
    }

    public String getDelivered_at() {
        if(this.delivered_at == null) {
            this.delivered_at = LocalDate.of(1,1,1);
        }
        return delivered_at.toString();
    }

    public void setDelivered_at(String delivered_at) {
        this.delivered_at = LocalDate.parse(delivered_at);
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
    public int hashCode() {
        int hash = 5;
        hash = 17 * hash + this.id;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final shipment other = (shipment) obj;
        return this.id == other.id;
    }
    
}
