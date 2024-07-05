/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import controller.DocumentBean;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

/**
 *
 * @author Efe
 */

@WebServlet(name = "FileServlet", urlPatterns={ "/file/*" })
public class FileServlet extends HttpServlet {
    
    @Inject
    private DocumentBean documentBean;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String file = req.getPathInfo();
        File f = new File(documentBean.getUploadTo()+file);
        
        Files.copy(f.toPath(), resp.getOutputStream());
    }
}
