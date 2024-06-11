/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.SystemUserDAO;
import entity.SystemUser;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.ExternalContext;
import jakarta.faces.context.FacesContext;
import jakarta.faces.validator.ValidatorException;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Efe
 */

@Named
@SessionScoped
public class SystemUserBean extends baseController<SystemUser> implements Serializable, IController<SystemUser> {

    @EJB
    private SystemUserDAO dao;
    
    private int selection = 0;
    
    public SystemUserBean() {
        
    }

    public SystemUserDAO getDao() {
        return dao;
    }

    public void setDao(SystemUserDAO dao) {
        this.dao = dao;
    }

    public int getSelection() {
        return selection;
    }

    public void setSelection(int selection) {
        this.selection = selection;
    }
    
    public void login() {
        for(SystemUser user : this.dao.getList()) {
            if(user.getUserName().equals(this.entity.getUserName()) && user.getPassword().equals(this.entity.getPassword())) {
                if(user.isAdmin())
                    this.entity.setAdmin(true);
                else
                    this.entity.setAdmin(false);
                
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("validUser", user);
                
                try {
                    ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
                    ec.redirect(ec.getRequestContextPath() + "/Home Page/home.xhtml");
                } 
                catch (Exception ex) {
                    ex.printStackTrace();
                }
                return;
            }
        }
        
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Kullanıcı adı veya şifre hatalı!"));
    }
    
    public void register() {
        for(SystemUser user : this.dao.getList()) {
            if (this.entity.getUserName().equals(user.getUserName())) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Bu kullanıcı adı daha önce alınmış!"));
                return;
            }
        }
        
        insert();
    }
    
    public boolean validateRegisterUsername(FacesContext fc, UIComponent comp, Object obj) throws ValidatorException {
        String username = (String)obj;
        
        if(username.length() < 5) {
            throw new ValidatorException(new FacesMessage("Kullanıcı adı en az 5 karakter uzunluğunda olmalıdır!"));
        }
        
        return true;
    }
    
    public boolean validateRegisterPassword(FacesContext fc, UIComponent comp, Object obj) throws ValidatorException {
        String password = (String)obj;
        
        if(password.length() < 5) {
            throw new ValidatorException(new FacesMessage("Şifre en az 5 karakter uzunluğunda olmalıdır!"));
        }

        return true;
    }
    
    @Override
    public SystemUser newEntity() {
        return new SystemUser();
    }

    @Override
    public ArrayList<SystemUser> select() {
        return this.dao.getList();
    }

    @Override
    public void insert() {
        this.dao.insert(this.entity);
        super.clearForm();
    }

    @Override
    public void update() {
        this.dao.update(this.entity);
        super.clearForm();
    }

    @Override
    public void delete(SystemUser entity) {
        this.dao.delete(entity);
        super.clearForm();
    }
    
}
