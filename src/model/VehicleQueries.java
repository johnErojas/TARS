/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import model.DatabaseConnection.Status;

/**
 *
 * @author johnrojas
 */
public class VehicleQueries {
    private final DatabaseConnection db;
    private ResultSet resultSet;
    
    public static final String TABLE = "vehicle";
    public static final String PLATE = "VEHICLE_ID";
    public static final String MODEL = "MODEL";
    public static final String YEAR = "MAKE_YEAR";
    public static final String OWNER_NAME = "OWNER_NAME";
    public static final String OWNER_ADDRESS = "ADDRESS";
    public static final String OWNER_PHONE = "PHONE";
    
    public VehicleQueries(DatabaseConnection db) {
        this.db = db;
    }
    
    public int totalVehicles() {
        int total = 0;
            try {
                db.openConnection();
                resultSet = db.runQuery("select count(*) from " + DatabaseConnection.VEHICLES);
                total = resultSet.getInt(1);
            } catch (SQLException ex) {
                DatabaseConnection.printError(this, "totalVehicles", ex);
            }finally{
                    db.closeConnection();
            }
        return total;
    }    
    
    public ArrayList<Vehicle> vehiclesList(){
        ArrayList<Vehicle> list = new ArrayList<>();
        try {
            db.openConnection();
            resultSet = db.runQuery("select * from "+DatabaseConnection.VEHICLES);
            if(resultSet != null){
                list.add(buildVehicle(resultSet));
                while(resultSet.next())
                    list.add(buildVehicle(resultSet));
            }
        } catch (SQLException ex) {
            DatabaseConnection.printError(this, "vehiclesList", ex);
        }finally{
            db.closeConnection();
        }
        return list;
    }
    
    private void findAccidents(Vehicle vehicle) throws SQLException{
        //get links
        String query = "select * from "+DatabaseConnection.TABLE_AC+" where "+VehicleQueries.PLATE +"=?";
        PreparedStatement st = db.getStatement(query);
        st.setString(1,vehicle.getPlate());
        ResultSet rs = st.executeQuery();
        if(rs != null){
           while(rs.next()){
               String id = rs.getString("ACCIDENT_ID");
               query = "select * from "+DatabaseConnection.ACCIDENTS+" where "+AccidentQueries.ID+"=?";
               st = db.getStatement(query);
               st.setString(1, id);
               ResultSet rs2 = st.executeQuery();
               if(rs2 != null){
                   while(rs2.next()){
                       joinAccident(rs2, vehicle);
                   }
               }
            }
        }
    }
    
    private void joinAccident(ResultSet result, Vehicle vehicle) throws SQLException{
        Accident accident = new Accident();
        accident.setID(result.getInt(AccidentQueries.ID));
        accident.setLocation(result.getString(AccidentQueries.LOCATION));
        accident.setComments(result.getString(AccidentQueries.COMMENTS));
        Timestamp timestamp = result.getTimestamp(AccidentQueries.DATE);
        LocalDateTime date = timestamp.toLocalDateTime();
        accident.setDate(date);
        vehicle.getAccidents().add(accident);
    }
    
    public Vehicle findVehicle(String plate){
        if(plate == null)return null;
        try {
            db.openConnection();
            String query = "select * from "+VehicleQueries.TABLE + " where "+VehicleQueries.PLATE + "=\""+plate+"\"";
            System.out.println(query);
            resultSet = db.runQuery(query);
            if(resultSet !=null){
                if(resultSet.next())
                return buildVehicle(resultSet);
            }
        } catch (SQLException ex) {
           DatabaseConnection.printError(this, "findVehicle", ex);
        }finally{
            db.closeConnection();
        }
        return null;
    }
    
