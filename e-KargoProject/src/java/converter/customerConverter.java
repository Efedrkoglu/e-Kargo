/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package converter;

import dao.customerDAO;
import entity.customer;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.FacesConverter;

/**
 *
 * @author Efe
 */

@FacesConverter("customerConverter")
public class customerConverter implements Converter {
    
    private customerDAO cDAO;

    public customerDAO getcDAO() {
        if(this.cDAO == null) {
            this.cDAO = new customerDAO();
        }
        return cDAO;
    }

    public void setcDAO(customerDAO cDAO) {
        this.cDAO = cDAO;
    }
    
    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        int id = Integer.valueOf(string);
        customer c = this.getcDAO().getById(id);
        return c;
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object t) {
        customer c = (customer)t;
        return String.valueOf(c.getCustomer_id());
    }
    
}
