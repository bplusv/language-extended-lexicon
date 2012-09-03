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

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import model.*;
import session.*;

/**
 *
 * @author Luis Salazar <bp.lusv@gmail.com>
 */
@WebServlet(name = "ControllerServlet",
loadOnStartup = 1,
urlPatterns = {"/get/data/classifySelectSynonym",
	"/get/data/classifyShowSynonyms",
	"/get/data/exploreSymbols",
	"/get/data/projectSymbols",
	"/get/view/classify",
	"/get/view/document",
	"/get/view/explore",
	"/get/view/loadDocument",
	"/get/view/loadProject",
	"/get/view/test",
	"/signIn",
	"/post/chooseLanguage",
	"/post/createDocument",
	"/post/createProject",
	"/post/createSymbol",
	"/post/leaveSynonymsGroup",
	"/post/loadDocument",
	"/post/loadProject",
	"/post/removeSymbol",
	"/post/signIn",
	"/post/signOut",
	"/post/updateDocument",
	"/post/updateSymbol"})
public class ControllerServlet extends HttpServlet {

	@EJB
	private CategoryFacade categoryFacade;
	@EJB
	private ClassificationFacade classificationFacade;
	@EJB
	private DocumentFacade documentFacade;
	@EJB
	private ProjectFacade projectFacade;
	@EJB
	private SymbolFacade symbolFacade;
	@EJB
	private UserFacade userFacade;
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

		if (userPath.equals("/get/data/classifySelectSynonym")) {
			request.setAttribute("symbol",
					symbolFacade.find(request.getParameter("symbol")));
		} else if (userPath.equals("/get/data/classifyShowSynonyms")) {
			request.setAttribute("synonyms",
					projectFacade.getSymbolCollection(
					((Project) session.getAttribute("project")).getId().toString()));
		} else if (userPath.equals("/get/data/exploreSymbols")) {
			
		} else if (userPath.equals("/get/data/projectSymbols")) {
			
		} else if (userPath.equals("/get/view/classify")) {
			Symbol symbol = request.getParameter("sy") != null
					? symbolFacade.find(request.getParameter("sy"))
					: symbolFacade.findByDocumentAndName(
					request.getParameter("dc"),
					request.getParameter("na"));
			if (symbol != null) {
				request.setAttribute("log",
						symbolFacade.getLastLog(symbol.getId().toString()));
				request.setAttribute("submitAction", "/post/updateSymbol");
			} else {
				symbol = symbolFacade.createPossibleSymbol(
						request.getParameter("dc"), request.getParameter("na"));
				request.setAttribute("submitAction", "/post/createSymbol");
			}
			request.setAttribute("symbol", symbol);
		} else if (userPath.equals("/get/view/document")) {
			if (session.getAttribute("document") == null) {
				userPath = "/get/view/loadDocument";
			}
			if (session.getAttribute("project") == null) {
				userPath = "/get/view/loadProject";
			}
		} else if (userPath.equals("/get/view/explore")) {
			if (session.getAttribute("project") == null) {
				userPath = "/get/view/loadProject";
			}
		} else if (userPath.equals("/get/view/loadDocument")) {
		} else if (userPath.equals("/get/view/loadProject")) {
		} else if (userPath.equals("/get/view/test")) {
			String foo = "Esto es una prueba del sistema LeL.";
			request.setAttribute("foo", foo);
		} else if (userPath.equals("/signIn")) {
		}

