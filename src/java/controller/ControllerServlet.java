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

import entity.ConceptClassification;
import entity.Document;
import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import session.ConceptClassificationFacade;
import session.ConceptFacade;
import session.ConceptManager;
import session.DocumentFacade;

/**
 *
 * @author Luis Salazar <bp.lusv@gmail.com>
 */
@WebServlet(name = "ControllerServlet",
loadOnStartup = 1,
urlPatterns = {"/explore",
                "/lookup",
                "/classify",
                "/load",
                "/createConcept",
                "/deleteConcept",
                "/test"})
public class ControllerServlet extends HttpServlet {
    
    @EJB
    private ConceptFacade conceptFacade;
    @EJB
    private ConceptClassificationFacade conceptClassificationFacade;
    @EJB
    private ConceptManager conceptManager;
    @EJB
    private DocumentFacade documentFacade;

    
    @Override
    public void init() throws ServletException {
        getServletContext().setAttribute("conceptClassifications", conceptClassificationFacade.findAll());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String userPath = request.getServletPath();

        if (userPath.equals("/explore")) {
            // TODO: Implement explore page request

            userPath = "/explore";

        } else if (userPath.equals("/lookup")) {
            // TODO: Implement lookup page request

        } else if (userPath.equals("/classify")) {

        } else if (userPath.equals("/load")) {
            // TODO: Implement load request

        } else if (userPath.equals("/test")) {
            HttpSession session = request.getSession();
            String conceptClassificationId = request.getQueryString();
            if (conceptClassificationId != null) {
                ConceptClassification selectedConceptClassification = conceptClassificationFacade.find(Integer.parseInt(conceptClassificationId));               
               
                session.setAttribute("conceptClassifications", conceptClassificationFacade.findAll());
                session.setAttribute("selectedConceptClassification", selectedConceptClassification);
 
            }
            
            session.setAttribute("currentConcept", "current");
            session.setAttribute("currentDocument", documentFacade.find(1));
            
            // userPath = "/test";
        }

        // use RequestDispatcher to forward request internally
        String url = "/WEB-INF/view" + userPath + ".jsp";

        try {
            request.getRequestDispatcher(url).forward(request, response);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String userPath = request.getServletPath();

        if (userPath.equals("/createConcept")) {
            String name = request.getParameter("name");
            int document = 1;
            int classification = Integer.parseInt(request.getParameter("classification"));
            int category = Integer.parseInt(request.getParameter("category"));
            String notion = request.getParameter("notion");
            String actualIntention = request.getParameter("actualIntention");
            String futureIntention = request.getParameter("futureIntention");
            String comments = request.getParameter("comments");
            
            int res = conceptManager.createConcept(name, document, category, classification, notion, actualIntention, futureIntention, comments);
            
            userPath = "/explore";
            
        } else if (userPath.equals("/deleteConcept")) {
            // TODO: Implement deleteConcept action

            userPath = "/explore";
        }

        // use RequestDispatcher to forward request internally
        String url = "/WEB-INF/view" + userPath + ".jsp";

        try {
            request.getRequestDispatcher(url).forward(request, response);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}