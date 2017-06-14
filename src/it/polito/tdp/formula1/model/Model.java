package it.polito.tdp.formula1.model;


import java.util.Collections;
import java.util.List;

import it.polito.tdp.formula1.db.F1DAO;

public class Model {
	private List<Season> seasons;
	private List<Circuit> circuiti;
	private F1DAO dao;
	private Simulator sim;

	public Model() {
		super();
		dao= new F1DAO();
	}
	
	public List<Season> getAllSeasons(){
		if(seasons==null)
			seasons=dao.getAllSeasons();
		
		return seasons;
	}
	
	public List<Circuit> getCircuitsForSeasons(Season season){
		  circuiti=dao.getCircuitiForStagione(season);
			Collections.sort(circuiti);
		  return circuiti;	  
	}

	public List<Driver> getPilotiPerGara(Season s, Circuit c) {
		List<Driver> result= dao.getPilotiPerGara(s, c);
		Collections.sort(result);
		return result;
		
	}
	
	public List<FantaStat> getClassifica(Driver d, Circuit c){
		List<FantaDriver> partecipanti=dao.getFantaGiocatori(d, c);
		for(FantaDriver ftemp:partecipanti){
			ftemp.setTempi(dao.getTempiPerGiocatore(ftemp,d, c));
		}
		sim=new Simulator(partecipanti);
		
		return sim.getClassifica();
	
	}


}