		String responseView = "/WEB-INF" + userPath + ".jsp";
		request.setAttribute("userPath", userPath);
		try {
			request.getRequestDispatcher(responseView).forward(request, response);
		} catch (Exception e) {
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		session = request.getSession(false);
		String userPath = request.getServletPath();

		if (userPath.equals("/post/chooseLanguage")) {
			try {
				String language = request.getParameter("language");
				Cookie languageCookie = new Cookie("language", language);
				languageCookie.setMaxAge(60 * 60 * 24 * 365);
				languageCookie.setPath(request.getContextPath());
				languageCookie.setHttpOnly(true);
				response.addCookie(languageCookie);
				request.setAttribute("success", true);
				request.setAttribute("language", language);
			} catch (Exception e) {
				request.setAttribute("success", false);
			}
		} else if (userPath.equals("/post/createDocument")) {
			Document document = documentFacade.createDocument(
					((Project) session.getAttribute("project")).getId().toString(),
					request.getParameter("name"));
			if (document != null) {
				session.setAttribute("document", document);
				request.setAttribute("success", true);
				request.setAttribute("document", document);
			} else {
				request.setAttribute("success", false);
			}
		} else if (userPath.equals("/post/createProject")) {
			Project project = projectFacade.createProject(
					((User) session.getAttribute("user")).getId().toString(),
					request.getParameter("name"));
			if (project != null) {
				session.setAttribute("project", project);
				session.setAttribute("document", null);
				request.setAttribute("success", true);
				request.setAttribute("project", project);
			} else {
				request.setAttribute("success", false);
			}
		} else if (userPath.equals("/post/createSymbol")) {
			Symbol symbol;
			if (request.getParameter("synonym") != null) {
				symbol = symbolFacade.createSymbolBySynonym(
					((Project) session.getAttribute("project")).getId().toString(),
					((User) session.getAttribute("user")).getId().toString(),
					request.getParameter("document"),
					request.getParameter("name"),
					request.getParameter("synonym"));
			} else {
				symbol = symbolFacade.createSymbol(
					((Project) session.getAttribute("project")).getId().toString(),
					((User) session.getAttribute("user")).getId().toString(),
					request.getParameter("document"),
					request.getParameter("name"),
					request.getParameter("category"),
					request.getParameter("classification"),
					request.getParameter("notion"),
					request.getParameter("actualIntention"),
					request.getParameter("futureIntention"),
					request.getParameter("newComment"));
			}
			if (symbol != null) {
				request.setAttribute("success", true);
				request.setAttribute("symbol", symbol);
			} else {
				request.setAttribute("success", false);
			}
		} else if (userPath.equals("/post/leaveSynonymsGroup")) {
			Symbol symbol = symbolFacade.leaveSynonymsGroup(
					((User) session.getAttribute("user")).getId().toString(),
					request.getParameter("symbol"),
					request.getParameter("category"),
					request.getParameter("classification"));
			if (symbol != null) {
				request.setAttribute("success", true);
				request.setAttribute("symbol", symbol);
			} else {
				request.setAttribute("success", false);
			}
		} else if (userPath.equals("/post/loadDocument")) {
			Document document = documentFacade.find(
					request.getParameter("document"));
			if (document != null) {
				session.setAttribute("document", document);
				request.setAttribute("success", true);
				request.setAttribute("document", document);
			} else {
				request.setAttribute("success", false);
			}
		} else if (userPath.equals("/post/loadProject")) {
			Project project = projectFacade.find(
					request.getParameter("project"));

			if (project != null) {
				session.setAttribute("project", project);
				session.setAttribute("document", null);
				request.setAttribute("success", true);
				request.setAttribute("project", project);
			} else {
				request.setAttribute("success", false);
			}
		} else if(userPath.equals("/post/removeSymbol")) {
			Symbol symbol = symbolFacade.removeSymbol(
					request.getParameter("symbol"));
			if (symbol != null) {
				request.setAttribute("success", true);
			} else {
				request.setAttribute("success", false);
			}
		} else if (userPath.equals("/post/signIn")) {
			User user = userFacade.signIn(
					request.getParameter("username"),
					request.getParameter("password"));
			if (user != null) {
				session.setAttribute("user", user);
				request.setAttribute("success", true);
			} else {
				request.setAttribute("success", false);
			}
		} else if (userPath.equals("/post/signOut")) {
			try {
				session.setAttribute("user", null);
				session.invalidate();
				request.setAttribute("success", true);
			} catch (Exception e) {
				request.setAttribute("success", false);
			}
		} else if (userPath.equals("/post/updateDocument")) {
			Document document = documentFacade.updateContent(
					request.getParameter("document"),
					request.getParameter("content"));
			if (document != null) {
				request.setAttribute("success", true);
				session.setAttribute("document", document);
			} else {
				request.setAttribute("success", false);
			}
		} else if (userPath.equals("/post/updateSymbol")) {
			Symbol symbol;
			if (request.getParameter("synonym") != null) {
				symbol = symbolFacade.updateSymbolBySynonym(
					((User) session.getAttribute("user")).getId().toString(),
					request.getParameter("symbol"),
					request.getParameter("synonym"));
			} else {
				symbol = symbolFacade.updateSymbol(
					((User) session.getAttribute("user")).getId().toString(),
					request.getParameter("symbol"),
					request.getParameter("category"),
					request.getParameter("classification"),
					request.getParameter("notion"),
					request.getParameter("actualIntention"),
					request.getParameter("futureIntention"),
					request.getParameter("newComment"));
			}
			if (symbol != null) {
				request.setAttribute("success", true);
				request.setAttribute("symbol", symbol);
			} else {
				request.setAttribute("success", false);
			}
		}

		String responseView = "/WEB-INF" + userPath + ".jsp";
		request.setAttribute("userPath", userPath);
		try {
			request.getRequestDispatcher(responseView).forward(request, response);
		} catch (Exception e) {
		}
	}
}