/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package converter;

import dao.shipmentDAO;
import entity.shipment;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.FacesConverter;

/**
 *
 * @author Efe
 */

@FacesConverter("shipmentConverter")
public class shipmentConverter implements Converter {
    
    private shipmentDAO sDAO;

    public shipmentDAO getsDAO() {
        if(this.sDAO == null) {
            this.sDAO = new shipmentDAO();
        }
        return sDAO;
    }

    public void setsDAO(shipmentDAO sDAO) {
        this.sDAO = sDAO;
    }
    
    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        int id = Integer.valueOf(string);
        shipment s = this.getsDAO().getById(id);
        return s;
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object t) {
        shipment s = (shipment)t;
        return String.valueOf(s.getShipment_id());
    }
}
