package it.polito.tdp.formula1.model;

import java.util.HashMap;
import java.util.Map;

public class FantaDriver {
	private int year;
	private Map<Integer,LapTime> tempi;
	
	public FantaDriver(int year) {
		super();
		this.year = year;
		tempi=new HashMap<>();
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public Map<Integer, LapTime> getTempi() {
		return tempi;
	}

	public void setTempi(Map<Integer, LapTime> tempi) {
		this.tempi = tempi;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + year;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FantaDriver other = (FantaDriver) obj;
		if (year != other.year)
			return false;
		return true;
	}

	
	
	
	
	
	
	
	
	

}
