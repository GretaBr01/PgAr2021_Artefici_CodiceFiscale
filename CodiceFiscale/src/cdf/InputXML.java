package cdf;

import java.io.FileInputStream;
import java.util.ArrayList;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

public class InputXML {

	public InputXML() {
		// TODO Auto-generated constructor stub
	}
	
	public void leggiXML(String nome_file) {
		switch (nome_file) {
			case "inputPersone.xml":
				this.leggiXMLPersone(nome_file);
				break;
			case "comuni.xml":
				this.leggiXMLComuni(nome_file);
				break;
			case "codiciFiscali.xml":
				this.leggiXMLCodiciFiscali(nome_file);
				break;
		
		}
		
	}
	
	public ArrayList<Persona> leggiXMLPersone(String nome_file) {
		ArrayList<Persona> persone= new ArrayList<Persona>();
		
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
			while (xmlr.hasNext()) { // continua a leggere finché ha eventi a disposizione
				 switch (xmlr.getEventType()) { // switch sul tipo di evento
					 case XMLStreamConstants.START_DOCUMENT: // inizio del documento: stampa che inizia il documento
						 System.out.println("Start Read Doc " + filename); 
						 break;
					 case XMLStreamConstants.START_ELEMENT: // inizio di un elemento: stampa il nome del tag e i suoi attributi
						 switch (xmlr.getLocalName()) {
						 	case "persona":
						 		break;
						 	case "nome":
						 		break;
						 	case "cognome":
						 		break;
						 	case "sesso":
						 		break;
						 	case "comune_nascita":
						 		break;
						 	case "data_nascita":
						 		break;
						 }
						 System.out.println("Tag " + xmlr.getLocalName());
						 for (int i = 0; i < xmlr.getAttributeCount(); i++)
							 System.out.printf(" => attributo %s->%s%n", xmlr.getAttributeLocalName(i), xmlr.getAttributeValue(i));	
						 break;
					 case XMLStreamConstants.END_ELEMENT: // fine di un elemento: stampa il nome del tag chiuso
						 System.out.println("END-Tag " + xmlr.getLocalName()); 
						 break;
					 case XMLStreamConstants.COMMENT:
						 System.out.println("// commento " + xmlr.getText()); 
						 break; // commento: ne stampa il contenuto
					 case XMLStreamConstants.CHARACTERS: // content all’interno di un elemento: stampa il testo
						 if (xmlr.getText().trim().length() > 0) // controlla se il testo non contiene solo spazi
							 System.out.println("-> " + xmlr.getText());
					 break;
				 }
				 try {
					xmlr.next();
				} catch (XMLStreamException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				}
		} catch (XMLStreamException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return persone;
	}

}
