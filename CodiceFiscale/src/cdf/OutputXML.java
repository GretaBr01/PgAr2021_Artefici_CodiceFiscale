package cdf;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

public class OutputXML {
	
	private static final String NOME_FILE_OUTPUT = "codiciPersone.xml";

	public static void scriviXML(ArrayList<Persona> persone, ArrayList<CodiceFiscale> codice_fiscali, int num_spaiati, int num_non_corretti) {
		SimpleDateFormat stf=new SimpleDateFormat("yyyy-MM-dd");
		
		XMLOutputFactory xmlof = null;
		XMLStreamWriter xmlw = null;
		try {
			xmlof = XMLOutputFactory.newInstance();
			xmlw = xmlof.createXMLStreamWriter(new FileOutputStream(NOME_FILE_OUTPUT), "utf-8");
			xmlw.writeStartDocument("utf-8", "1.0");
		} catch (Exception e) {
			System.out.println("Errore nell'inizializzazione del writer:");
			System.out.println(e.getMessage());
		}
		
		String[] autori = {"Alessandro","Greta", "Pietro"}; // esempio di dati da scrivere
		try { // blocco try per raccogliere eccezioni
			xmlw.writeStartElement("output"); // scrittura del tag radice <programmaArnaldo>

			xmlw.writeStartElement("persone"); // scrittura del tag autore...
			xmlw.writeAttribute("numero", String.valueOf(persone.size())); // ...con attributo id...
			
			for (int i = 0; i < persone.size(); i++) {
				Persona persona= persone.get(i);
				xmlw.writeStartElement("persona");
				xmlw.writeAttribute("id", persona.getId());
				
				xmlw.writeStartElement("nome");
				xmlw.writeCharacters(persona.getNome());
				xmlw.writeEndElement(); 
				
				xmlw.writeStartElement("cognome");
				xmlw.writeCharacters(persona.getCognome());
				xmlw.writeEndElement(); 
				
				xmlw.writeStartElement("sesso");
				xmlw.writeCharacters(persona.getSesso());
				xmlw.writeEndElement(); 
				
				xmlw.writeStartElement("comune_nascita");
				xmlw.writeCharacters(persona.getComune_nascita().getNome());
				xmlw.writeEndElement(); 
				
				xmlw.writeStartElement("data_nascita");
				xmlw.writeCharacters(stf.format(persona.getData_nascita()));
				xmlw.writeEndElement(); 
				
				xmlw.writeStartElement("codice_fiscale");
				if(persona.getIs_presente())
					xmlw.writeCharacters(persona.getCodice_fiscale());
				else
					xmlw.writeCharacters("ASSENTE");				
				xmlw.writeEndElement(); 
				
				xmlw.writeEndElement();
				
			}
			xmlw.writeEndElement(); // chiusura di </persone>
			
			xmlw.writeStartElement("codici");
			
			xmlw.writeStartElement("invalidi");
			xmlw.writeAttribute("numero", String.valueOf(num_non_corretti));
			for(int i=0; i<codice_fiscali.size(); i++) {
				CodiceFiscale cod=codice_fiscali.get(i);
				if(!cod.getIs_corretto()) {
					xmlw.writeStartElement("codice");
					xmlw.writeCharacters(cod.getCodice());
					xmlw.writeEndElement(); 
				}
			}
			xmlw.writeEndElement(); // chiusura di </invalidi>
			
			xmlw.writeStartElement("spaiati");
			xmlw.writeAttribute("numero", String.valueOf(num_spaiati));
			for(int i=0; i<codice_fiscali.size(); i++) {
				CodiceFiscale cod=codice_fiscali.get(i);
				if(cod.getIs_corretto() && !cod.getIs_appaiato()) {
					xmlw.writeStartElement("codice");
					xmlw.writeCharacters(cod.getCodice());
					xmlw.writeEndElement(); 
				}
			}
			xmlw.writeEndElement(); // chiusura di </spaiati>
			
			xmlw.writeEndElement(); // chiusura di </codici>
			
			xmlw.writeEndDocument(); // scrittura della fine del documento
			xmlw.flush(); // svuota il buffer e procede alla scrittura
			xmlw.close(); // chiusura del documento e delle risorse impiegate
		} catch (Exception e) { // se c’è un errore viene eseguita questa parte
			System.out.println("Errore nella scrittura");
		}


	}
}
