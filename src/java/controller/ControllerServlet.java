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

import entity.Classification;
import entity.Concept;
import java.io.IOException;
import java.util.List;
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
urlPatterns = {"/explore",
                "/document",
                "/classify",
                "/load",
                "/createConcept",
                "/deleteConcept"})
public class ControllerServlet extends HttpServlet {
    @EJB private DocumentFacade documentFacade;
    @EJB private ConceptFacade conceptFacade;
    @EJB private CategoryFacade categoryFacade;
    @EJB private ClassificationFacade classificationFacade;
    @EJB private ConceptManager conceptManager;
   
    @Override
    public void init() throws ServletException {
        getServletContext().setAttribute("classifications", classificationFacade.findAll());
        getServletContext().setAttribute("categories", categoryFacade.findAll());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        String userPath = request.getServletPath();

        if (userPath.equals("/explore")) {
            
            String classificationParam = request.getParameter("classification");
            String categoryParam = request.getParameter("category");
            List<Concept> concepts = conceptManager.getConcepts(classificationParam, categoryParam);
            request.setAttribute("concepts", concepts);
            
        } else if (userPath.equals("/document")) {


        } else if (userPath.equals("/classify")) {
            
            
        } else if (userPath.equals("/load")) {


        }

        String url = "/WEB-INF/view" + userPath + ".jsp";
        request.setAttribute("userPath", userPath);
        
        try {
            request.getRequestDispatcher(url).forward(request, response);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        String userPath = request.getServletPath();
        
        if (userPath.equals("/createConcept")) {
            String nameParam = request.getParameter("name");
            String documentParam = "1";
            String categoryParam = request.getParameter("category");
            String classificationParam = request.getParameter("classification");
            String notionParam = request.getParameter("notion");
            String actualIntentionParam = request.getParameter("actualIntention");
            String futureIntentionParam = request.getParameter("futureIntention");
            String commentsParam = request.getParameter("comments");
            
            int conceptId = conceptManager.createConcept(nameParam, documentParam, categoryParam, 
                    classificationParam, notionParam, actualIntentionParam, futureIntentionParam, commentsParam);
            request.setAttribute("createConceptError", conceptId < 0 ? true : false);
            
            userPath = "/classify";
            
        } else if (userPath.equals("/deleteConcept")) {
            
            
            userPath = "/explore";
        }

        String url = "/WEB-INF/view" + userPath + ".jsp";
        request.setAttribute("userPath", userPath);

        try {
            request.getRequestDispatcher(url).forward(request, response);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}