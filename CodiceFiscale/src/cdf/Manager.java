package cdf;

import java.util.ArrayList;

public class Manager {
	private static ArrayList<Persona> persone;
	private static ArrayList<Comune> comuni;
	private static ArrayList<CodiceFiscale> codici_fiscali;
	private static int numer_codici_non_corretti=0;
	private static int numero_codici_appaiati=0;
	
	
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
	
	public static void verificaValiditaCodiciFiscali() {
		boolean corretto;
		for(CodiceFiscale cod_fisc: codici_fiscali) {
			corretto = cod_fisc.verificaCodiceFiscale(cod_fisc.getCodice());
			cod_fisc.setIs_corretto(corretto);
		}
	}
	
	public static void verificaPresenzaCodiceFiscaleInFile() {
		boolean presente;
		for(CodiceFiscale cod_fisc: codici_fiscali) {
			if(cod_fisc.getIs_corretto()) {
				for(Persona pers: persone) {
					if(cod_fisc.getCodice().equals(pers.getCodice_fiscale())) {
							presente=true;
							cod_fisc.setIs_appaiato(presente);
							pers.setIs_presente(presente);
							numero_codici_appaiati++;
					}
				}
			}else {
				numer_codici_non_corretti++;
			}
		}
	}
	
	public static void scritturaFileXML() {
		int num_spaiati=codici_fiscali.size()-numero_codici_appaiati-numer_codici_non_corretti;
		OutputXML.scriviXML(persone, codici_fiscali, num_spaiati, numer_codici_non_corretti);
	}
}
