package it.polito.tdp.formula1.model;

public class Stat {
	private Driver pilota;
	private int punteggio;
	
	
	public Stat(Driver pilota, int punteggio) {
		super();
		this.pilota = pilota;
		this.punteggio = punteggio;
	}
	public Driver getPilota() {
		return pilota;
	}
	public void setPilota(Driver pilota) {
		this.pilota = pilota;
	}
	public int getPunteggio() {
		return punteggio;
	}
	public void setPunteggio(int punteggio) {
		this.punteggio = punteggio;
	}
	
	
	

}
