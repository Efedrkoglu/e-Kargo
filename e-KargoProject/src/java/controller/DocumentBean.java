/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.DocumentDAO;
import entity.Document;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import jakarta.servlet.http.Part;
import java.io.File;
import java.io.InputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.util.ArrayList;

/**
 *
 * @author Efe
 */

@Named
@SessionScoped
public class DocumentBean implements Serializable {
    
    private Document entity;
    private DocumentDAO dao;
    private ArrayList<Document> list;
    
    private Part doc;
    
    private final String uploadTo = "C:\\Users\\efe44\\Desktop\\eKargoUpload\\";
    
    public DocumentBean() {
        
    }
    
    public void upload() {
        try {
            InputStream input = doc.getInputStream();
            File f = new File(uploadTo + doc.getSubmittedFileName());
            Files.copy(input, f.toPath());
            
            entity = new Document(0, f.getName(), f.getParent(), doc.getContentType());
            this.getDao().insert(entity);
            entity = new Document();
        }
        catch(Exception ex) {
            ex.printStackTrace();
        }
    }

    public Document getEntity() {
        if(this.entity == null)
            this.entity = new Document();
        return entity;
    }

    public void setEntity(Document entity) {
        this.entity = entity;
    }

    public DocumentDAO getDao() {
        if(this.dao == null)
            this.dao = new DocumentDAO();
        return dao;
    }

    public void setDao(DocumentDAO dao) {
        this.dao = dao;
    }

    public ArrayList<Document> getList() {
        return this.getDao().getList();
    }

    public Part getDoc() {
        return doc;
    }

    public void setDoc(Part doc) {
        this.doc = doc;
    }

    public String getUploadTo() {
        return uploadTo;
    }
    
    
}
