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
import entity.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import session.*;

/**
 *
 * @author Luis Salazar <bp.lusv@gmail.com>
 */
@WebServlet(name = "ControllerServlet",
loadOnStartup = 1,
urlPatterns = {"/classify",
                "/chooseLanguage",
                "/document",
                "/explore",
                "/loadDocument",
                "/loadProject",
                "/signIn",
                "/test",
                "/doCreateSymbol",
                "/doCreateDocument",
                "/doCreateProject",
                "/doDeleteSymbol",
                "/doLoadDocument",
                "/doLoadProject",
                "/doSignIn",
                "/doSignOut",
                "/doUpdateSymbol",
                "/doUpdateDocument"})
public class ControllerServlet extends HttpServlet {
    @EJB private DocumentManager documentManager;
    @EJB private DocumentFacade documentFacade;
    @EJB private SymbolFacade symbolFacade;
    @EJB private CategoryFacade categoryFacade;
    @EJB private ClassificationFacade classificationFacade;
    @EJB private SymbolManager symbolManager;
    @EJB private UserFacade userFacade;
    @EJB private ProjectFacade projectFacade;
    @EJB private ProjectManager projectManager;
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
        getServletContext().setAttribute("symbolManager", symbolManager);
        getServletContext().setAttribute("documentManager", documentManager);
        getServletContext().setAttribute("projectFacade", projectFacade);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        session = request.getSession(false);
        String userPath = request.getServletPath();
        
        if (userPath.equals("/classify")) {
            
            
            String symbolParam = request.getParameter("sy");
            Symbol symbol = symbolManager.getSymbol(symbolParam);
            Log log = symbolManager.getLog(symbolParam);
            
            request.setAttribute("symbol", symbol);
            request.setAttribute("log", log);
            request.setAttribute("submitAction", "/doUpdateSymbol");
            
            
        } else if(userPath.equals("/chooseLanguage")) {
        
            String language = request.getParameter("language");
            
            request.setAttribute("language", language);
            
            Cookie langCookie = new Cookie("language", language);
            langCookie.setMaxAge(60*60*24*365);
            response.addCookie(langCookie);
            
            
            userPath = "/loadProject";
            
            
        }else if (userPath.equals("/document")) {

            
            if (session.getAttribute("document") == null) userPath = "/loadDocument";
            if (session.getAttribute("project") == null) userPath = "/loadProject";
  
            
        } else if (userPath.equals("/explore")) {
            
            if (session.getAttribute("project") == null) userPath = "/loadProject";

            
        } else if (userPath.equals("/loadDocument")) {
            
            
        } else if(userPath.equals("/loadProject")) {
        
            
        
        
        } if (userPath.equals("/signIn")) {
            
            
        } if (userPath.equals("/test")) {
            
            String foo = "Esto es una prueba del sistema LeL.";
            request.setAttribute("foo", foo);
        }

        String responseView = "/WEB-INF/view" + userPath + ".jsp";
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
        
        if (userPath.equals("/classify")) {
            
            
            String documentParam = request.getParameter("document");
            String nameParam = request.getParameter("name");
            
            Symbol symbol = symbolManager.getSymbolByDocAndName(documentParam, nameParam);
            
            if (symbol != null) {
                String symbolParam = symbol.getId().toString();
                Log log = symbolManager.getLog(symbolParam);
                request.setAttribute("symbol", symbol);
                request.setAttribute("log", log);
                request.setAttribute("submitAction", "/doUpdateSymbol");   
            } else {
                symbol = symbolManager.createPossibleSymbol(nameParam, documentParam);
                request.setAttribute("symbol", symbol);
                request.setAttribute("submitAction", "/doCreateSymbol");
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
            
            request.setAttribute("createSymbolFail", symbol != null ? false : true);
            request.setAttribute("symbol", symbol);
            request.setAttribute("log", log);
            request.setAttribute("submitAction", "/doUpdateSymbol");
            userPath = "/classify";
            
            
        } else if(userPath.equals("/doCreateDocument")) {
            
            
            String nameParam = request.getParameter("name");
            String projectParam = ((Project) session.getAttribute("project")).getId().toString();
            
            Document document = documentManager.createDocument(nameParam, projectParam);
            
            if (document != null) {
                session.setAttribute("document", document);
                request.setAttribute("createDocumentFail", false);
                userPath = "/document";
            } else {
                request.setAttribute("createDocumentFail", true);
                userPath = "/loadDocument";
            }
        
            
        } else if(userPath.equals("/doCreateProject")) {
            
            
            String nameParam = request.getParameter("name");
            String userParam = ((User) session.getAttribute("user")).getId().toString();
            
            
            Project project = projectManager.createProject(nameParam, userParam);
            
            if (project != null) {
                session.setAttribute("project", project);
                session.setAttribute("document", null);
                request.setAttribute("createProjectFail", false);
                userPath = "/loadDocument";
            } else {
                request.setAttribute("createProjectFail", true);
                userPath = "/loadProject";
            }
        
        
        } else if (userPath.equals("/doDeleteSymbol")) {
            
            
            userPath = "/explore";
            
            
        } else if (userPath.equals("/doLoadDocument")) {
            
            
            String documentParam = request.getParameter("document");
            Document document = documentManager.getDocument(documentParam);

            if (document != null) {
                session.setAttribute("document", document);
                userPath = "/document";
            } else {
                request.setAttribute("loadDocumentFail", true);
                userPath = "/loadDocument";
            }

            
        } else if(userPath.equals("/doLoadProject")) {
        
            
            String projectParam = request.getParameter("project");
            Project project = projectManager.getProject(projectParam);
            
            if (project != null) {
                session.setAttribute("project", project);
                session.setAttribute("document", null);
                userPath = "/explore";
            } else {
                request.setAttribute("loadProjectFail", true);
                userPath = "/loadProject";
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
            
            
            session.setAttribute("user", null);
            session.invalidate();
            request.setAttribute("response", true);
            
            
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
            request.setAttribute("updateSymbolFail", symbol != null ? false : true);
            userPath = "/classify";

            
        } else if (userPath.equals("/doUpdateDocument")) {
            
            
            String documentParam = request.getParameter("document");
            String dataParam = request.getParameter("data");
            
            Document document = documentManager.updateDocument(documentParam, dataParam);
            
            if (document != null) {
                session.setAttribute("document", document);
                request.setAttribute("updateDocumentFail", false);
            } else {
                request.setAttribute("updateDocumentFail", true);
            }
            userPath = "/document";
            
            
        }

        
        
        String responseView = "/WEB-INF/view" + userPath + ".jsp";
        request.setAttribute("userPath", userPath);
        try {
            request.getRequestDispatcher(responseView).forward(request, response);
        } catch (Exception ex) {}
    }
}