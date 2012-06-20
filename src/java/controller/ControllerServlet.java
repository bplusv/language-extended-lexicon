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

import business.DocumentManager;
import business.ProjectManager;
import business.SymbolManager;
import business.UserManager;
import entity.*;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import session.*;

/**
 *
 * @author Luis Salazar <bp.lusv@gmail.com>
 */
@WebServlet(name = "ControllerServlet",
loadOnStartup = 1,
urlPatterns = {"/chooseLanguage",
                "/classify",
                "/document",
                "/explore",
                "/loadDocument",
                "/loadProject",
                "/signIn",
                "/test",
                "/doCreateDocument",
                "/doCreateProject",
                "/doCreateSymbol",
                "/doLoadDocument",
                "/doLoadProject",
                "/doSignIn",
                "/doSignOut",
                "/doUpdateDocument",
                "/doUpdateSymbol"})
public class ControllerServlet extends HttpServlet {
    @EJB private CategoryFacade categoryFacade;
    @EJB private ClassificationFacade classificationFacade;
    @EJB private DocumentFacade documentFacade;
    @EJB private ProjectFacade projectFacade;
    @EJB private SymbolFacade symbolFacade;
    @EJB private UserFacade userFacade;
    @EJB private DocumentManager documentManager;
    @EJB private ProjectManager projectManager;
    @EJB private SymbolManager symbolManager;
    @EJB private UserManager userManager;
    private HttpSession session;

