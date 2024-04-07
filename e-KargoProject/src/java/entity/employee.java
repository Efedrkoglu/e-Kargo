/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author Efe
 */
public class employee {
    private int employee_id;
    private warehouse warehouse;
    private String phone_number;
    private String email;
    private String name;
    
    public employee() {
        
    }

    public employee(int employee_id, warehouse warehouse, String phone_number, String email, String name) {
        this.employee_id = employee_id;
        this.warehouse = warehouse;
        this.phone_number = phone_number;
        this.email = email;
        this.name = name;
    }

    public int getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(int employee_id) {
        this.employee_id = employee_id;
    }

    public warehouse getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(warehouse warehouse) {
        this.warehouse = warehouse;
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
    
    
}
