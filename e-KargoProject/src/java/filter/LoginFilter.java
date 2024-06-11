/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package filter;

import entity.SystemUser;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

/**
 *
 * @author Efe
 */

@WebFilter("/*")
public class LoginFilter implements Filter {

    @Override
    public void doFilter(ServletRequest sr, ServletResponse sr1, FilterChain fc) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest)sr;
        HttpServletResponse response = (HttpServletResponse)sr1;
        
        String url = request.getRequestURI();
        
        //css dosyalarını engellememek için
        if (url.startsWith(request.getContextPath() + "/resources/") || url.contains("jakarta.faces.resource")) {
            fc.doFilter(sr, sr1);
            return;
        }
        
        HttpSession session = request.getSession();
        
        SystemUser user = null;
        if(session != null) {
            user = (SystemUser)session.getAttribute("validUser");
        }
        
        if(user == null) {
            if(!url.contains("login")) {
                response.sendRedirect(request.getContextPath() + "/login.xhtml");
            }
            else {
                fc.doFilter(sr, sr1);
            }
        }
        else {
            if(!user.isAdmin()) {
                if(url.contains("systemUser")) {
                    response.sendRedirect(request.getContextPath() + "/denied.xhtml");
                }
                else if(url.contains("logout")) {
                    session.invalidate();
                    response.sendRedirect(request.getContextPath() + "/login.xhtml");
                }
                else {
                    fc.doFilter(sr, sr1);
                }
            }
            else if(url.contains("logout")) {
                session.invalidate();
                response.sendRedirect(request.getContextPath() + "/login.xhtml");
            }
            else {
                fc.doFilter(sr, sr1);
            }
        }
    }
    
}
