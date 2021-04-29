package cdf;

public class Main {

	private static final String CODICI_FISCALI_XML = "codiciFiscali.xml";
	private static final String INPUTPERSONE_XML = "inputPersone.xml";
	private static final String COMUNI_XML = "comuni.xml";

	public static void main(String[] args) {
		
		Manager.prelievoDatiComuni(COMUNI_XML);					// --> classe Manager --> classe InputXML
		Manager.prelievoDatiPersone(INPUTPERSONE_XML);			// --> classe Manager --> classe InputXML
		Manager.prelievoDatiCodiciFiscali(CODICI_FISCALI_XML);	// --> classe Manager --> classe InputXML
		
		Manager.generaCodiciFiscaliPersone();								// --> classe Manager --> CodiceFiscale
		Manager.verificaValiditaCodiciFiscali();  //del file codiciFiscali.xml	// --> classe Manager --> CodiceFiscale
		Manager.verificaPresenzaCodiceFiscaleInFile();							// --> classe Manager
		
		Manager.scritturaFileXML();						// --> classe Manager  --> classe OutputXML	
		
	}

}
