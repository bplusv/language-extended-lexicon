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
package session;

import java.io.ByteArrayOutputStream;
import java.util.Collection;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.SessionContext;
import org.apache.fop.apps.FopFactory;
import org.apache.fop.apps.Fop;
import org.apache.fop.apps.MimeConstants;
import org.apache.fop.servlet.ServletContextURIResolver;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.URIResolver;
import javax.xml.transform.sax.SAXResult;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import model.Category;
import model.Classification;
import model.Definition;
import model.Project;
import model.Symbol;

/**
 *
 * @author Luis Salazar <bp.lusv@gmail.com>
 */
public class ReportManager {
    @Resource protected SessionContext context;

    protected FopFactory fopFactory = FopFactory.newInstance();
    protected TransformerFactory tFactory = TransformerFactory.newInstance();
    //protected URIResolver uriResolver = new ServletContextURIResolver();
    
    public ByteArrayOutputStream makeProjectReportPdf(String projectId, Source xsltSrc, ProjectFacade projectFacade, DefinitionFacade definitionFacade) {
        try {
            ByteArrayOutputStream out = new ByteArrayOutputStream();

            //Setup FOP
            Fop fop = fopFactory.newFop(MimeConstants.MIME_PDF, out);

            //Setup Transformer
            //Source xsltSrc = uriResolver.resolve("servlet-context:/WEB-INF/report/projectReport.xsl", null);
            Transformer transformer = tFactory.newTransformer(xsltSrc);

            //Make sure the XSL transformation's result is piped through to FOP
            Result res = new SAXResult(fop.getDefaultHandler());

            // XML source creation -------------------------------------------------------

            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

            // root elements
            org.w3c.dom.Document doc = docBuilder.newDocument();
            org.w3c.dom.Element rootElement = doc.createElement("root");
            doc.appendChild(rootElement);

            // symbol elements
            org.w3c.dom.Element definitionsElement = doc.createElement("definitions");
            rootElement.appendChild(definitionsElement);

            Collection<Definition> definitions = projectFacade.getDefinitionCollection(projectId);

            org.w3c.dom.Element definitionElement;
            org.w3c.dom.Element classificationElement;
            org.w3c.dom.Element categoryElement;
            org.w3c.dom.Element symbolsElement;
            org.w3c.dom.Element symbolElement;
            org.w3c.dom.Element symbolNameElement;
            org.w3c.dom.Element notionElement;
            org.w3c.dom.Element actualIntentionElement;
            org.w3c.dom.Element futureIntentionElement;
            for (Definition definition : definitions) {
                definitionElement = doc.createElement("definition");

                classificationElement = doc.createElement("classification");
                Classification classification = definition.getClassification();
                String classificationText = classification != null ? classification.getName() : "N/A";
                classificationElement.appendChild(doc.createCDATASection(classificationText));
                definitionElement.appendChild(classificationElement);

                categoryElement = doc.createElement("category");
                Category category = definition.getCategory();
                String categoryText = category != null ? category.getName() : "N/A";
                categoryElement.appendChild(doc.createCDATASection(categoryText));
                definitionElement.appendChild(categoryElement);

                symbolsElement = doc.createElement("symbols");
                Collection<Symbol> synonyms = definitionFacade.getSynonymsGroup(definition.getId().toString());
                for (Symbol synonym : synonyms) {
                    symbolElement = doc.createElement("symbol");
                    symbolNameElement = doc.createElement("name");
                    symbolNameElement.appendChild(doc.createCDATASection(synonym.getName()));
                    symbolElement.appendChild(symbolNameElement);
                    symbolsElement.appendChild(symbolElement);
                }
                definitionElement.appendChild(symbolsElement);


                notionElement = doc.createElement("notion");
                String notionText = definition.getNotion();
                notionText = notionText != null ? notionText : "N/A";
                notionElement.appendChild(doc.createCDATASection(notionText));
                definitionElement.appendChild(notionElement);

                actualIntentionElement = doc.createElement("actualIntention");
                String actualIntentionText = definition.getActualIntention();
                actualIntentionText = actualIntentionText != null ? actualIntentionText : "N/A";
                actualIntentionElement.appendChild(doc.createCDATASection(actualIntentionText));
                definitionElement.appendChild(actualIntentionElement);

                futureIntentionElement = doc.createElement("futureIntention");
                String futureIntentionText = definition.getFutureIntention();
                futureIntentionText = futureIntentionText != null ? futureIntentionText : "N/A";
                futureIntentionElement.appendChild(doc.createCDATASection(futureIntentionText));
                definitionElement.appendChild(futureIntentionElement);

                definitionsElement.appendChild(definitionElement);
            }

            DOMSource src = new DOMSource(doc);


            // tests ***************************************************************************


            //transformer = tFactory.newTransformer();
//                DOMSource source = new DOMSource(doc);
//                
//                response.setContentType("text/xml;charset=UTF-8");
//                StreamResult resXML = new StreamResult(response.getOutputStream());
//                transformer.transform(source, resXML);

            // tests ***************************************************************************



            // -----------------------------------------------------------------------

            //Start the transformation and rendering process
            transformer.transform(src, res);

            return out;


        } catch (Exception e) {
            int i = 0;
        }
        
        return null;
    }

    protected ByteArrayOutputStream makePdf() {
        
        return null;
    }
}
