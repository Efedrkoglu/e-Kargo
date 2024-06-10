/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package converter;

import dao.packageStatusDAO;
import entity.packageStatus;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.FacesConverter;

/**
 *
 * @author Efe
 */

@FacesConverter("packageStatusConverter")
public class packageStatusConverter implements Converter {
    
    private packageStatusDAO psDAO;

    public packageStatusDAO getPsDAO() {
        if(this.psDAO == null) {
            this.psDAO = new packageStatusDAO();
        }
        return psDAO;
    }

    public void setPsDAO(packageStatusDAO psDAO) {
        this.psDAO = psDAO;
    }
    
    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        int id = Integer.valueOf(string);
        packageStatus ps = this.getPsDAO().getById(id);
        return ps;
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object t) {
        packageStatus ps = (packageStatus)t;
        return String.valueOf(ps.getId());
    }
    
}
