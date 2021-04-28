package cdf;

import java.text.SimpleDateFormat;

public class CodiceFiscale {
	private static final int DIVISORE = 26;
	private static final int ADD_GIORNO_NASCITA = 40;
	private static final String FEMMINA = "F";
	private static final String CARATTERE_AGGIUNTIVO = "X";
	private static final int NUM_CARATTERI=3;
		
		
	private String codice;
	
	public CodiceFiscale() {
		// TODO Auto-generated constructor stub
	}

	public String getCodice() {
		return codice;
	}

	public void setCodice(String codice) {
		this.codice = codice;
	}
	
	
	public String generazioneCodiceFiscale(Persona persona) {
		String codice_fiscale = "";
		Boolean is_nome;
		SimpleDateFormat format_anno= new SimpleDateFormat("yyyy");
		SimpleDateFormat format_mese= new SimpleDateFormat("MM");
		SimpleDateFormat format_giorno= new SimpleDateFormat("dd");
		
		String caratteri_cognome = "";
		String caratteri_nome = "";
		String caratteri_anno = "";
		String carattere_mese = "";
		String caratteri_giorno = "";
		String caratteri_comune = "";
		String carettere_controllo = "";
		
		is_nome=false;
		caratteri_cognome = this.calcolaStringa(persona.getCognome(), is_nome);
		
		is_nome=true;
		caratteri_nome = this.calcolaStringa(persona.getNome(), is_nome);
		
		caratteri_anno=format_anno.format(persona.getData_nascita());
		caratteri_anno=caratteri_anno.substring(2, 4);
		
		carattere_mese= this.calcolaCarattereMese(Integer.parseInt(format_mese.format(persona.getData_nascita()) ));	
		
		caratteri_giorno= this.celcolaCaratteriGiorno(Integer.parseInt(format_giorno.format(persona.getData_nascita())), persona.getSesso());
		
		caratteri_comune=persona.getComune_nascita().getCodice();
		
		codice_fiscale = caratteri_cognome+caratteri_nome+caratteri_anno+carattere_mese+caratteri_giorno+caratteri_comune;
		carettere_controllo=this.calcolaCarattereControllo(codice_fiscale);		
		
		codice_fiscale = caratteri_cognome+caratteri_nome+caratteri_anno+carattere_mese+caratteri_giorno+caratteri_comune+carettere_controllo;
		return codice_fiscale;
	}
	
	public String calcolaStringa(String stringa, Boolean is_nome) {
		String stringa_calcolata="";
		stringa = stringa.replaceAll(" ", "");		// Rimuovo eventuali spazi
	    stringa = stringa.toUpperCase();			// Rendo tutte le lettere maiuscole
	    
	    String consonanti_stringa = getConsonanti(stringa);
	    String vocali_stringa = getVocali(stringa);
		
	    if(consonanti_stringa.length()==NUM_CARATTERI) {	    	
	    	stringa_calcolata = consonanti_stringa;	    	
	    }else if(consonanti_stringa.length()>NUM_CARATTERI){
	    	if(is_nome) {
	    		stringa_calcolata = ""+consonanti_stringa.charAt(0)+consonanti_stringa.charAt(2)+consonanti_stringa.charAt(3);
	    	}else {
	    		stringa_calcolata = consonanti_stringa.substring(0,3);
	    	}
	    }else if(consonanti_stringa.length()<NUM_CARATTERI && stringa.length()<NUM_CARATTERI) {
	    	
	    	stringa_calcolata = consonanti_stringa;
	    	stringa_calcolata = stringa_calcolata+vocali_stringa; 
	    	while(stringa_calcolata.length()<3){
	    		stringa_calcolata=stringa_calcolata+CARATTERE_AGGIUNTIVO;
	    	}
	    }else if(consonanti_stringa.length()<NUM_CARATTERI && stringa.length()>NUM_CARATTERI){
	    	stringa_calcolata = consonanti_stringa;
	    	for(int i=0; stringa_calcolata.length()<3; i++){
	    		stringa_calcolata=stringa_calcolata+vocali_stringa.charAt(i);
	    	}
	    }
	    
		return stringa_calcolata;
	}
	
	public String getConsonanti(String stringa) {
		stringa = stringa.toUpperCase();
		stringa=stringa.replaceAll("[AEIOU]","");
	    return stringa;
	}
	
	public String getVocali(String stringa) {
		stringa = stringa.toUpperCase();
		stringa=stringa.replaceAll("[^ AEIOU]","");
	    return stringa;
	}
	
	public String calcolaCarattereMese(int mese) {
		String carattere="";
		mese=mese-1;	// l'idice del Enum parte da 0;
		carattere=Mese.getById(mese).getValore();
		return carattere;
	}
	
	public String celcolaCaratteriGiorno(int giorno, String sesso) {
		String caratteri="";
		sesso=sesso.toUpperCase();
		if(sesso.substring(0, 1).equals(FEMMINA)) {
			giorno=giorno+ADD_GIORNO_NASCITA;
		}
		caratteri=String.valueOf(giorno);
		return caratteri;
	}
	
	public String calcolaCarattereControllo(String codice_fisc) {
		String carattere="";
		
		int somma_caratteri_pari=0;
		int somma_caratteri_dispari=0;
		int resto;
		
		for(int i=0; i<codice_fisc.length(); i++){
			if((i+1)%2 == 0) {
				somma_caratteri_pari=somma_caratteri_pari + CaratteriPari.getValoreDaNome(Character.toString(codice_fisc.charAt(i)));
			}else {
				somma_caratteri_dispari =somma_caratteri_dispari +CaratteriDispari.getValoreDaNome(Character.toString(codice_fisc.charAt(i)));
			}
		}
		resto=(somma_caratteri_pari+somma_caratteri_dispari)%DIVISORE;
		
		carattere=RestoCaratteri.getNomeDaValore(resto).name().substring(1);
		return carattere;
	}
}
