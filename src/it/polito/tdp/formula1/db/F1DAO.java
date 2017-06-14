package it.polito.tdp.formula1.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Year;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import it.polito.tdp.formula1.model.Circuit;
import it.polito.tdp.formula1.model.Driver;
import it.polito.tdp.formula1.model.FantaDriver;
import it.polito.tdp.formula1.model.LapTime;
import it.polito.tdp.formula1.model.Race;
import it.polito.tdp.formula1.model.Season;


public class F1DAO {

	public List<Season> getAllSeasons() {
		
		String sql = "SELECT year, url FROM seasons ORDER BY year" ;
		
		try {
			Connection conn = DBConnect.getConnection() ;

			PreparedStatement st = conn.prepareStatement(sql) ;
			
			ResultSet rs = st.executeQuery() ;
			
			List<Season> list = new ArrayList<>() ;
			while(rs.next()) {
				list.add(new Season(Year.of(rs.getInt("year")), rs.getString("url"))) ;
			}
			
			conn.close();
			return list ;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null ;
		}
	}
	
	

	public List<Circuit> getCircuitiForStagione(Season season) {
		String sql = "SELECT circuits.* " + 
				"FROM races,circuits " + 
				"WHERE races.circuitId=circuits.circuitId " + 
				"AND races.year=?";
				
		
		try {
			Connection conn = DBConnect.getConnection() ;

			PreparedStatement st = conn.prepareStatement(sql) ;
			
			st.setInt(1,season.getYear().getValue() );
			
			ResultSet rs = st.executeQuery() ;
			
			List<Circuit> list = new ArrayList<>() ;
			while(rs.next()) {
				list.add(new Circuit(
						rs.getInt("circuitId"),
						rs.getString("circuitRef"),
						rs.getString("name"),
						rs.getString("location"),
						rs.getString("country"),
						rs.getFloat("lat"),
						rs.getFloat("lng"),
						rs.getInt("alt"),
						rs.getString("url")
						)) ;
			}
			
			conn.close();
			return list ;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null ;
		}
	}



	public List<Driver> getPilotiPerGara(Season s, Circuit c) {
	String sql ="SELECT drivers.* " + 
			"FROM races,drivers,results " + 
			"WHERE races.year=? " + 
			"AND races.circuitId=? " + 
			"AND results.raceId=races.raceId " + 
			"AND results.driverId=drivers.driverId";
		
		try {
			Connection conn = DBConnect.getConnection() ;

			PreparedStatement st = conn.prepareStatement(sql) ;
			st.setInt(1, s.getYear().getValue());
			st.setInt(2, c.getCircuitId());
			ResultSet rs = st.executeQuery() ;
			
			List<Driver> list = new ArrayList<>() ;
			while(rs.next()) {
				list.add(new Driver(
						rs.getInt("driverId"),
						rs.getString("driverRef"),
						rs.getInt("number"),
						rs.getString("code"),
						rs.getString("forename"),
						rs.getString("surname"),
						rs.getDate("dob").toLocalDate(),
						rs.getString("nationality"),
						rs.getString("url")
						)) ;
			}
			conn.close();
			return list ;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null ;
		}
		
		
	}



	public List<FantaDriver> getFantaGiocatori(Driver d, Circuit c) {
		
		String sql = "SELECT races.year " + 
				"FROM results,races " + 
				"WHERE results.driverId=? " + 
				"AND races.raceId=results.raceId " + 
				"AND races.circuitId=? " + 
				"ORDER BY races.year" ;
		
		try {
			Connection conn = DBConnect.getConnection() ;

			PreparedStatement st = conn.prepareStatement(sql) ;
			st.setInt(1, d.getDriverId());
			st.setInt(2, c.getCircuitId());
			
			ResultSet rs = st.executeQuery() ;
			
			List<FantaDriver> list = new ArrayList<>() ;
			while(rs.next()) {
				list.add(new FantaDriver(rs.getInt("races.year"))) ;
			}
			
			conn.close();
			return list ;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null ;
		}
		
		
	}



	public Map<Integer, LapTime> getTempiPerGiocatore(FantaDriver fd, Driver d, Circuit c) {
		String sql = "SELECT laptimes.* " + 
				"FROM results,races, laptimes " + 
				"WHERE results.driverId=? " + 
				"AND races.raceId=results.raceId " + 
				"AND races.circuitId=? " + 
				"AND laptimes.driverId=results.driverId " + 
				"AND races.raceId=laptimes.raceId " + 
				"AND races.year=?";
		
		try {
			Connection conn = DBConnect.getConnection() ;

			PreparedStatement st = conn.prepareStatement(sql) ;
			st.setInt(1, d.getDriverId());
			st.setInt(2, c.getCircuitId());
			st.setInt(3, fd.getYear());
			
			ResultSet rs = st.executeQuery() ;
			
			Map<Integer,LapTime> map = new HashMap<>() ;
			while(rs.next()) {
				map.put(rs.getInt("lap"),new LapTime(
						rs.getInt("raceId"),
						rs.getInt("driverId"),
						rs.getInt("lap"),
						rs.getInt("position"),
						rs.getString("time"),
						rs.getInt("milliseconds")
						
						
						));
			}
			
			conn.close();
			return map ;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null ;
		}
		
		

	}
	
}