    private Vehicle buildVehicle(ResultSet resultSet) throws SQLException{        
        Vehicle vehicle = new Vehicle();
        vehicle.setPlate(resultSet.getString(VehicleQueries.PLATE));
        vehicle.setModel(resultSet.getString(VehicleQueries.MODEL));
        vehicle.setYear(resultSet.getInt(VehicleQueries.YEAR));
        vehicle.setOwnerName(resultSet.getString(VehicleQueries.OWNER_NAME));
        vehicle.setOwnerAddress(resultSet.getString(VehicleQueries.OWNER_ADDRESS));
        vehicle.setOwnerPhone(resultSet.getString(VehicleQueries.OWNER_PHONE));
        findAccidents(vehicle);
        return vehicle;
    }

    public Status addVehicle(Vehicle vehicle){
        //exits:
        if(findVehicle(vehicle.getPlate()) != null)return Status.EXISTS;
        try{
            db.openConnection();
            String query = "insert into "
                    +DatabaseConnection.VEHICLES 
                    +" ("
                    + PLATE +", "
                    + MODEL +", "
                    + YEAR +", "
                    + OWNER_NAME +", "
                    + OWNER_ADDRESS +", "
                    + OWNER_PHONE +") values (?,?,?,?,?,?)";
            PreparedStatement st = db.getStatement(query);
            st.setString(1, vehicle.getPlate());
            st.setString(2, vehicle.getModel());
            st.setInt(3, vehicle.getYear());
            st.setString(4, vehicle.getOwnerName());
            st.setString(5, vehicle.getOwnerAddress());
            st.setString(6, vehicle.getOwnerPhone());
            st.executeUpdate();
        } catch (SQLException ex) {
            DatabaseConnection.printError(this, "addVehicle", ex);
            return Status.ERROR;
        }finally{
            db.closeConnection();
        }
        return Status.CREATED;
    }
    
    public Status updateVehicle(String id,Vehicle vehicle){
        try{
            String query;
            PreparedStatement st;
            db.openConnection();
            if(!vehicle.getPlate().equals(id)){
                //exits:more than 1:means that is trying to update the plate to another that already exists
                query = "select count(*) from "+DatabaseConnection.VEHICLES + " where "+PLATE+"=?";
                st = db.getStatement(query);
                st.setString(1, vehicle.getPlate());
                resultSet = st.executeQuery();
                if(resultSet ==null)return Status.ERROR;
                if(resultSet.next()){
                    int num = resultSet.getInt(1);
                    if(num>0)return Status.EXISTS;
                }
            }
            
            query = "update "
                    +DatabaseConnection.VEHICLES
                    +" set "
                    + PLATE +"=?, "
                    + MODEL +"=?, "
                    + YEAR +"=?, "
                    + OWNER_NAME +"=?, "
                    + OWNER_ADDRESS +"=?, "
                    + OWNER_PHONE +"=? "
                    + "where "+PLATE+"=?";
            st = db.getStatement(query);
            st.setString(1, vehicle.getPlate());
            st.setString(2, vehicle.getModel());
            st.setInt(3, vehicle.getYear());
            st.setString(4, vehicle.getOwnerName());
            st.setString(5, vehicle.getOwnerAddress());
            st.setString(6, vehicle.getOwnerPhone());
            st.setString(7, id);
            st.executeUpdate();
        } catch (SQLException ex) {
            DatabaseConnection.printError(this, "updateVehicle", ex);
            return Status.ERROR;
        }finally{
            db.closeConnection();
        }
        return Status.UPDATED;
    }
    
    
    public Status deleteVehicle(Vehicle vehicle){
        // store plate: to destroy relationship whit accidents:
        String plate = vehicle.getPlate();
        try {
            db.openConnection();
            String query = "delete from "+DatabaseConnection.VEHICLES+" where "+PLATE+"= ?";
            PreparedStatement st = db.getStatement(query);
            st.setString(1, vehicle.getPlate());
            st.execute();
            //destroy relationship
            query = "delete from "+DatabaseConnection.TABLE_AC + " where "+PLATE +"=?";
            st = db.getStatement(query);
            st.setString(1, plate);
            st.execute();
        } catch (SQLException ex) {
            DatabaseConnection.printError(this, "deleteVehicle", ex);
            return Status.ERROR;
        }finally{
            db.closeConnection();
        }
        return Status.DELETED;
    }
}
