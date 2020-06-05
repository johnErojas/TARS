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
import java.util.ArrayList;
import model.DatabaseConnection.Status;

/**
 *
 * @author johnrojas
 */
public class AccidentQueries {
    public static final String ID = "ACCIDENT_ID";
    public static final String LOCATION = "LOCATION";
    public static final String COMMENTS = "COMMENTS";
    public static final String DATE = "ACCIDENT_DATE";
    
    private DatabaseConnection db;
    public AccidentQueries(DatabaseConnection db) {
        this.db = db;
    }
        
    public int totalAccidents() {
        int total = 0;
            try {
                db.openConnection();
                ResultSet result = db.runQuery("select count(*) from accident");
                total = result.getInt(1);
            } catch (SQLException ex) {
                DatabaseConnection.printError(this, "totalAccidents", ex);
            }finally{
                    db.closeConnection();
            }
        return total;
    }
    
    public Status saveAccident(Accident accident, ArrayList<Vehicle> vehicles){
        try {
            //step 1:check if exists
            db.openConnection();
            if(accidentExists(accident))
                return Status.EXISTS;
            //step 2:save accident
             String query = "insert into "
                    +DatabaseConnection.ACCIDENTS 
                    +" ("
                    + LOCATION +", "
                    + COMMENTS +", "
                    + DATE +") values (?,?,?)";
             PreparedStatement st = db.getStatement(query);
             st.setString(1, accident.getLocation());
             String comments = accident.getComments().replaceAll("\\n","<br>");
             System.out.println("saveAccident:"+comments);
             st.setString(2, comments);
             Timestamp stamp = Timestamp.valueOf(accident.getDate());
             st.setTimestamp(3, stamp);
             st.executeLargeUpdate();
             //find and get id:
             int id = getAccidentID(accident);
             accident.setID(id);
             //save relationshif
             query = "insert into "
                    +DatabaseConnection.TABLE_AC 
                    +" ("+ ID +", "+ VehicleQueries.PLATE +") values (?,?)";
             st = db.getStatement(query);
             for(Vehicle vehicle : vehicles){
                 st.setInt(1,accident.getID());
                 st.setString(2, vehicle.getPlate());
                 st.addBatch();
             }
             st.executeBatch();
        } catch (SQLException ex) {
            DatabaseConnection.printError(this, "saveAccident", ex);
            return Status.ERROR;
        }finally{
            db.closeConnection();
        }
        return Status.CREATED;
    }
    
    private int getAccidentID(Accident accident) throws SQLException{
        String query = "select * from "+DatabaseConnection.ACCIDENTS+" where "+LOCATION+"=? AND "+COMMENTS+"=? AND "+DATE+"=?";
        PreparedStatement st = db.getStatement(query);
        st.setString(1, accident.getLocation());
        String comments = accident.getComments().replaceAll("\\n","<br>");
        System.out.println("getAccidentID:"+comments);
        st.setString(2,comments);
        Timestamp stamp = Timestamp.valueOf(accident.getDate());
        st.setTimestamp(3, stamp);
        ResultSet rs = st.executeQuery();
        if(rs!=null){
            if(rs.next())
                return rs.getInt(1);
        }
        return 0;
    }
    
    private boolean accidentExists(Accident accident) throws SQLException{
        String query = "select count(*) from "+DatabaseConnection.ACCIDENTS+" where "+LOCATION+"=? AND "+COMMENTS+"=? AND "+DATE+"=?";
        PreparedStatement st = db.getStatement(query);
        st.setString(1, accident.getLocation());
        String comments = accident.getComments().replaceAll("\\n","<br>");
        st.setString(2, comments);
        Timestamp stamp = Timestamp.valueOf(accident.getDate());
        st.setTimestamp(3, stamp);
        ResultSet rs = st.executeQuery();
        if(rs!=null){
            if(rs.next()){
                int num =  rs.getInt(1);
                    if(num>0)
                        return true;
            }
        }
        return false;
    }
    
