package cdf;

public class Main {

	public static void main(String[] args) {
		
		//prelievoDatiPersone(inputPersone.xml);			// --> classe Manager --> classe InputXML
		// prelievoDatiComuni(comuni.xml);					// --> classe Manager --> classe InputXML
		// prelievoDatiCodiciFiscali(codiciFiscali.xml);	// --> classe Manager --> classe InputXML
		
		// generaCodiciFiscaliPersone();									// --> classe Manager --> CodiceFiscale
		// verificaValiditaCodiciFiscali();  //del file codiciFiscali.xml	// --> classe Manager --> CodiceFiscale
		// verificaPresenzaCodiceFiscaleInFile();							// --> classe Manager
		
		// scritturaFileXML();						// --> classe Manager  --> classe OutputXML
		
		
		
		InputXML file = new InputXML();
		file.leggiXMLPersone("inputPersone.xml");
		file.leggiXMLComune("comuni.xml");
		file.leggiXMLCodiceFiscale("codiciFiscali.xml");
		

	}

}
