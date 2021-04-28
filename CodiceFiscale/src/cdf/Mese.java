package cdf;

public enum Mese {
	GENNAIO ("A", 31), 
	FEBBRAIO ("B", 28),
	MARZO ("C", 31),
	APRILE ("D", 30),
	MAGGIO ("E", 31),
	GIUGNO ("H", 30),
	LUGLIO ("L", 31),
	AGOSTO ("M", 31),
	SETTEMBRE ("P", 30),
	OTTOBRE ("R", 31),
	NOVEMBRE ("S", 30),
	DICEMBRE ("T", 31);
	
	String valore;
	int giorni;
	
	Mese(String string, int i) {
		this.valore=string;
		this.giorni=i;
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
	
	public int getGiorni() {
		return giorni;
	}
	
	public static boolean isPresente(String carattere) {
		Mese nodes[] = values();
		
		for(int i=0; i<nodes.length; i++) {
			if(nodes[i].getValore().equalsIgnoreCase(carattere)) {
				return true;
			}
		}
		return false;
	}
	
	public static Mese getNomeDaValore(String valore) {
		Mese var = null;
		Mese nodes[] = values();
	
		for(int i=0; i<nodes.length; i++) {
			if (nodes[i].getValore().equalsIgnoreCase(valore)) {
				return getById(i);
			}
		}
		return var;
	}
	
	
}
