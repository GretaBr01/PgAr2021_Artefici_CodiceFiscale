package cdf;

import java.io.FileInputStream;
import java.util.ArrayList;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

public class OutputXML {

	public static void scriviXML(ArrayList<Persona> persone) {
		XMLOutputFactory xmlof = null;
		XMLStreamWriter xmlw = null;
		try {
		 xmlof = XMLOutputFactory.newInstance();
		 xmlw = xmlof.createXMLStreamWriter(new FileOutputStream(filename), “utf-8”);
		 xmlw.writeStartDocument(“utf-8”, “1.0”);
		} catch (Exception e) {
		 System.out.println("Errore nell'inizializzazione del writer:");
		 System.out.println(e.getMessage());
		}

	}
}
