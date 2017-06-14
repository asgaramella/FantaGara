package it.polito.tdp.formula1;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.formula1.model.Circuit;
import it.polito.tdp.formula1.model.Driver;
import it.polito.tdp.formula1.model.FantaStat;
import it.polito.tdp.formula1.model.Model;
import it.polito.tdp.formula1.model.Season;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;

public class Controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<Circuit> boxCircuit;

    @FXML
    private ComboBox<Driver> boxDriver;

    @FXML
    private ComboBox<Season> boxSeason;

    @FXML
    private TextArea txtResult;

	private Model model;
	
    @FXML
    void doTendina(ActionEvent event) {
    	Season s =this.boxSeason.getValue();
    	if(s==null){
    		txtResult.appendText("ERRORE : selezionare una stagione \n");
    		return;
    	}
    	
    	this.boxCircuit.getItems().clear();
    	this.boxCircuit.getItems().addAll(model.getCircuitsForSeasons(s));

    }
	
	

    @FXML
    void doFantaGara(ActionEvent event) {
    	Circuit c=this.boxCircuit.getValue();
    	if(c==null){
    		txtResult.appendText("ERRORE : selezionare un circuito \n");
    		return;
    		
    	}
    	
    	Driver d=this.boxDriver.getValue();
    	if(d==null){
    		txtResult.appendText("ERRORE: selezionare un pilota \n");
    		return;
    	}
    	
    	for(FantaStat fs:model.getClassifica(d, c)){
    		txtResult.appendText("Fantagiocatore "+fs.getDriver().getYear()+" pnt "+fs.getPunteggio()+" \n");
    	}
    	txtResult.appendText("----\n");

    }

    @FXML
    void doInfoGara(ActionEvent event) {
    	Season s=this.boxSeason.getValue();
    	if(s==null){
    		txtResult.appendText("ERRORE : selezionare una stagione \n");
    		return;
    	}
    	Circuit c=this.boxCircuit.getValue();
    	if(c==null){
    		txtResult.appendText("ERRORE : selezionare un circuito \n");
    		return;
    		
    	}
    	
    	txtResult.appendText("Piloti partecipanti nel "+s.getYear().toString()+ " alla gara sul circuito di "+c.getName().toString()+" :\n");
        List<Driver> list=model.getPilotiPerGara(s, c);
        
        for(Driver d:list){
        	txtResult.appendText(d.toString()+"\n");
        }
        
        this.boxDriver.getItems().addAll(list);

    }

    @FXML
    void initialize() {
        assert boxCircuit != null : "fx:id=\"boxCircuit\" was not injected: check your FXML file 'Formula1.fxml'.";
        assert boxDriver != null : "fx:id=\"boxDriver\" was not injected: check your FXML file 'Formula1.fxml'.";
        assert boxSeason != null : "fx:id=\"boxSeason\" was not injected: check your FXML file 'Formula1.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Formula1.fxml'.";

    }

	public void setModel(Model model) {
		this.model = model ;
		this.boxSeason.getItems().addAll(model.getAllSeasons());
	}
}
