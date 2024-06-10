/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package converter;

import dao.locationDAO;
import entity.location;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.FacesConverter;

/**
 *
 * @author Efe
 */

@FacesConverter("locationConverter")
public class locationConverter implements Converter{
    
    private locationDAO locationDAO;

    public locationDAO getLocationDAO() {
        if(this.locationDAO == null) {
            this.locationDAO = new locationDAO();
        }
        return locationDAO;
    }

    public void setLocationDAO(locationDAO locationDAO) {
        this.locationDAO = locationDAO;
    }
    
    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        System.out.println(string);
        int id = Integer.valueOf(string);
        location l = this.getLocationDAO().getById(id);
        return l;
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object t) {
        location l = (location)t;
        return String.valueOf(l.getId());
    }
    
}
