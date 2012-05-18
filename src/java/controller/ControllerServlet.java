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

import entity.Concept;
import entity.Document;
import entity.User;
import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
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
                "/createConcept",
                "/deleteConcept",
                "/document",
                "/explore",
                "/load",
                "/loadDocument",
                "/signIn",
                "/signOut",
                "/updateConcept",
                "/updateDocument",
                "/test"})
public class ControllerServlet extends HttpServlet {
    @EJB private DocumentManager documentManager;
    @EJB private DocumentFacade documentFacade;
    @EJB private ConceptFacade conceptFacade;
    @EJB private CategoryFacade categoryFacade;
    @EJB private ClassificationFacade classificationFacade;
    @EJB private ConceptManager conceptManager;
    @EJB private UserFacade userFacade;
    @EJB private UserManager userManager;
   
    @Override
    public void init() throws ServletException {
        getServletContext().setAttribute("classifications", classificationFacade.findAll());
        getServletContext().setAttribute("categories", categoryFacade.findAll());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String userPath = request.getServletPath();
        
        if (userPath.equals("/classify")) {
            String conceptIdParam = request.getParameter("co");
            request.setAttribute("concept", conceptManager.getConcept(conceptIdParam));
            request.setAttribute("submitAction", "/updateConcept");
        } else if (userPath.equals("/document")) {
            
        } else if (userPath.equals("/explore")) {
            String classificationParam = request.getParameter("cl");
            String categoryParam = request.getParameter("ca");
            String conceptParam = request.getParameter("co");
            request.setAttribute("concepts", conceptManager.getConcepts(classificationParam, categoryParam, conceptParam));
        } else if (userPath.equals("/load")) {
            request.setAttribute("documents", documentFacade.findAll());
            
        } else if (userPath.equals("/test")) {
            
        }

        String url = "/WEB-INF/view" + userPath + ".jsp";
        request.setAttribute("userPath", userPath);
        
        try {
            request.getRequestDispatcher(url).forward(request, response);
        } catch (Exception ex) {}
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String userPath = request.getServletPath();
        
        if (userPath.equals("/classify")) {
            String nameParam = request.getParameter("name");
            String documentParam = "1";
            
            request.setAttribute("concept", conceptManager.createPossibleConcept(nameParam, documentParam));
            request.setAttribute("submitAction", "/createConcept");  
        } else if (userPath.equals("/createConcept")) {
            String nameParam = request.getParameter("name");
            String documentParam = request.getParameter("document");
            String categoryParam = request.getParameter("category");
            String classificationParam = request.getParameter("classification");
            String notionParam = request.getParameter("notion");
            String actualIntentionParam = request.getParameter("actualIntention");
            String futureIntentionParam = request.getParameter("futureIntention");
            String commentsParam = request.getParameter("comments");

            Concept concept = conceptManager.createConcept(nameParam, documentParam,
                    categoryParam, classificationParam, notionParam, actualIntentionParam,
                    futureIntentionParam, commentsParam);
            request.setAttribute("createConceptError", concept == null ? true : false);
            
            userPath = "/classify";
        } else if (userPath.equals("/deleteConcept")) {

            userPath = "/explore";

        } else if (userPath.equals("/updateDocument")) {
            
            userPath = "/document";
            
        } else if (userPath.equals("/loadDocument")) {
            
            
            String documentParam = request.getParameter("document");
            Document document = documentManager.getDocument(documentParam);
            
            HttpSession session = request.getSession(false);
            if (session != null && document != null) {
                session.setAttribute("document", document);
            }
            
            request.setAttribute("loadDocumentError", document == null ? true : false);
            
            userPath = document == null ? "/load" : "/document";
            
            
        } else if (userPath.equals("/signIn")) {
            
            String usernameParam = request.getParameter("username");
            String passwordParam = request.getParameter("password");
            
            User user = userManager.signIn(usernameParam, passwordParam);
            if (user != null) {
                HttpSession session = request.getSession();
                session.setAttribute("user", user);
                response.sendRedirect("explore");
            } else {
                request.setAttribute("signInError", true);
                try {
                    request.getRequestDispatcher("/index.jsp").forward(request, response);
                } catch (Exception ex) {}
                return;
            }
            
        } else if (userPath.equals("/signOut")) {
            HttpSession session = request.getSession(false);
            session.setAttribute("user", null);
            session.invalidate();
            try {
                request.getRequestDispatcher("/index.jsp").forward(request, response);
            } catch (Exception ex) {}
            return;
        } else if (userPath.equals("/updateConcept")) {
            String conceptParam = request.getParameter("concept");
            String categoryParam = request.getParameter("category");
            String classificationParam = request.getParameter("classification");
            String notionParam = request.getParameter("notion");
            String actualIntentionParam = request.getParameter("actualIntention");
            String futureIntentionParam = request.getParameter("futureIntention");
            String commentsParam = request.getParameter("comments");

            Concept concept = conceptManager.updateConcept(conceptParam, categoryParam,
                    classificationParam, notionParam, actualIntentionParam,
                    futureIntentionParam, commentsParam);
            request.setAttribute("concept", concept);
            request.setAttribute("updateConceptError", concept == null ? true : false);
            
            userPath = "/classify";
        } else if (userPath.equals("/updateDocument")) {
            userPath = "/document";
        }

        String url = "/WEB-INF/view" + userPath + ".jsp";
        request.setAttribute("userPath", userPath);

        try {
            request.getRequestDispatcher(url).forward(request, response);
        } catch (Exception ex) {}
    }
}