package com.campusdual.classroom;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;

public class XMLFileCreator {
    public static void createDocument()  {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;
        try {
            builder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        }
        Document document = builder.newDocument();

        Element root = document.createElement("shoppinglist");
        document.appendChild(root);
        Element items = document.createElement("items");
        root.appendChild(items);

        items.appendChild(XMLFileCreator.createXMLItem(document, "2", "Manzana", "item"));
        items.appendChild(XMLFileCreator.createXMLItem(document, "1", "Leche", "item"));
        items.appendChild(XMLFileCreator.createXMLItem(document, "3", "Pan", "item"));
        items.appendChild(XMLFileCreator.createXMLItem(document, "2", "Huevo", "item"));
        items.appendChild(XMLFileCreator.createXMLItem(document, "1", "Queso", "item"));
        items.appendChild(XMLFileCreator.createXMLItem(document, "1", "Cereal", "item"));
        items.appendChild(XMLFileCreator.createXMLItem(document, "4", "Agua", "item"));
        items.appendChild(XMLFileCreator.createXMLItem(document, "6", "Yogur", "item"));
        items.appendChild(XMLFileCreator.createXMLItem(document, "2", "Arroz", "item"));

        try {
            XMLFileCreator.writeXMLtoFile(document, "src/main/resources/shoppingList.xml");
        } catch (TransformerException e) {
            throw new RuntimeException(e);
        } finally {
            System.out.println("XML successfully written!");
        }
    }

    private static void writeXMLtoFile(Document document, String pathName) throws TransformerException {
        TransformerFactory factory = TransformerFactory.newInstance();
        Transformer transformer = factory.newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty("{https://xml.apache.org/xslt}indent-amount", "3");

        File file = new File(pathName);
        DOMSource source = new DOMSource(document);
        StreamResult result = new StreamResult(file);
        transformer.transform(source, result);
    }

    private static Element createXMLItem(Document document, String quantity, String article, String tag) {
        Element item = document.createElement(tag);
        item.setAttribute("quantity", quantity);
        item.setTextContent(article);
        return item;
    }
}