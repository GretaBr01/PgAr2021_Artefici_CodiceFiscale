package cdf;

public enum Mese {
	GENNAIO ("A"), 
	FEBBRAIO ("B"),
	MARZO ("C"),
	APRILE ("D"),
	MAGGIO ("E"),
	GIUGNO ("H"),
	LUGLIO ("L"),
	AGOSTO ("M"),
	SETTEMBRE ("P"),
	OTTOBRE ("R"),
	NOVEMBRE ("S"),
	DICEMBRE ("T");
	
	String valore;
	
	Mese(String string) {
		this.valore=string;
	}
	
	/**
	 * serve per trovare il nome dell'elemento Enum che occupa l'indice dato come parametro
	 * @param id	indice dell'elemento Enum
	 * @return	 elemento Enum presente all'indice dato
	 */
	public static Mese getById(int id) {
		return Mese.values()[id];
	}
	
	/**
	 * richiamando questo metodo si ottiene il valore della costante Enum
	 * @return	valore
	 */
	public String getValore() {
		return valore;
	}
	
	
}
