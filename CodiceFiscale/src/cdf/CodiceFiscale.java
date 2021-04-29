package cdf;

import java.text.SimpleDateFormat;

public class CodiceFiscale {
	private static final int INIZIO_BLOCCO7 = 15;
	private static final int INIZIO_BLOCCO6 = 11;
	private static final int INIZIO_BLOCCO5 = 9;
	private static final int INIZIO_BLOCCO4 = 8;
	private static final int INIZIO_BLOCCO3 = 6;
	private static final int INIZIO_BLOCCO1_2 = 0;
	private static final int DIVISORE = 26;
	private static final int ADD_GIORNO_NASCITA = 40;
	private static final String FEMMINA = "F";
	private static final String CARATTERE_AGGIUNTIVO = "X";
	private static final int NUM_CARATTERI=3;
		
		
	private String codice;
	private boolean is_corretto;
	private boolean is_appaiato=false;
	
	public CodiceFiscale() {
		// TODO Auto-generated constructor stub
	}

	public String getCodice() {
		return codice;
	}

	public void setCodice(String codice) {
		this.codice = codice;
	}
	
	
	public boolean getIs_corretto() {
		return is_corretto;
	}

	public void setIs_corretto(boolean is_corretto) {
		this.is_corretto = is_corretto;
	}

	public boolean getIs_appaiato() {
		return is_appaiato;
	}

	public void setIs_appaiato(boolean is_appaiato) {
		this.is_appaiato = is_appaiato;
	}

	public static String generazioneCodiceFiscale(Persona persona) {
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
		caratteri_cognome = calcolaStringa(persona.getCognome(), is_nome);
		
		is_nome=true;
		caratteri_nome = calcolaStringa(persona.getNome(), is_nome);
		
		caratteri_anno=format_anno.format(persona.getData_nascita());
		caratteri_anno=caratteri_anno.substring(2, 4);
		
		carattere_mese= calcolaCarattereMese(Integer.parseInt(format_mese.format(persona.getData_nascita()) ));	
		
		caratteri_giorno= celcolaCaratteriGiorno(Integer.parseInt(format_giorno.format(persona.getData_nascita())), persona.getSesso());
		
		caratteri_comune= persona.getComune_nascita().getCodice();
		
		codice_fiscale = caratteri_cognome+caratteri_nome+caratteri_anno+carattere_mese+caratteri_giorno+caratteri_comune;
		carettere_controllo=calcolaCarattereControllo(codice_fiscale);		
		
		codice_fiscale = caratteri_cognome+caratteri_nome+caratteri_anno+carattere_mese+caratteri_giorno+caratteri_comune+carettere_controllo;
		return codice_fiscale;
	}
	
	public static String calcolaStringa(String stringa, Boolean is_nome) {
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
	
	public static String getConsonanti(String stringa) {
		stringa = stringa.toUpperCase();
		stringa=stringa.replaceAll("[AEIOU]","");
	    return stringa;
	}
	
	public static String getVocali(String stringa) {
		stringa = stringa.toUpperCase();
		stringa=stringa.replaceAll("[^ AEIOU]","");
	    return stringa;
	}
	
	public static String calcolaCarattereMese(int mese) {
		String carattere="";
		mese=mese-1;	// l'idice del Enum parte da 0;
		carattere=Mese.getById(mese).getValore();
		return carattere;
	}
	
	public static String celcolaCaratteriGiorno(int giorno, String sesso) {
		String caratteri="";
		sesso=sesso.toUpperCase();
		if(sesso.substring(0, 1).equals(FEMMINA)) {
			giorno=giorno+ADD_GIORNO_NASCITA;
		}
		caratteri=String.format("%02d",giorno);
		return caratteri;
	}
	
	public static String calcolaCarattereControllo(String codice_fisc) {
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
	
	
	/*in un codice fiscale, per determinarme la validità bisogna
	 * controllare che consonanti e numeri siano posizionati 
	 * correttamente all'interno della stringa, e bisogna controllare
	 * la correttezza del numero dei giorni rispetto al mese
	 */
	
	//verifica i pirimi 6 caratteri ==> i=[0,5]							NOME E COGNOME
	public boolean verificaNomeCognome( String cognom_nom) {
		for(int i=0; i<cognom_nom.length(); i++) {
			if(cognom_nom.charAt(i) < 'A' || cognom_nom.charAt(i) > 'Z') {
				return false;
			}
		}
		return true;
	}
	
	//verifica i 2 caratteri successivi ==> i=[6,7]						ANNO
	public boolean verificaAnno(String year) {
		for( int indice = 0; indice <year.length(); indice++) {
			if(year.charAt(indice)< '0' || year.charAt(indice) > '9' ) {
				return false;
			}
		}
		return true;
	}
	
	//													MESE-GIORNO
	public boolean verificaMeseGiorno(String carattere_mese, String giorno) {
		
		if(Mese.isPresente(carattere_mese)){
			if(Integer.parseInt(giorno)<= Mese.getNomeDaValore(carattere_mese).getGiorni() && Integer.parseInt(giorno)>0)return true;
			if(Integer.parseInt(giorno)<= Mese.getNomeDaValore(carattere_mese).getGiorni()+ADD_GIORNO_NASCITA && Integer.parseInt(giorno)>ADD_GIORNO_NASCITA)return true;
		}
		return false;
	}
		
	
	//verifico il codice del comune 
	public boolean verificaCodiceComune(String code) {
		int i;
		if(code.charAt(0)<'A' || code.charAt(0)>'Z') {
			return false;
		}
		else {
			for(i=1; i<code.length(); i++) {
				if(code.charAt(i)<'0' || code.charAt(i)>'9') {
					return false;
				}
			}
		}
		return true;
	}
	
	public boolean verificaCarattereControllo(String codice_fisc, String carattere) {
		String carattere_calcolato;
		carattere_calcolato=calcolaCarattereControllo(codice_fisc);
		
		if(carattere_calcolato.equalsIgnoreCase(carattere)) {
			return true;
		}
		return false;
	}
	
	//verifico correttezza del codice fiscale
	public boolean verificaCodiceFiscale(String codice_fiscale) {
		String nome_cognome=codice_fiscale.substring(INIZIO_BLOCCO1_2, INIZIO_BLOCCO3);
		String anno=codice_fiscale.substring(INIZIO_BLOCCO3, INIZIO_BLOCCO4);
		String mese=codice_fiscale.substring(INIZIO_BLOCCO4, INIZIO_BLOCCO5);
		String giorno=codice_fiscale.substring(INIZIO_BLOCCO5, INIZIO_BLOCCO6);
		String comune=codice_fiscale.substring(INIZIO_BLOCCO6, INIZIO_BLOCCO7);
		String cf_senza_carattere_controllo=codice_fiscale.substring(INIZIO_BLOCCO1_2, INIZIO_BLOCCO7);
		String carattere_controllo=codice_fiscale.substring(INIZIO_BLOCCO7);
		
		if(!verificaNomeCognome(nome_cognome)) return false;
		if(!verificaAnno(anno)) return false;
		if(!verificaMeseGiorno(mese, giorno)) return false;
		if(!verificaCodiceComune(comune)) return false;
		if(!verificaCarattereControllo(cf_senza_carattere_controllo,carattere_controllo))return false;
		return true;
	}

}