/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presenter;

import java.sql.SQLException;
import java.util.ArrayList;
import model.Accident;
import model.AccidentQueries;
import model.DatabaseConnection;
import model.DatabaseConnection.Status;
import model.Vehicle;
import model.VehicleQueries;

/**
 *
 * @author johnrojas
 */
public class Model {
    AppPresenter app;
    AccidentQueries accidentDB;
    VehicleQueries vehicleDB;

    public Model(AppPresenter app) {
        this.app = app;
    }
    
    public boolean start(){
        DatabaseConnection db = new DatabaseConnection(app);
        boolean test = true;
        try {
            test = db.openConnection();
            if(test){
                accidentDB = new AccidentQueries(db);
                vehicleDB = new VehicleQueries(db);
            }
        } catch (SQLException ex) {
            return false;
        }finally{
            db.closeConnection();
        }
        return test;
    }
        
    public int getTotalAccidents(){
        return accidentDB.totalAccidents();
    }
    
    public ArrayList<Accident> getAccidentsList(){
        return accidentDB.getAccidentsList();
    }
    
    public ArrayList<Accident> getAccidentsByVehiclePlate(String plate){
        return accidentDB.getAccidentsByVehiclePlate(plate);
    }
    
    
    public ArrayList<Vehicle> getVehicleList(){
        return vehicleDB.vehiclesList();
    }
    
    public Status addNewVehicle(Vehicle vehicle){
        return vehicleDB.addVehicle(vehicle);
    }
    
    public Status updateVehicle(String id, Vehicle vehicle){
        return vehicleDB.updateVehicle(id, vehicle);
    }
    public Status deleteVehicle(Vehicle vehicle){
        return vehicleDB.deleteVehicle(vehicle);
    }
    
    public Status saveAccident(Accident accident,ArrayList<Vehicle> vehicles){
        return accidentDB.saveAccident(accident, vehicles);
    }
    
}
