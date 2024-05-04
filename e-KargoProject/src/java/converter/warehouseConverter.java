/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package converter;

import dao.warehouseDAO;
import entity.warehouse;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.FacesConverter;

/**
 *
 * @author Efe
 */

@FacesConverter("warehouseConverter")
public class warehouseConverter implements Converter{
    
    private warehouseDAO warehouseDAO;

    public warehouseDAO getWarehouseDAO() {
        if(this.warehouseDAO == null) {
            this.warehouseDAO = new warehouseDAO();
        }
        return warehouseDAO;
    }

    public void setWarehouseDAO(warehouseDAO warehouseDAO) {
        this.warehouseDAO = warehouseDAO;
    }

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        int id = Integer.valueOf(string);
        warehouse w = this.getWarehouseDAO().getById(id);
        return w;
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object t) {
        warehouse w = (warehouse)t;
        return String.valueOf(w.getWarehouse_id());
    }
    
}
