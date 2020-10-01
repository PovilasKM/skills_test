package com.decathlon_calculator.util;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.StringWriter;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.decathlon_calculator.model.Result;
import com.decathlon_calculator.model.Athlete;

/**
 * @author Mindaugas Jaunius
 */
public class XMLBuilder {

    private static final String XML_VERSION = "1.0";
    private static final String XML_ENCODING = "UTF-8";
    private ArrayList<Athlete> athletes;

    public XMLBuilder(ArrayList<Athlete> athletes) {
        this.athletes = athletes;
        writeXML(constructXML());
        writehtm(constructXML());
    }

//	Private
    public Document constructXML() {

        Document doc = null;
        Element root, athlete, name, place, totalscore, part, event, point;
        try {
            DocumentBuilderFactory dbfac = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = dbfac.newDocumentBuilder();
            doc = docBuilder.newDocument();


            //Creating the XML tree

            //create the root element and add it to the document
            root = doc.createElement("decathlon");
            doc.appendChild(root);

            for (Athlete athleteObject : athletes) {

                //create child element, add an attribute, and add to root
                athlete = doc.createElement("athlete");
                name = doc.createElement("name");
                name.appendChild(doc.createTextNode(athleteObject.getName()));
                athlete.appendChild(name);

                place = doc.createElement("place");
                place.appendChild(doc.createTextNode(athleteObject.getPlace()));
                athlete.appendChild(place);

                totalscore = doc.createElement("totalscore");
                totalscore.appendChild(doc.createTextNode(athleteObject.getTotalScore() + ""));
                athlete.appendChild(totalscore);

                part = doc.createElement("participation");

                for (Result res : athleteObject.getResults()) {
                    event = doc.createElement("event");
                    event.setAttribute("name", res.getName());

                    point = doc.createElement("points");

                    if (res.getMeters()>0) {
                        point.appendChild(doc.createTextNode( res.getMeters()+""));
                        point.setAttribute("unit", "meters");
                    } else {
                        point.appendChild(doc.createTextNode( res.getSeconds()+""));
                        point.setAttribute("unit", "seconds");
                    }
                    event.appendChild(point);
                    part.appendChild(event);
                }
                athlete.appendChild(part);

                root.appendChild(athlete);

            }

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        return doc;
    }
    
    private void generalWrite(Transformer trans,String outputFile, Document doc) {
    	
            trans.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
            trans.setOutputProperty(OutputKeys.METHOD, "xml");
            trans.setOutputProperty(OutputKeys.ENCODING, XML_ENCODING);
            trans.setOutputProperty(OutputKeys.INDENT, "yes");
            trans.setOutputProperty(OutputKeys.VERSION, XML_VERSION);

            StringWriter sw = new StringWriter();
            StreamResult result = new StreamResult(sw);
            DOMSource source = new DOMSource(doc);
            try {
				trans.transform(source, result);
				trans.transform(source, new StreamResult(new FileOutputStream(outputFile)) );
			} catch (TransformerException e) {
				e.printStackTrace();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
            String xmlString = sw.toString();
                System.out.println("Here's the xml:\n\n" + xmlString);
    }
    
    public void writeXML(Document doc) {
        String outputFile = null;
        Transformer trans = null;
        TransformerFactory transfac = TransformerFactory.newInstance();
        	try {
				trans = transfac.newTransformer();
			} catch (TransformerConfigurationException e) {
				e.printStackTrace();
			}
        	outputFile = "outputData.xml";
        	generalWrite(trans, outputFile, doc);
    
    }

    public void writehtm(Document doc) {
        String outputFile = null;
        Transformer trans = null;
            TransformerFactory transfac = TransformerFactory.newInstance();
                try {
					trans = transfac.newTransformer(new StreamSource("styles.xsl"));
				} catch (TransformerConfigurationException e) {
					e.printStackTrace();
				}
                outputFile = "outputData.htm";
            	generalWrite(trans, outputFile, doc);
    }
}
