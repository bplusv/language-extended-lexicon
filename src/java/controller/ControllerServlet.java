package controller;

import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import session.ConceptFacade;

@WebServlet(name = "ControllerServlet",
loadOnStartup = 1,
urlPatterns = {"/explore",
                "/lookup",
                "/classify",
                "/load",
                "/saveConcept",
                "/deleteConcept",
                "/prueba"})
public class ControllerServlet extends HttpServlet {

    @EJB
    private ConceptFacade conceptFacade;

    @Override
    public void init() throws ServletException {
        getServletContext().setAttribute("concepts", conceptFacade.findAll());
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
            // TODO: Implement classify request
        } else if (userPath.equals("/load")) {
            // TODO: Implement load request
        } else if (userPath.equals("/prueba")) {
            // userPath = "/prueba";
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

        if (userPath.equals("/saveConcept")) {
            // TODO: Implement saveConcept action
        } else if (userPath.equals("/deleteConcept")) {
            // TODO: Implement deleteConcept action

            userPath = "/confirmation";
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
