package it.polito.tdp.formula1.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class Simulator {
	
	private PriorityQueue<Event> queue;
	private Map<Integer,List<FantaDriver>> map;
	private List<FantaDriver> giocatori;
	private Map<FantaDriver,Integer> punteggi;
	private int bestLap;
	

	public Simulator(List<FantaDriver> giocatori) {
		this.giocatori=giocatori;
		queue=new PriorityQueue<>();
		map=new HashMap<>();
		punteggi=new HashMap<>();
		for(FantaDriver fd:giocatori){
			punteggi.put(fd, 0);
			
		}
		bestLap=0;
		
		
	}

	public void run() {
		this.popolaCoda();
		while(!queue.isEmpty()){
			Event e=queue.poll();
			
			if(map.get(e.getLap())==null){
				bestLap=e.getLap();
				map.put(e.getLap(), new ArrayList<FantaDriver>());
			}
			
			map.get(e.getLap()).add(e.getGiocatore());
			if(e.getLap()!=1){
			int posAttuale=map.get(e.getLap()).size()-1;
			
			int posPrec=0;
			int counter=0;
				for(FantaDriver fd: map.get(e.getLap()-1)){
					if(e.getGiocatore().equals(fd)){
						posPrec=counter;
						break;
					}
					counter++;
				}
				
			if(posAttuale<posPrec){
				int punti=punteggi.get(e.getGiocatore());
				punteggi.put(e.getGiocatore(), punti+1);
				
			}
			}
			if(e.getLap()+2<= bestLap){
				punteggi.remove(e.getGiocatore());
			}else{
				if(e.getGiocatore().getTempi().get(e.getLap()+1)!=null){
				Event e2= new Event(e.getGiocatore(),e.getLap()+1,e.getTime()+e.getGiocatore().getTempi().get(e.getLap()+1).getMiliseconds());
				queue.add(e2);
				}
				
				
			}
				
			
			
			
			
			
		}
		
	}

	private void popolaCoda() {
		for(FantaDriver fd:giocatori){
			if(fd.getTempi().get(1)!=null){
			Event e= new Event(fd,1,fd.getTempi().get(1).getMiliseconds());
			queue.add(e);
			}
		}
		
	}
	
	public List<FantaStat> getClassifica(){
		this.run();
		List<FantaStat> result=new ArrayList<FantaStat>();
		for(FantaDriver fd:punteggi.keySet()){
			result.add(new FantaStat(fd,punteggi.get(fd)));
			
		}
		Collections.sort(result);
		return result;
		
	}

}
