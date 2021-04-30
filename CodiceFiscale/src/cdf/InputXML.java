	package cdf;
	
	import java.io.FileInputStream;
	import java.util.ArrayList;
	
	import javax.xml.stream.XMLInputFactory;
	import javax.xml.stream.XMLStreamConstants;
	import javax.xml.stream.XMLStreamException;
	import javax.xml.stream.XMLStreamReader;
	
	
	public class InputXML {
		
		private static final String CODICE = "codice";
		private static final String COMUNE = "comune";
		private static final String ERRORE_READER = "Errore nell'inizializzazione del reader:";
		private static final String DATA_NASCITA = "data_nascita";
		private static final String COMUNE_NASCITA = "comune_nascita";
		private static final String SESSO = "sesso";
		private static final String COGNOME = "cognome";
		private static final String NOME = "nome";
		private static final String PERSONA = "persona";


		/** 
		 * lettura del file passato come argomento e creazione di un'ArrayList contenente le informazioni ricavate 
		 * @param nome_file
		 * @return persone
		 */
		public static ArrayList<Persona> leggiXMLPersone(String nome_file) {
			ArrayList<Persona> persone= new ArrayList<Persona>();
			Persona persona = null;
			String filename= nome_file;
			String comune;
			
			//creazione della variabile xmlr di tipo XMLStreamReader
			XMLInputFactory xmlif = null;
			XMLStreamReader xmlr = null;
			try {
				//istanziamento della variabile xmlr 
				 xmlif = XMLInputFactory.newInstance();
				 xmlr = xmlif.createXMLStreamReader(filename, new FileInputStream(filename));
			} catch (Exception e) {
				System.out.println(ERRORE_READER);
				System.out.println(e.getMessage());
			}
			
			try {
				while (xmlr.hasNext()){ 
					 switch (xmlr.getEventType()) { 
						 case XMLStreamConstants.START_ELEMENT:
							 switch (xmlr.getLocalName()){
							 	case PERSONA: //caso tag di apertura "persona": istanziamento della variabile persona di tipo Persona						 		
							 		persona= new Persona();
							 		for (int i = 0; i < xmlr.getAttributeCount(); i++) {
							 			if(xmlr.getAttributeLocalName(i)=="id") {
							 				persona.setId(xmlr.getAttributeValue(i)); //settaggio variabile id della perosna
							 			}
							 		}									 
							 		break;
							 	case NOME: //caso tag di apertura "nome": inizializzazione della variabile nome dell'oggetto persona con il content del tag nome
							 		persona.setNome(xmlr.getElementText());						 			
							 		break;
							 	case COGNOME: //caso tag di apertura "cognome": inizializzazione della variabile cognome dell'oggetto persona con il content del tag cognome
							 		persona.setCognome(xmlr.getElementText());						 		
							 		break;
							 	case SESSO: //caso tag di apertura "sesso": inizializzazione della variabile sesso dell'oggetto persona con il content del tag sesso
							 		persona.setSesso(xmlr.getElementText());
							 		break;
							 	case COMUNE_NASCITA: //caso tag di apertura "comune_nascita": inizializzazione della variabile comune_nascita dell'oggetto persona con il content del tag comune_nascita
							 		comune=xmlr.getElementText();
							 		persona.setComune_nascita(Manager.getComune(comune));
							 		
							 		break;
							 	case DATA_NASCITA: //caso tag di apertura "data_nascita": inizializzazione della variabile data_nascita dell'oggetto persona con il content del tag data_nascita
							 		persona.setData_nascita(xmlr.getElementText());		
							 		break;
							 }						 	
							 break;
						 case XMLStreamConstants.END_ELEMENT: //caso tag di chiusura "persona": aggiunta dell'oggetto persona all'ArrayList di tipo Persona
							 if(xmlr.getLocalName().equalsIgnoreCase(PERSONA)) {
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
		
		/**
		 * lettura del file passato come argomento e creazione di un'ArrayList contenente le informazioni ricavate 
		 * @param nome_file
		 * @return comuni
		 */
		public static ArrayList<Comune> leggiXMLComune(String nome_file) {
			ArrayList<Comune> comuni= new ArrayList<Comune>();
			Comune comune = null;
			String filename= nome_file;
			
			//creazione della variabile xmlr di tipo XMLStreamReader
			XMLInputFactory xmlif = null;
			XMLStreamReader xmlr = null;
			try {
				//istanziamento della variabile xmlr 
				 xmlif = XMLInputFactory.newInstance();
				 xmlr = xmlif.createXMLStreamReader(filename, new FileInputStream(filename));
			} catch (Exception e) {
				System.out.println(ERRORE_READER);
				System.out.println(e.getMessage());
			}
			
			try {
				while (xmlr.hasNext()){ 
					 switch (xmlr.getEventType()) { 
						 case XMLStreamConstants.START_ELEMENT:
							 switch (xmlr.getLocalName()){
							 	
							   case COMUNE: //caso tag di apertura "comune": istanziamneto della variabile comune di tipo Comune						 		
							 		comune= new Comune();									 
							 		break;
							   case NOME: //caso tag di apertura "nome": inizializzazione della variabile nome dell'oggetto comune con il content del tag nome
								   comune.setNome(xmlr.getElementText());
								   break;
							   case CODICE: //caso tag di apertura "codice": inizializzazione della variabile codice dell'oggetto comune con il content del tag codice
							 		comune.setCodice(xmlr.getElementText());						 			
							 		break;
							 	
							 }						 	
							 break;
						 case XMLStreamConstants.END_ELEMENT: //caso tag di chiusura "comune": aggiunta dell'oggetto comune all'ArrayList di tipo Comune
							 if(xmlr.getLocalName().equalsIgnoreCase(COMUNE)) {
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
		
		/**
		 * lettura del file passato come argomento e creazione di un'ArrayList contenente le informazioni ricavate 
		 * @param nome_file
		 * @return codiciFiscaliInput
		 */
		public static ArrayList<CodiceFiscale> leggiXMLCodiceFiscale(String nome_file) {
			ArrayList<CodiceFiscale> codiciFiscaliInput= new ArrayList<CodiceFiscale>();
			CodiceFiscale codiceFiscale = null;
			String filename= nome_file;
			
			//creazione della variabile xmlr di tipo XMLStreamReader
			XMLInputFactory xmlif = null;
			XMLStreamReader xmlr = null;
			try {
				//istanziamento della variabile xmlr 
				 xmlif = XMLInputFactory.newInstance();
				 xmlr = xmlif.createXMLStreamReader(filename, new FileInputStream(filename));
			} catch (Exception e) {
				System.out.println(ERRORE_READER);
				System.out.println(e.getMessage());
			}
			
			try {
				while (xmlr.hasNext()){ 
					 if(xmlr.getEventType() == XMLStreamConstants.START_ELEMENT) {
						 if(xmlr.getLocalName().equalsIgnoreCase(CODICE)){ //caso tag di apertura "codice": istanziamneto della variabile codice di tipo Codice				 						 		
						 		codiceFiscale= new CodiceFiscale();
						 		codiceFiscale.setCodice(xmlr.getElementText());	//inizializzazione della variabile codice dell'oggetto codiceFiscale con il content del tag codice 
						 		codiciFiscaliInput.add(codiceFiscale); //aggiunta dell'oggetto codiceFiscale all'ArrayList di tipo CodiceFiscale							 		
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
