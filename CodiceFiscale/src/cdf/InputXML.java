package cdf;

import java.io.FileInputStream;
import java.util.ArrayList;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;


public class InputXML {
	
	public static ArrayList<Persona> leggiXMLPersone(String nome_file) {
		ArrayList<Persona> persone= new ArrayList<Persona>();
		Persona persona = null;
		String filename= nome_file;
		
		XMLInputFactory xmlif = null;
		XMLStreamReader xmlr = null;
		try {
			 xmlif = XMLInputFactory.newInstance();
			 xmlr = xmlif.createXMLStreamReader(filename, new FileInputStream(filename));
		} catch (Exception e) {
			System.out.println("Errore nell'inizializzazione del reader:");
			System.out.println(e.getMessage());
		}
		
		try {
			while (xmlr.hasNext()){ 
				 switch (xmlr.getEventType()) { 
					 case XMLStreamConstants.START_DOCUMENT: 
						 System.out.println("Start Read Doc " + filename); 
						 break;
					 case XMLStreamConstants.START_ELEMENT:
						 System.out.println("Tag " + xmlr.getLocalName());
						 switch (xmlr.getLocalName()){
						 	case "persona":						 		
						 		persona= new Persona();
						 		for (int i = 0; i < xmlr.getAttributeCount(); i++) {
						 			System.out.printf(" => attributo %s->%s%n", xmlr.getAttributeLocalName(i), xmlr.getAttributeValue(i));
						 			if(xmlr.getAttributeLocalName(i)=="id") {
						 				persona.setId(xmlr.getAttributeValue(i));
						 			}
						 		}									 
						 		break;
						 	case "nome":
						 		persona.setNome(xmlr.getElementText());						 			
						 		break;
						 	case "cognome":
						 		persona.setCognome(xmlr.getElementText());						 		
						 		break;
						 	case "sesso":
						 		persona.setSesso(xmlr.getElementText());
						 		break;
						 	case "comune_nascita":
						 		persona.setComune_nascita(xmlr.getElementText());
						 		break;
						 	case "data_nascita":
						 		persona.setData_nascita(xmlr.getElementText());		
						 		break;
						 }						 	
						 break;
					 case XMLStreamConstants.END_ELEMENT: // fine di un elemento: stampa il nome del tag chiuso
						 System.out.println("END-Tag " + xmlr.getLocalName()); 
						 if(xmlr.getLocalName().equalsIgnoreCase("persona")) {
						 		persone.add(persona);
						 }
						 break;
				 }
				 try {
					 xmlr.next();
				 }catch (XMLStreamException e) {
					e.printStackTrace();
				 }
			}
		} catch (XMLStreamException e) {
			e.printStackTrace();
		}			
		return persone;
	}
	public static ArrayList<Comune> leggiXMLComune(String nome_file) {
		ArrayList<Comune> comuni= new ArrayList<Comune>();
		Comune comune = null;
		String filename= nome_file;
		
		XMLInputFactory xmlif = null;
		XMLStreamReader xmlr = null;
		try {
			 xmlif = XMLInputFactory.newInstance();
			 xmlr = xmlif.createXMLStreamReader(filename, new FileInputStream(filename));
		} catch (Exception e) {
			System.out.println("Errore nell'inizializzazione del reader:");
			System.out.println(e.getMessage());
		}
		
		try {
			while (xmlr.hasNext()){ 
				 switch (xmlr.getEventType()) { 
					 case XMLStreamConstants.START_DOCUMENT: 
						 System.out.println("Start Read Doc " + filename); 
						 break;
					 case XMLStreamConstants.START_ELEMENT:
						 System.out.println("Tag " + xmlr.getLocalName());
						 switch (xmlr.getLocalName()){
						 	
						   case "comune":						 		
						 		comune= new Comune();
						 		for (int i = 0; i < xmlr.getAttributeCount(); i++) {
						 			System.out.printf(" => attributo %s->%s%n", xmlr.getAttributeLocalName(i), xmlr.getAttributeValue(i));
						 			
						 		}									 
						 		break;
						   case "nome":
							   comune.setNome(xmlr.getElementText());
							   break;
						 	case "codice":
						 		comune.setCodice(xmlr.getElementText());						 			
						 		break;
						 	
						 }						 	
						 break;
					 case XMLStreamConstants.END_ELEMENT: // fine di un elemento: stampa il nome del tag chiuso
						 System.out.println("END-Tag " + xmlr.getLocalName()); 
						 if(xmlr.getLocalName().equalsIgnoreCase("comune")) {
						 		comuni.add(comune);
						 }
						 break;
				 }
				 try {
					 xmlr.next();
				 }catch (XMLStreamException e) {
					e.printStackTrace();
				 }
			}
		} catch (XMLStreamException e) {
			e.printStackTrace();
		}			
		return comuni;
		
		
	}
	public static ArrayList<CodiceFiscale> leggiXMLCodiceFiscale(String nome_file) {
		ArrayList<CodiceFiscale> codiciFiscaliInput= new ArrayList<CodiceFiscale>();
		CodiceFiscale codiceFiscale = null;
		String filename= nome_file;
		
		XMLInputFactory xmlif = null;
		XMLStreamReader xmlr = null;
		try {
			 xmlif = XMLInputFactory.newInstance();
			 xmlr = xmlif.createXMLStreamReader(filename, new FileInputStream(filename));
		} catch (Exception e) {
			System.out.println("Errore nell'inizializzazione del reader:");
			System.out.println(e.getMessage());
		}
		
		try {
			while (xmlr.hasNext()){ 
				 switch (xmlr.getEventType()) { 
					 case XMLStreamConstants.START_DOCUMENT: 
						 System.out.println("Start Read Doc " + filename); 
						 break;
					 case XMLStreamConstants.START_ELEMENT:
						 System.out.println("Tag " + xmlr.getLocalName());
						 if(xmlr.getLocalName().equalsIgnoreCase("codice")){
						 						 		
						 		codiceFiscale= new CodiceFiscale();
						 		codiceFiscale.setCodice(xmlr.getElementText());
						 		for (int i = 0; i < xmlr.getAttributeCount(); i++) {
						 			System.out.printf(" => attributo %s->%s%n", xmlr.getAttributeLocalName(i), xmlr.getAttributeValue(i));
						 		}							 								 								 	
						 }						 	
						 break;
					 case XMLStreamConstants.END_ELEMENT: // fine di un elemento: stampa il nome del tag chiuso
						 System.out.println("END-Tag " + xmlr.getLocalName()); 
						 if(xmlr.getLocalName().equalsIgnoreCase("codice")) {
						 		codiciFiscaliInput.add(codiceFiscale);
						 }
						 break;
				 }
				 try {
					 xmlr.next();
				 }catch (XMLStreamException e) {
					e.printStackTrace();
				 }
			}
		} catch (XMLStreamException e) {
			e.printStackTrace();
		}			
		return codiciFiscaliInput;
	}

}
