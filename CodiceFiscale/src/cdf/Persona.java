package cdf;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import  java.util.Date;  

public class Persona {
	
	private String id;
	private String nome;
	private String cognome;
	private String sesso;
	private Comune comune_nascita;
	private Date data_nascita;
	private String codice_fiscale;

	public Persona() {
		// TODO Auto-generated constructor stub
	}
	
	public String getID() {
		return id;
	}

	public String getNome() {
		return nome;
	}
	
	public void setId(String string) {
		this.id = string;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getSesso() {
		return sesso;
	}

	public void setSesso(String sesso) {
		this.sesso = sesso;
	}

	public Comune getComune_nascita() {
		return comune_nascita;
	}

	public void setComune_nascita(Comune comune_nascita) {
		this.comune_nascita = comune_nascita;
	}

	public Date getData_nascita() {
		return data_nascita;
	}

	public void setData_nascita(String stringa_data) {
		try {
			this.data_nascita = new SimpleDateFormat("yyyy-MM-dd").parse(stringa_data);
		} catch (ParseException e) {
			e.printStackTrace();
		} 		
	}
	
	public String getCodice_fiscale() {
		return codice_fiscale;
	}

	public void setCodice_fiscale(String codice_fiscale) {
		this.codice_fiscale = codice_fiscale;
	}

	public String getId() {
		return id;
	}

}
