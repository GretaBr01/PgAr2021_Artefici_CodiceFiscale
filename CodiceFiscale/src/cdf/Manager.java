package cdf;

import java.util.ArrayList;

public class Manager {
	private static ArrayList<Persona> persone;
	private static ArrayList<Comune> comuni;
	private static ArrayList<CodiceFiscale> codici_fiscali;
	
	
	public static void prelievoDatiComuni(String nome_file) {
		comuni=InputXML.leggiXMLComune(nome_file);
	}
	
	public static void prelievoDatiPersone(String nome_file) {
		persone=InputXML.leggiXMLPersone(nome_file);
	}
	
	public static void prelievoDatiCodiciFiscali(String nome_file) {
		codici_fiscali=InputXML.leggiXMLCodiceFiscale(nome_file);
	}
	
	public static void generaCodiciFiscaliPersone() {
		String cod_fisc;
		for(Persona pers: persone) {
			cod_fisc=CodiceFiscale.generazioneCodiceFiscale(pers);
			pers.setCodice_fiscale(cod_fisc);
		}
	}
	
	/**
	 * otteenere l'oggetto comune avente come nome il valore del parametro fornito
	 * @param nome_comune
	 * @return	oggetto comune
	 */
	public static Comune getComune(String nome_comune) {
		for(Comune com: comuni) {
			if(com.getNome().equalsIgnoreCase(nome_comune)) {
				return com;
			}
		}
		return null;
	}
}
