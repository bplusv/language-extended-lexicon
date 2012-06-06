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
                "/test",
                "/doCreateConcept",
                "/doCreateDocument",
                "/doCreateProject",
                "/doDeleteConcept",
                "/doLoadDocument",
                "/doLoadProject",
                "/doSignIn",
                "/doSignOut",
                "/doUpdateConcept",
                "/doUpdateDocument"})
public class ControllerServlet extends HttpServlet {
    @EJB private DocumentManager documentManager;
    @EJB private DocumentFacade documentFacade;
    @EJB private ConceptFacade conceptFacade;
    @EJB private CategoryFacade categoryFacade;
    @EJB private ClassificationFacade classificationFacade;
    @EJB private ConceptManager conceptManager;
    @EJB private UserFacade userFacade;
    @EJB private ProjectFacade projectFacade;
    @EJB private ProjectManager projectManager;
    @EJB private UserManager userManager;
    private HttpSession session;

    @Override
    public void init() throws ServletException {
        getServletContext().setAttribute("classifications", classificationFacade.findAll());
        getServletContext().setAttribute("categories", categoryFacade.findAll());
        getServletContext().setAttribute("documentFacade", documentFacade);
        getServletContext().setAttribute("conceptManager", conceptManager);
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
        //String lang = request.getLocale().getLanguage();
        
        if (userPath.equals("/classify")) {
            
            
            String conceptParam = request.getParameter("co");
            Concept concept = conceptManager.getConcept(conceptParam);
            Log log = conceptManager.getLog(conceptParam);
            
            request.setAttribute("concept", concept);
            request.setAttribute("log", log);
            request.setAttribute("submitAction", "/doUpdateConcept");
            
            
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
        
            
        
        
        }else if (userPath.equals("/test")) {
            
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
            
            Concept concept = conceptManager.getConceptByDocAndName(documentParam, nameParam);
            
            if (concept != null) {
                String conceptParam = concept.getId().toString();
                Log log = conceptManager.getLog(conceptParam);
                request.setAttribute("concept", concept);
                request.setAttribute("log", log);
                request.setAttribute("submitAction", "/doUpdateConcept");   
            } else {
                concept = conceptManager.createPossibleConcept(nameParam, documentParam);
                request.setAttribute("concept", concept);
                request.setAttribute("submitAction", "/doCreateConcept");
            }
            
            
        } else if (userPath.equals("/doCreateConcept")) {
            
            
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
            
            
            Concept concept = conceptManager.createConcept(userParam, projectParam, documentParam,
                    nameParam, categoryParam, classificationParam, notionParam, actualIntentionParam,
                    futureIntentionParam, commentsParam);
            String conceptParam = concept != null ? concept.getId().toString() : "";
            Log log = conceptManager.getLog(conceptParam);
            
            request.setAttribute("createConceptFail", concept != null ? false : true);
            request.setAttribute("concept", concept);
            request.setAttribute("log", log);
            request.setAttribute("submitAction", "/doUpdateConcept");
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
        
        
        } else if (userPath.equals("/doDeleteConcept")) {
            
            
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
                userPath = "/loadProject";
            } else {
                request.setAttribute("signInError", true);
                try {
                    request.getRequestDispatcher("/index.jsp").forward(request, response);
                } catch (Exception ex) {}
                return;
            }
            
            
        } else if (userPath.equals("/doSignOut")) {
            
            
            session.setAttribute("user", null);
            session.invalidate();
            
            try {
                request.getRequestDispatcher("/index.jsp").forward(request, response);
            } catch (Exception ex) {}
            return;
            
            
        } else if (userPath.equals("/doUpdateConcept")) {
            
            
            String userParam = ((User) session.getAttribute("user")).getId().toString();
            String conceptParam = request.getParameter("concept");
            String categoryParam = request.getParameter("category");
            String classificationParam = request.getParameter("classification");
            String notionParam = request.getParameter("notion");
            String actualIntentionParam = request.getParameter("actualIntention");
            String futureIntentionParam = request.getParameter("futureIntention");
            String commentsParam = request.getParameter("comments");
            
            Concept concept = conceptManager.updateConcept(userParam, conceptParam, categoryParam,
                    classificationParam, notionParam, actualIntentionParam,
                    futureIntentionParam, commentsParam);
            Log log = conceptManager.getLog(conceptParam);
            
            request.setAttribute("concept", concept != null ? concept : conceptManager.getConcept(conceptParam));
            request.setAttribute("log", log);
            request.setAttribute("updateConceptFail", concept != null ? false : true);
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