    public ArrayList<Accident> getAccidentsList(){
        ArrayList<Accident> list = new ArrayList<>();
        try {
            db.openConnection();
            String query = "select * from "+DatabaseConnection.ACCIDENTS;
            ResultSet rs = db.runQuery(query);
            if(rs!=null){
                boolean next = true;//to include first row:
                while(next){
                    list.add(buildAccident(rs));
                    next = rs.next();
                }
                list = clearAccidents(list);
            }
        } catch (SQLException ex) {
            DatabaseConnection.printError(this, "getAccidentsList", ex);
        }finally{
            db.closeConnection();
        }
        return list;
    }
    
    public ArrayList<Accident> getAccidentsByVehiclePlate(String plate){
        ArrayList<Accident> list = new ArrayList<>();
        try {
            db.openConnection();
            String query = "select * from "+DatabaseConnection.TABLE_AC +" where "+VehicleQueries.PLATE + "=?";
            PreparedStatement st = db.getStatement(query);
            st.setString(1, plate);
            ResultSet rs = st.executeQuery();
            ArrayList<Integer> accidentsID = new ArrayList<>();
            if(rs!=null){
                while(rs.next()){
                    accidentsID.add(rs.getInt(ID));
                }
            }
            // find accidents:
            if(!accidentsID.isEmpty()){
                query = "select * from "+DatabaseConnection.ACCIDENTS +" where "+ID+ "=?";
                st = db.getStatement(query);
                for(int id : accidentsID){
                    st.setInt(1, id);
                    rs = st.executeQuery();
                    if(rs !=null){
                        while(rs.next()){
                            list.add(buildAccident(rs));
                        }
                    }
                }
            }
        } catch (SQLException ex) {
            DatabaseConnection.printError(this, "getAccidentsByVehiclePlate", ex);
        }finally{
            db.closeConnection();
        }
        return list;
    };
    
    private Accident buildAccident(ResultSet rs) throws SQLException{
        Accident accident =  new Accident();
        accident.setID(rs.getInt(ID));
        accident.setLocation(rs.getString(LOCATION));
        accident.setComments(rs.getString(COMMENTS));
        accident.setDate(rs.getTimestamp(DATE).toLocalDateTime());
        accident.setPlates(getVehiclesIDByAccident(accident.getID()));
        return accident;
    }
    // Destroy accidents that do not have relationship with any vehicle:
    // It could happen after all their vehicles have been deleted.
    private ArrayList<Accident> clearAccidents(ArrayList<Accident> list) throws SQLException{
        // Collect relationship
        String query = "select * from "+DatabaseConnection.TABLE_AC;
        ResultSet resultSet = db.runQuery(query);
        ArrayList<Integer> accidentsID = new ArrayList<>();
        if(resultSet!=null){
            boolean next = true;
            while(next){
                accidentsID.add(resultSet.getInt(AccidentQueries.ID));
                next = resultSet.next();
            }
        }
        // collect accidents to delete:
        ArrayList<Accident> deleteList = new ArrayList<>();
        ArrayList<Accident> finalList = new ArrayList<>();
        list.forEach((item) -> {
            boolean match = false;
            for(int id: accidentsID){
                if(item.getID() == id){// It is a match: keep safe
                    match = true;
                    if(!finalList.contains(item))
                        finalList.add(item);
                }
            }
            if (!match){
                deleteList.add(item);
            }
        });
        
        // Delete accidents collected:
       if(!deleteList.isEmpty()){
            query = "delete from "+DatabaseConnection.ACCIDENTS +" where "+ID+"=?";
            PreparedStatement st = db.getStatement(query);
            for(Accident item: deleteList){
                st.setInt(1, item.getID());
                st.addBatch();
            }
            st.executeBatch();
       }
        return finalList;
    }
    
    public ArrayList<String> getVehiclesIDByAccident(int id) throws SQLException{
        ArrayList<String> list = new ArrayList<>();
        String query = "select * from "+DatabaseConnection.TABLE_AC + " where "+ID+"=?";
        PreparedStatement st = db.getStatement(query);
        st.setInt(1, id);
        ResultSet rs = st.executeQuery();
        if(rs !=null){
            while(rs.next()){
                list.add(rs.getString(VehicleQueries.PLATE));
            }
        }
        return list;
    }
        
}
