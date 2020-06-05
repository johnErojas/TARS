package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import presenter.AppPresenter;


/**
 *
 * @author johnrojas
 */
public class DatabaseConnection {

    private static final String USERNAME = "root";
    private static final String PASSWORD = "";
    private static final String URI_CONNECTION = "jdbc:mysql://localhost/accidentdatabase_2";
    
    public static final String VEHICLES = "vehicle";
    public static final String ACCIDENTS = "accident";
    public static final String TABLE_AC = "accident_vehicle";
    
    public static enum Status {
        CREATED,
        DELETED,
        UPDATED,
        EXISTS,
        ERROR
    }
    
    private AppPresenter App;
    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;
    
    public DatabaseConnection(AppPresenter App) {
        this.App = App;
    }

    private boolean testConnection(){
        try {
            // uncoment if required inlcude library:depends on developer environment
            //Class.forName(com.mysql.jdbc.Driver);
            connection = DriverManager.getConnection(URI_CONNECTION, USERNAME, PASSWORD);
        } catch (SQLException ex) {
            printError(this, "testConnection", ex);
            if(App!=null)App.noDatabaseConnection();
            return false;
        }
        return true;
    }
    
    public boolean openConnection() throws SQLException {
        if(!testConnection())return false;
        // uncoment if required inlcude library:depends on developer environment
        //Class.forName(com.mysql.jdbc.Driver);
        connection = DriverManager.getConnection(URI_CONNECTION, USERNAME, PASSWORD);
        System.out.print("connected to db: ");
        return true;
    }
    
    public void closeConnection(){
        if(connection != null){
            try {
                connection.close();
                System.out.println(" | Disconnected from db");
            } catch (SQLException ex) {
                //Logger.getLogger(DatabaseConnection.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public ResultSet runQuery(String query) throws SQLException{
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            resultSet = statement.executeQuery(query);
            if(resultSet !=null){
                if(resultSet.next())
                    return resultSet;
            }
        return null;
    }
    
    public PreparedStatement getStatement(String query) throws SQLException{
            return connection.prepareStatement(query);            
    }
    
    
    public static void printError(Object Class,String from,SQLException e){
            System.err.println("BD Error: "+Class.getClass().toString() + "$" + from);
            System.err.println("code: "+e.getErrorCode());
            System.err.println("Estate: "+e.getSQLState());
            System.err.println("Message: "+e.getMessage());
    }
    
    
    public static Status addVehicles(ArrayList<Vehicle> vehicles){
        DatabaseConnection db = new DatabaseConnection(null);
        try {
            db.openConnection();
             String query = "insert into "
                    +DatabaseConnection.VEHICLES 
                    +" ("
                    + VehicleQueries.PLATE +", "
                    + VehicleQueries.MODEL +", "
                    + VehicleQueries.YEAR +", "
                    + VehicleQueries.OWNER_NAME +", "
                    + VehicleQueries.OWNER_ADDRESS +", "
                    + VehicleQueries.OWNER_PHONE +") values (?,?,?,?,?,?)";
             
            PreparedStatement st = db.getStatement(query);
            for(Vehicle vehicle: vehicles){
                st.setString(1, vehicle.getPlate());
                st.setString(2, vehicle.getModel());
                st.setInt(3, vehicle.getYear());
                st.setString(4, vehicle.getOwnerName());
                st.setString(5, vehicle.getOwnerAddress());
                st.setString(6, vehicle.getOwnerPhone());
                st.addBatch();
            }
            st.executeBatch();
        } catch (SQLException ex) {
            printError(db, "addVehicles", ex);
            return Status.ERROR;
        }finally{
            db.closeConnection();
        }
        return Status.CREATED;
    }
    
    public static Status deleteVehicles(ArrayList<Vehicle> vehicles){
        DatabaseConnection db = new DatabaseConnection(null);
        try {
            db.openConnection();
            String query = "delete from " +DatabaseConnection.VEHICLES +" where "+VehicleQueries.PLATE+"=?";
             
            PreparedStatement st = db.getStatement(query);
            for(Vehicle vehicle: vehicles){
                st.setString(1, vehicle.getPlate());
                st.addBatch();
            }
            st.executeBatch();
        } catch (SQLException ex) {
            printError(db, "deleteVehicles", ex);
            return Status.ERROR;
        }finally{
            db.closeConnection();
        }
        return Status.DELETED;
    }
    
    public static Status addAccidents(ArrayList<Accident> accidents){
        DatabaseConnection db = new DatabaseConnection(null);
        try {
            db.openConnection();
             String query = "insert into "
                    +DatabaseConnection.ACCIDENTS 
                    +" ("
                    + AccidentQueries.LOCATION +", "
                    + AccidentQueries.COMMENTS +", "
                    + AccidentQueries.DATE +") values (?,?,?)";
            PreparedStatement st = db.getStatement(query); 
            
            
            //relationship
             HashMap<Integer,String> map = new HashMap();
            
            for(Accident accident: accidents){
                st.setString(1, accident.getLocation());
                String comments = accident.getComments().replaceAll("\\n","<br>");
                st.setString(2, comments);
                Timestamp stamp = Timestamp.valueOf(accident.getDate());
                st.setTimestamp(3, stamp);
                st.addBatch();
                //save plates: to relationship
                for(String plate: accident.getPlates())
                    map.put(accident.getID(), plate);                
            }
            st.executeBatch();
            
            query = "insert into "
                    +DatabaseConnection.TABLE_AC 
                    +" ("
                    + AccidentQueries.ID +", "
                    + VehicleQueries.PLATE +", "
                    + AccidentQueries.DATE 
                    +") values (?,?,?)";
            st = db.getStatement(query);
            for (Iterator<Map.Entry<Integer, String>> item = map.entrySet().iterator(); item.hasNext();) {
                Map.Entry e = item.next();
                st.setInt(1, (int) e.getKey());
                st.setString(2, (String) e.getValue());
                st.addBatch();
            }
            st.executeBatch();
        } catch (SQLException ex) {
            printError(db, "addVehicles", ex);
            return Status.ERROR;
        }finally{
            db.closeConnection();
        }
        return Status.CREATED;
    }
    
    public static Status deleteAccidents(ArrayList<Accident> accidents){
        DatabaseConnection db = new DatabaseConnection(null);
        try {
            db.openConnection();
            String query = "delete from " +DatabaseConnection.ACCIDENTS +" where "+AccidentQueries.ID+"=?";
            PreparedStatement st = db.getStatement(query);
            for(Accident accident:accidents){
                st.setInt(1, accident.getID());
                st.addBatch();
            }
            st.executeBatch();
            //delete relationship
            query = "delete from " +DatabaseConnection.TABLE_AC +" where "+AccidentQueries.ID+"=?";
            st = db.getStatement(query);
            for(Accident accident:accidents){
                st.setInt(1, accident.getID());
                st.addBatch();
            }
            st.executeBatch();
        } catch (SQLException ex) {
            printError(db, "deleteAccidents", ex);
            return Status.ERROR;
        }finally{
            db.closeConnection();
        }
        return Status.DELETED;
    }
    
}
