package it.polito.tdp.formula1.model;

public class FantaStat implements Comparable<FantaStat>{
	private FantaDriver driver;
	private int punteggio;
	
	public FantaStat(FantaDriver driver, int punteggio) {
		super();
		this.driver = driver;
		this.punteggio = punteggio;
	}

	public FantaDriver getDriver() {
		return driver;
	}

	public void setDriver(FantaDriver driver) {
		this.driver = driver;
	}

	public int getPunteggio() {
		return punteggio;
	}

	public void setPunteggio(int punteggio) {
		this.punteggio = punteggio;
	}

	@Override
	public int compareTo(FantaStat other) {
		// TODO Auto-generated method stub
		return this.punteggio-other.getPunteggio();
	}
	
	
	
	

}
