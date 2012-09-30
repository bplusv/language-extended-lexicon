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
package report;

import java.io.ByteArrayOutputStream;
import java.util.Collection;
import java.util.Locale;
import java.util.ResourceBundle;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.sax.SAXResult;
import javax.xml.transform.stream.StreamSource;
import model.Category;
import model.Classification;
import model.Definition;
import model.Symbol;
import org.apache.fop.apps.Fop;
import org.apache.fop.apps.FopFactory;
import org.apache.fop.apps.MimeConstants;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import session.DefinitionFacade;
import session.ProjectFacade;

/**
 *
 * @author Luis Salazar <bp.lusv@gmail.com>
 */
@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class ReportManager {

    @EJB
    protected ProjectFacade projectFacade;
    @EJB
    protected DefinitionFacade definitionFacade;
    protected FopFactory fopFactory = FopFactory.newInstance();
    protected TransformerFactory tFactory = TransformerFactory.newInstance();

    public ByteArrayOutputStream makeProjectReportPdf(String projectId, String language) {
        try {
            ResourceBundle rb = ResourceBundle.getBundle(
                    "language.messages", Locale.forLanguageTag(language));
            DocumentBuilder docBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();

            Document doc = docBuilder.newDocument();
            Element rootElement = doc.createElement("root");
            doc.appendChild(rootElement);

            Element captionsElement = doc.createElement("captions");
                Element captionsProjectElement = doc.createElement("project");
                captionsProjectElement.appendChild(doc.createCDATASection(rb.getString("project")));
                captionsElement.appendChild(captionsProjectElement);

                Element captionsClassificationElement = doc.createElement("classification");
                captionsClassificationElement.appendChild(doc.createCDATASection(rb.getString("classification")));
                captionsElement.appendChild(captionsClassificationElement);

                Element captionsCategoryElement = doc.createElement("category");
                captionsCategoryElement.appendChild(doc.createCDATASection(rb.getString("category")));
                captionsElement.appendChild(captionsCategoryElement);

                Element captionsSymbolElement = doc.createElement("symbol");
                captionsSymbolElement.appendChild(doc.createCDATASection(rb.getString("symbol")));
                captionsElement.appendChild(captionsSymbolElement);

                Element captionsSymbolsElement = doc.createElement("symbols");
                captionsSymbolsElement.appendChild(doc.createCDATASection(rb.getString("symbols")));
                captionsElement.appendChild(captionsSymbolsElement);

                Element captionsNotionElement = doc.createElement("notion");
                captionsNotionElement.appendChild(doc.createCDATASection(rb.getString("notion")));
                captionsElement.appendChild(captionsNotionElement);

                Element captionsActualIntentionElement = doc.createElement("actualIntention");
                captionsActualIntentionElement.appendChild(doc.createCDATASection(rb.getString("actual intention")));
                captionsElement.appendChild(captionsActualIntentionElement);

                Element captionsFutureIntentionElement = doc.createElement("futureIntention");
                captionsFutureIntentionElement.appendChild(doc.createCDATASection(rb.getString("future intention")));
                captionsElement.appendChild(captionsFutureIntentionElement);
            
                Element captionsPageElement = doc.createElement("page");
                captionsPageElement.appendChild(doc.createCDATASection(rb.getString("page")));
                captionsElement.appendChild(captionsPageElement);

                Element captionsOfElement = doc.createElement("of");
                captionsOfElement.appendChild(doc.createCDATASection(rb.getString("of").toLowerCase()));
                captionsElement.appendChild(captionsOfElement);
            rootElement.appendChild(captionsElement);
            
            Element projectElement = doc.createElement("project");
            Element projectNameElement = doc.createElement("name");
            projectNameElement.appendChild(doc.createCDATASection(projectFacade.find(projectId).getName()));
            projectElement.appendChild(projectNameElement);
            rootElement.appendChild(projectElement);
            
            Element definitionsElement = doc.createElement("definitions");
                Collection<Definition> definitions = projectFacade.getDefinitionCollection(projectId);
                Element definitionElement;
                Element classificationElement;
                Element categoryElement;
                Element symbolsElement;
                Element symbolElement;
                Element symbolNameElement;
                Element notionElement;
                Element actualIntentionElement;
                Element futureIntentionElement;
                for (Definition definition : definitions) {
                    definitionElement = doc.createElement("definition");
                        classificationElement = doc.createElement("classification");
                        Classification classification = definition.getClassification();
                        String classificationText = classification != null ? 
                                rb.getString(classification.getName()) : rb.getString("n/a");
                        classificationElement.appendChild(doc.createCDATASection(classificationText));
                        definitionElement.appendChild(classificationElement);

                        categoryElement = doc.createElement("category");
                        Category category = definition.getCategory();
                        String categoryText = category != null ? 
                                rb.getString(category.getName()) : rb.getString("n/a");
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
                        notionText = notionText != null ? notionText : rb.getString("n/a");
                        notionElement.appendChild(doc.createCDATASection(notionText));
                        definitionElement.appendChild(notionElement);

                        actualIntentionElement = doc.createElement("actualIntention");
                        String actualIntentionText = definition.getActualIntention();
                        actualIntentionText = actualIntentionText != null ? 
                                actualIntentionText : rb.getString("n/a");
                        actualIntentionElement.appendChild(doc.createCDATASection(actualIntentionText));
                        definitionElement.appendChild(actualIntentionElement);

                        futureIntentionElement = doc.createElement("futureIntention");
                        String futureIntentionText = definition.getFutureIntention();
                        futureIntentionText = futureIntentionText != null ? 
                                futureIntentionText : rb.getString("n/a");
                        futureIntentionElement.appendChild(doc.createCDATASection(futureIntentionText));
                        definitionElement.appendChild(futureIntentionElement);
                    definitionsElement.appendChild(definitionElement);
                }
            rootElement.appendChild(definitionsElement);

            DOMSource xmlSource = new DOMSource(doc);
            Source xsltSrc = new StreamSource(
                ReportManager.class.getResourceAsStream("/report/projectReport.xsl"));
            return makePdf(xmlSource, xsltSrc);
        } catch (Exception e) {
            return null;
        }
    }

    protected ByteArrayOutputStream makePdf(Source xmlSource, Source xsltSrc) {
        try {
            ByteArrayOutputStream pdf = new ByteArrayOutputStream();
            Fop fop = fopFactory.newFop(MimeConstants.MIME_PDF, pdf);
            Transformer transformer = tFactory.newTransformer(xsltSrc);
            Result result = new SAXResult(fop.getDefaultHandler());
            transformer.transform(xmlSource, result);
            return pdf;
        } catch (Exception e) {
            return null;
        }
    }
}