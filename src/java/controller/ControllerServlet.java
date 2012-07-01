/*
 * The MIT License
 *
 * Copyright 2012 Luis Salazar <bp.lusv@gmail.com>.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package controller;

import business.*;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import model.*;

/**
 *
 * @author Luis Salazar <bp.lusv@gmail.com>
 */
@WebServlet(name = "ControllerServlet",
loadOnStartup = 1,
urlPatterns = {"/get/classify",
                "/get/document",
                "/get/explore",
                "/get/loadDocument",
                "/get/loadProject",
                "/get/test",
                "/signIn",
                "/do/chooseLanguage",
                "/do/createDocument",
                "/do/createProject",
                "/do/createSymbol",
                "/do/loadDocument",
                "/do/loadProject",
                "/do/signIn",
                "/do/signOut",
                "/do/updateDocument",
                "/do/updateSymbol"})
public class ControllerServlet extends HttpServlet {
    @EJB private CategoryFacade categoryFacade;
    @EJB private ClassificationFacade classificationFacade;
    @EJB private DocumentFacade documentFacade;
    @EJB private ProjectFacade projectFacade;
    @EJB private SymbolFacade symbolFacade;
    @EJB private UserFacade userFacade;
    private HttpSession session;

    @Override
    public void init() throws ServletException {
        List<Category> categories = (List) categoryFacade.findAll();
        Collections.reverse(categories);
        getServletContext().setAttribute("categories", categories);
        List<Classification> classifications = (List) classificationFacade.findAll();
        Collections.reverse(classifications);
        getServletContext().setAttribute("classifications", classifications);
        getServletContext().setAttribute("documentFacade", documentFacade);
        getServletContext().setAttribute("projectFacade", projectFacade);
        getServletContext().setAttribute("symbolFacade", symbolFacade);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        session = request.getSession(false);
        String userPath = request.getServletPath();
        
        
        
        if (userPath.equals("/get/classify")) {

            
            try {
                if (request.getParameter("sy") != null) {
                    Symbol symbol = symbolFacade.find(Integer.parseInt(request.getParameter("sy")));
                    request.setAttribute("symbol", symbol);
                    request.setAttribute("log", symbolFacade.findLastLog(symbol));
                    request.setAttribute("submitAction", "/do/updateSymbol");
                } else {
                    Document document = documentFacade.find(Integer.parseInt(request.getParameter("dc")));
                    String name = request.getParameter("sn");
                    try {
                        Symbol symbol = symbolFacade.findByDocumentAndName(document, name);
                        request.setAttribute("symbol", symbol);
                        request.setAttribute("log", symbolFacade.findLastLog(symbol));
                        request.setAttribute("submitAction", "/do/updateSymbol");
                    } catch (Exception e) {
                        Symbol symbol = new Symbol();
                        symbol.setDocument(document);
                        symbol.setName(name);
                        request.setAttribute("symbol", symbol);
                        request.setAttribute("submitAction", "/do/createSymbol");
                    }
                }
            } catch (Exception e) {}
            
        } else if (userPath.equals("/get/document")) {

            
            if (session.getAttribute("document") == null) userPath = "/get/loadDocument";
            if (session.getAttribute("project") == null) userPath = "/get/loadProject";
  
            
        } else if (userPath.equals("/get/explore")) {
            
            
            if (session.getAttribute("project") == null) userPath = "/get/loadProject";
            
            
        } else if (userPath.equals("/get/loadDocument")) {
            
            
            
            
        } else if(userPath.equals("/get/loadProject")) {
        
            
        
        
        } else if (userPath.equals("/get/test")) {
            
           
            String foo = "Esto es una prueba del sistema LeL.";
            request.setAttribute("foo", foo);
            
            
        } else if (userPath.equals("/signIn")) {
            
            
        }
        
        
        String responseView = "/WEB-INF/view" + userPath + ".jsp";
        request.setAttribute("userPath", userPath);
        try {
            request.getRequestDispatcher(responseView).forward(request, response);
        } catch (Exception e) {}
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        session = request.getSession(false);
        String userPath = request.getServletPath();
        
        
        
        if(userPath.equals("/do/chooseLanguage")) {
        
            
            try {
                String language = request.getParameter("language");
                Cookie languageCookie = new Cookie("language", language);
                languageCookie.setMaxAge(60*60*24*365);
                languageCookie.setPath(request.getContextPath());
                response.addCookie(languageCookie);
                request.setAttribute("success", true);
            } catch (Exception e) {
                request.setAttribute("success", false);
            }

                        
        } else if (userPath.equals("/do/createDocument")) {
            
            
            try {
                Document document = documentFacade.createDocument(
                        request.getParameter("name"), 
                        (Project) session.getAttribute("project"));
                session.setAttribute("document", document);
                request.setAttribute("success", true);
            } catch (Exception e) {
                request.setAttribute("success", false);
            }
            
            
        } else if(userPath.equals("/do/createProject")) {
            
            
            try {
                Project project = projectFacade.createProject(
                        request.getParameter("name"), 
                        (User) session.getAttribute("user"));
                session.setAttribute("project", project);
                session.setAttribute("document", null);
                request.setAttribute("success", true);
            } catch (Exception e) {
                request.setAttribute("success", false);
            }
            
            
        } else if (userPath.equals("/do/createSymbol")) {
            
            
            try {
                symbolFacade.createSymbol(
                    (Project) session.getAttribute("project"), 
                    (User) session.getAttribute("user"), 
                    documentFacade.find(Integer.parseInt(request.getParameter("document"))), 
                    request.getParameter("name"), 
                    categoryFacade.find(Integer.parseInt(request.getParameter("category"))), 
                    classificationFacade.find(Integer.parseInt(request.getParameter("classification"))), 
                    request.getParameter("notion"), request.getParameter("actualIntention"), 
                    request.getParameter("futureIntention"), request.getParameter("comments"));
                
                request.setAttribute("success", true);
            } catch (Exception e) {
                request.setAttribute("success", false);
            }
            
            
        } else if (userPath.equals("/do/loadDocument")) {
            

            try {
                Document document = documentFacade.find(Integer.parseInt(request.getParameter("document")));
                session.setAttribute("document", document);
                request.setAttribute("success", true);
            } catch (Exception e) {
                request.setAttribute("success", false);
            }

            
        } else if(userPath.equals("/do/loadProject")) {
        

            try {
                Project project = projectFacade.find(Integer.parseInt(request.getParameter("project")));
                session.setAttribute("project", project);
                session.setAttribute("document", null);
                request.setAttribute("success", true);
            } catch (Exception e) {
                request.setAttribute("success", false);
            }

                        
        } else if (userPath.equals("/do/signIn")) {
            
            
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            try {
                User user = userFacade.signIn(username, password);
                session.setAttribute("user", user);
                request.setAttribute("success", true);
            } catch (Exception e) {
                request.setAttribute("success", false);
            }
            
            
        } else if (userPath.equals("/do/signOut")) {
            
            
            try {
                session.setAttribute("user", null);
                session.invalidate();
                request.setAttribute("success", true);
            } catch (Exception e) {
                request.setAttribute("success", false);
            }
            
            
        } else if (userPath.equals("/do/updateDocument")) {
            
            
            try {
                Document document = documentFacade.updateDocumentContent(
                        Integer.parseInt(request.getParameter("document")), 
                        request.getParameter("content"));
                
                session.setAttribute("document", document);
                request.setAttribute("success", true);
            } catch (Exception e) {
                request.setAttribute("success", false);
            }
            
            
        } else if (userPath.equals("/do/updateSymbol")) {
            
            
            try {
                symbolFacade.updateSymbol(
                    (User) session.getAttribute("user"), 
                    Integer.parseInt(request.getParameter("symbol")), 
                    categoryFacade.find(Integer.parseInt(request.getParameter("category"))),
                    classificationFacade.find(Integer.parseInt(request.getParameter("classification"))),
                    request.getParameter("notion"),
                    request.getParameter("actualIntention"),
                    request.getParameter("futureIntention"),
                    request.getParameter("comments"));

                request.setAttribute("success", true);
            } catch (Exception e) {
                request.setAttribute("success", false);
            }
            
            
        } 
        
        
        
        String responseView = "/WEB-INF/view" + userPath + ".jsp";
        request.setAttribute("userPath", userPath);
        try {
            request.getRequestDispatcher(responseView).forward(request, response);
        } catch (Exception e) {}
    }    
        
}