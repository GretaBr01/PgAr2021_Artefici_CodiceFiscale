package cdf;

public enum CaratteriDispari {
	_0 (1),
	_1 (0),
	_2 (5),
	_3 (7),
	_4 (9),
	_5 (13),
	_6 (15),
	_7 (17),
	_8 (19),
	_9 (21),
	_A (1),
	_B (0),
	_C (5),
	_D (7),
	_E (9),
	_F (13),
	_G (15),
	_H (17),
	_I (19),
	_J (21),
	_K (2),
	_L (4),
	_M (18),
	_N (20),
	_O (11),
	_P (3),
	_Q (6),
	_R (8),
	_S (12),
	_T (14),
	_U (16),
	_V (10),
	_W (22),
	_X (25),
	_Y (24),
	_Z (23);

	private int valore;
	
	CaratteriDispari(int i) {
		this.valore=i;
	}
	
	/**
	 * serve per trovare il nome dell'elemento Enum che occupa l'indice dato come parametro
	 * @param id	indice dell'elemento Enum
	 * @return	 elemento Enum presente all'indice dato
	 */
	public CaratteriDispari getById(int id) {
		return CaratteriDispari.values()[id];
	}
	
	/**
	 * richiamando questo metodo si ottiene il valore dell'elemento Enum
	 * @return	valore
	 */
	public int getValore() {
		return valore;
	}
	
	/**
	 * sapendo il carattere alfanumerico corrispondente ad un elemento Enum si ottiene il rispettivo valore
	 * 
	 * @param carattere		carattre alfanumerico
	 * @return valore dell'elemento Enum corrispondente se trovato, altrimenti restituisce -1
	 */	
	public static int getValoreDaNome(String carattere) {
		int val = -1;
		CaratteriDispari nodes[] = values();
		
		if(carattere.charAt(0)  == '_') {
			carattere=carattere.toUpperCase();
		}else {
			carattere="_"+carattere.toUpperCase();
		}		
				
		for(int i=0; i<nodes.length; i++) {
			if (nodes[i].name().equals(carattere)) {
				return nodes[i].getValore();
			}
		}		
		return val;
	}

}