    @Override
    public void init() throws ServletException {
        List<Category> categories = categoryFacade.findAll();
        Collections.reverse(categories);
        getServletContext().setAttribute("categories", categories);
        List<Classification> classifications = classificationFacade.findAll();
        Collections.reverse(classifications);
        getServletContext().setAttribute("classifications", classifications);
        getServletContext().setAttribute("documentFacade", documentFacade);
        getServletContext().setAttribute("projectFacade", projectFacade);
        getServletContext().setAttribute("documentManager", documentManager);
        getServletContext().setAttribute("projectManager", projectManager);
        getServletContext().setAttribute("symbolManager", symbolManager);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        session = request.getSession(false);
        String userPath = request.getServletPath();
        
        
        if(userPath.equals("/chooseLanguage")) {
        
            
            String language = request.getParameter("language");
            request.setAttribute("language", language);
            Cookie langCookie = new Cookie("language", language);
            langCookie.setMaxAge(60*60*24*365);
            response.addCookie(langCookie);
            try {
                response.sendRedirect("/lel");
            } catch (Exception ex) {}
            return;
            
            
        } else if (userPath.equals("/classify")) {
            
            
            String symbolParam = request.getParameter("sy");
            if (symbolParam != null) {
                Symbol symbol = symbolManager.getSymbol(symbolParam);
                Log log = symbolManager.getLog(symbolParam);
                request.setAttribute("symbol", symbol);
                request.setAttribute("log", log);
                request.setAttribute("submitAction", "/doUpdateSymbol");
            } else {
                String documentParam = request.getParameter("document");
                String nameParam = request.getParameter("name");
                Symbol symbol = symbolManager.getSymbolByDocAndName(documentParam, nameParam);
                if (symbol != null) {
                    symbolParam = symbol.getId().toString();
                    Log log = symbolManager.getLog(symbolParam);
                    request.setAttribute("symbol", symbol);
                    request.setAttribute("log", log);
                    request.setAttribute("submitAction", "/doUpdateSymbol");   
                } else {
                    symbol = symbolManager.createPossibleSymbol(nameParam, documentParam);
                    request.setAttribute("symbol", symbol);
                    request.setAttribute("submitAction", "/doCreateSymbol");
                }
            }
            
            
        } else if (userPath.equals("/document")) {

            
            if (session.getAttribute("document") == null) userPath = "/loadDocument";
            if (session.getAttribute("project") == null) userPath = "/loadProject";
  
            
        } else if (userPath.equals("/explore")) {
            
            
            if (session.getAttribute("project") == null) userPath = "/loadProject";
            
            
        } else if (userPath.equals("/loadDocument")) {
            
            
            
            
        } else if(userPath.equals("/loadProject")) {
        
            
        
        
        } else if (userPath.equals("/signIn")) {
            
            
            
            
        } else if (userPath.equals("/test")) {
            
            
            String foo = "Esto es una prueba del sistema LeL.";
            request.setAttribute("foo", foo);
            
            
        }

        
        
        String responseView = "/WEB-INF/view/html" + userPath + ".jsp";
        request.setAttribute("userPath", userPath);
        try {
            request.getRequestDispatcher(responseView).forward(request, response);
        } catch (Exception ex) {}
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        session = request.getSession(false);
        String userPath = request.getServletPath();
        
        
        
        if (userPath.equals("/doCreateDocument")) {
            
            
            String nameParam = request.getParameter("name");
            String projectParam = ((Project) session.getAttribute("project")).getId().toString();
            Document document = documentManager.createDocument(nameParam, projectParam);
            if (document != null) {
                session.setAttribute("document", document);
                request.setAttribute("success", true);
            } else {
                request.setAttribute("success", false);
            }
        
            
        } else if(userPath.equals("/doCreateProject")) {
            
            
            String nameParam = request.getParameter("name");
            String userParam = ((User) session.getAttribute("user")).getId().toString();
            Project project = projectManager.createProject(nameParam, userParam);
            if (project != null) {
                session.setAttribute("project", project);
                session.setAttribute("document", null);
                request.setAttribute("success", true);
            } else {
                request.setAttribute("success", false);
            }
            
            
        } else if (userPath.equals("/doCreateSymbol")) {
            
            
            String userParam = ((User) session.getAttribute("user")).getId().toString();
            String projectParam = ((Project) session.getAttribute("project")).getId().toString();
            String documentParam = request.getParameter("document");
            String nameParam = request.getParameter("name");
            String categoryParam = request.getParameter("category");
            String classificationParam = request.getParameter("classification");
            String notionParam = request.getParameter("notion");
            String actualIntentionParam = request.getParameter("actualIntention");
            String futureIntentionParam = request.getParameter("futureIntention");
            String commentsParam = request.getParameter("comments");
            Symbol symbol = symbolManager.createSymbol(userParam, projectParam, documentParam,
                    nameParam, categoryParam, classificationParam, notionParam, actualIntentionParam,
                    futureIntentionParam, commentsParam);
            String symbolParam = symbol != null ? symbol.getId().toString() : "";
            Log log = symbolManager.getLog(symbolParam);
            request.setAttribute("success", symbol != null ? true : false);
            request.setAttribute("symbol", symbol);
            request.setAttribute("log", log);
            request.setAttribute("submitAction", "/doUpdateSymbol");

            
        } else if (userPath.equals("/doLoadDocument")) {
            
            
            String documentParam = request.getParameter("document");
            Document document = documentManager.getDocument(documentParam);
            if (document != null) {
                session.setAttribute("document", document);
                request.setAttribute("success", true);
            } else {
                request.setAttribute("success", false);
            }

            
        } else if(userPath.equals("/doLoadProject")) {
        
            
            String projectParam = request.getParameter("project");
            Project project = projectManager.getProject(projectParam);
            if (project != null) {
                session.setAttribute("project", project);
                session.setAttribute("document", null);
                
                request.setAttribute("success", true);
            } else {
                request.setAttribute("success", false);
            }
            
            
        } else if (userPath.equals("/doSignIn")) {
            
            
            String usernameParam = request.getParameter("username");
            String passwordParam = request.getParameter("password");
            User user = userManager.signIn(usernameParam, passwordParam);
            if (user != null) {
                session.setAttribute("user", user);
                request.setAttribute("success", true);
                
            } else {
                request.setAttribute("success", false);
            }
            
            
        } else if (userPath.equals("/doSignOut")) {
            
            
            try {
                session.setAttribute("user", null);
                session.invalidate();
                request.setAttribute("success", true);
            } catch (Exception ex) {
                request.setAttribute("success", false);
            }
            
            
        } else if (userPath.equals("/doUpdateDocument")) {
            
            
            String documentParam = request.getParameter("document");
            String dataParam = request.getParameter("data");
            Document document = documentManager.updateDocument(documentParam, dataParam);
            if (document != null) {
                session.setAttribute("document", document);
                request.setAttribute("success", true);
            } else {
                request.setAttribute("success", false);
            }
            
            
        } else if (userPath.equals("/doUpdateSymbol")) {
            
            
            String userParam = ((User) session.getAttribute("user")).getId().toString();
            String symbolParam = request.getParameter("symbol");
            String categoryParam = request.getParameter("category");
            String classificationParam = request.getParameter("classification");
            String notionParam = request.getParameter("notion");
            String actualIntentionParam = request.getParameter("actualIntention");
            String futureIntentionParam = request.getParameter("futureIntention");
            String commentsParam = request.getParameter("comments");
            Symbol symbol = symbolManager.updateSymbol(userParam, symbolParam, categoryParam,
                    classificationParam, notionParam, actualIntentionParam,
                    futureIntentionParam, commentsParam);
            Log log = symbolManager.getLog(symbolParam);
            request.setAttribute("symbol", symbol != null ? symbol : symbolManager.getSymbol(symbolParam));
            request.setAttribute("log", log);
            request.setAttribute("success", symbol != null ? true : false);

            
        } 
        
        
        
        String responseView = "/WEB-INF/view/xml" + userPath + ".jsp";
        request.setAttribute("userPath", userPath);
        try {
            request.getRequestDispatcher(responseView).forward(request, response);
        } catch (Exception ex) {}
    }
}