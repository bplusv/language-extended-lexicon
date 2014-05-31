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

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import model.*;
import report.ReportManager;
import session.*;

/**
 *
 * @author Luis Salazar <bp.lusv@gmail.com>
 */
@WebServlet(name = "ReportController",
loadOnStartup = 1,
urlPatterns = {
    "/get/projectReport"
})
public class ReportController extends HttpServlet {

    @EJB
    protected CategoryFacade categoryFacade;
    @EJB
    protected ClassificationFacade classificationFacade;
    @EJB
    protected CommentFacade commentFacade;
    @EJB
    protected DefinitionFacade definitionFacade;
    @EJB
    protected DocumentFacade documentFacade;
    @EJB
    protected EventFacade eventFacade;
    @EJB
    protected LogFacade logFacade;
    @EJB
    protected ProjectFacade projectFacade;
    @EJB
    protected SymbolFacade symbolFacade;
    @EJB
    protected UserFacade userFacade;
    @EJB
    protected ReportManager reportManager;
    @EJB
    protected UserAccessManager userAccessManager;
    private HttpSession session;
    private Logger log = Logger.getLogger(ReportManager.class.getName());

    @Override
    public void init() throws ServletException {
        List<Category> categories = (List) categoryFacade.findAll();
        Collections.reverse(categories);
        getServletContext().setAttribute("categories", categories);
        List<Classification> classifications = (List) classificationFacade.findAll();
        Collections.reverse(classifications);
        getServletContext().setAttribute("classifications", classifications);
        getServletContext().setAttribute("commentFacade", commentFacade);
        getServletContext().setAttribute("definitionFacade", definitionFacade);
        getServletContext().setAttribute("documentFacade", documentFacade);
        getServletContext().setAttribute("eventFacade", eventFacade);
        getServletContext().setAttribute("logFacade", logFacade);
        getServletContext().setAttribute("projectFacade", projectFacade);
        getServletContext().setAttribute("symbolFacade", symbolFacade);
        getServletContext().setAttribute("userFacade", userFacade);
        getServletContext().setAttribute("userAccessManager", userAccessManager);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String userPath = request.getServletPath();
        session = request.getSession(false);
        Project loadedProject = (Project) session.getAttribute("project");
        String loadedProjectId = loadedProject != null ? loadedProject.getId().toString() : "";

        if (userPath.equals("/get/projectReport")) {
            
            try {
                Project project = (Project) session.getAttribute("project");
                Locale locale = (Locale) session.getAttribute("javax.servlet.jsp.jstl.fmt.locale.session");
                String language = locale != null ? locale.getLanguage() : request.getLocale().getLanguage();
                ByteArrayOutputStream pdf = reportManager.makeProjectReportPdf(
                        loadedProjectId, request.getParameter("comments"), language);
                response.setHeader("content-disposition",
                        "attachment; filename=" + project.getName() + ".pdf");
                response.setContentType("application/pdf; charset=UTF-8");
                response.setContentLength(pdf.size());
                response.getOutputStream().write(pdf.toByteArray());
                response.getOutputStream().flush();
            } catch (Exception e) {
                log.log(Level.SEVERE, e.getMessage());
            }
            return;
        
        }

        String responseView = "/WEB-INF" + userPath + ".jsp";
        try {
            request.getRequestDispatcher(responseView).forward(request, response);
        } catch (Exception e) {
        }
    }
}