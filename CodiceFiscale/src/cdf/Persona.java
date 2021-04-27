package cdf;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import  java.util.Date;  

public class Persona {
	
	private String id;
	private String nome;
	private String cognome;
	private String sesso;
	private String comune_nascita;
	private Date data_nascita;

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

	public String getComune_nascita() {
		return comune_nascita;
	}

	public void setComune_nascita(String comune_nascita) {
		this.comune_nascita = comune_nascita;
	}

	public Date getData_nascita() {
		return data_nascita;
	}

	public void setData_nascita(String stringa_data) {
		try {
			this.data_nascita = new SimpleDateFormat("yyyy-MM-dd").parse(stringa_data);
			System.out.println(data_nascita);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
	}	

}
