package it.polito.tdp.formula1.model;

public class Event implements Comparable<Event> {
	private FantaDriver giocatore;
	private int lap;
	private int time;
	
	public Event(FantaDriver giocatore, int lap, int time) {
		super();
		this.giocatore = giocatore;
		this.lap = lap;
		this.time = time;
	}

	public FantaDriver getGiocatore() {
		return giocatore;
	}

	public void setGiocatore(FantaDriver giocatore) {
		this.giocatore = giocatore;
	}

	public int getLap() {
		return lap;
	}

	public void setLap(int lap) {
		this.lap = lap;
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}

	@Override
	public int compareTo(Event other) {
	
		return this.time-other.time;
	}
	
	
	
	

}
