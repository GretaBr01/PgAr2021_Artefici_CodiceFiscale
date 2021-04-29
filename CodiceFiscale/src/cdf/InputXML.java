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
			String comune;
			
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
						 case XMLStreamConstants.START_ELEMENT:
							 switch (xmlr.getLocalName()){
							 	case "persona":						 		
							 		persona= new Persona();
							 		for (int i = 0; i < xmlr.getAttributeCount(); i++) {
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
							 		comune=xmlr.getElementText();
							 		persona.setComune_nascita(Manager.getComune(comune));
							 		
							 		break;
							 	case "data_nascita":
							 		persona.setData_nascita(xmlr.getElementText());		
							 		break;
							 }						 	
							 break;
						 case XMLStreamConstants.END_ELEMENT: // fine di un elemento: stampa il nome del tag chiuso
							 if(xmlr.getLocalName().equalsIgnoreCase("persona")) {
							 		persone.add(persona);
							 }
							 break;
					 }
					xmlr.next();
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
						 case XMLStreamConstants.START_ELEMENT:
							 switch (xmlr.getLocalName()){
							 	
							   case "comune":						 		
							 		comune= new Comune();									 
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
							 if(xmlr.getLocalName().equalsIgnoreCase("comune")) {
							 		comuni.add(comune);
							 }
							 break;
					 }
					xmlr.next();
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
					 if(xmlr.getEventType() == XMLStreamConstants.START_ELEMENT) {
						 if(xmlr.getLocalName().equalsIgnoreCase("codice")){							 						 		
						 		codiceFiscale= new CodiceFiscale();
						 		codiceFiscale.setCodice(xmlr.getElementText());	
						 		codiciFiscaliInput.add(codiceFiscale);							 		
						 }
					 }
					 xmlr.next();
				}
			} catch (XMLStreamException e) {
				e.printStackTrace();
			}			
			return codiciFiscaliInput;
		}
	
	}
