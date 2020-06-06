/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presenter;

import java.util.ArrayList;
import java.util.Random;
import model.Accident;
import model.DatabaseConnection;
import model.Vehicle;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import view.componets.Theme;

/**
 *
 * @author johnrojas
 */
public class AppPresenterTest {
    private final AppPresenter App;
    private enum ACTIONS {CREATE,DELETE}
    private ACTIONS action;
    private int numEntries = 1;
    private int delay = 500;//ml
    
    public AppPresenterTest() {
        this.App = new AppPresenter();
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Test of start method, of class AppPresenter.
     */
    @Before
    public void testStart() {
        this.App.start();
        this.action = ACTIONS.CREATE;
        this.numEntries = 500;
    }

    @Test
    public void runTest(){
        if(this.action == ACTIONS.CREATE){
            addVehicles();
            addAccidents();
        }
        
        
        if(this.action == ACTIONS.DELETE){
            deleteAccidents();
            deleteVehicles();
        }
    }

    private ArrayList<Vehicle> prepareVehicles(){
        String names[] = new String[]{"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
        int A = 0, B = 0, C = 0, D = 0;
        Random random = new Random();
        int range = names.length;
        ArrayList<Vehicle> list = new ArrayList<>();
        for(int i= 0;i<this.numEntries;i++){
            Vehicle vehicle = new Vehicle();
            vehicle.setPlate("T"+i);
            vehicle.setModel("test model");
            vehicle.setYear(Theme.getNow().getYear());
            A = random.nextInt(range);
            B = random.nextInt(range);
            C = random.nextInt(range);
            D = random.nextInt(range);
            String name = names[A]+names[B]+names[C]+names[D];
            vehicle.setOwnerName(name);
            vehicle.setOwnerAddress("test address");
            vehicle.setOwnerPhone("test phonel");
            list.add(vehicle);
        }
        return list;
    }
    
    private ArrayList<Accident> prepareAccidents(){
        ArrayList<Vehicle> vehicles = App.getModelPresenter().getVehicleList();
        boolean exp = false;
        boolean res = vehicles.isEmpty();
        assertEquals(exp, res);
        ArrayList<Accident> accidents =  new ArrayList<>();
        Random random = new Random();
        int range = vehicles.size();
        for(int i= 0;i<this.numEntries;i++){
            Accident accident = new Accident();
            accident.setLocation(i+" Street "+ random.nextInt(this.numEntries));
            accident.setDate(Theme.getNow());
            accident.setComments("delete_me");//Do not change it, because it is necesary to find after creation to get their ID, and create relationship.
            // the query use "where comments=?"
            //take some vehicles in a range
            int vRange = random.nextInt(5) + 1;//to avid get 0 results
            ArrayList<String> plates = new ArrayList<>();
            for(int n = 0;n<vRange;n++){
                //one on the list
                int veIndex = random.nextInt(range);
                String plate = vehicles.get(veIndex).getPlate();
                while(plates.contains(plate)){//get other if already it is in the list
                     veIndex = random.nextInt(range);
                     plate = vehicles.get(veIndex).getPlate();
                }
                plates.add(plate);
               accident.setPlates(plates);
            }
            accidents.add(accident);
        }
        return accidents;
    }
    
    
    private void addVehicles() {
        System.out.println("addVehicles");
        DatabaseConnection.Status expResult = DatabaseConnection.Status.CREATED;
        DatabaseConnection.Status result = DatabaseConnection.addVehicles(prepareVehicles());
        assertEquals(expResult, result);
    }

    public void addAccidents(){
        System.out.println("addAccidents");
        DatabaseConnection.Status expResult = DatabaseConnection.Status.CREATED;
        DatabaseConnection.Status result = DatabaseConnection.addAccidents(prepareAccidents(),App);
        assertEquals(expResult, result);
        
    }

    
    public void deleteAccidents(){
        System.out.println("deleteAccidents");
        //get accidents:
        ArrayList<Accident> repository = App.getModelPresenter().getAccidentsList();
        boolean exp = true;
        boolean res = repository != null;
        assertEquals(exp, res);
        
        
        
        ArrayList<Accident> toDelete = new ArrayList<>();
        for(Accident accident: repository){
            if(accident.getComments().equalsIgnoreCase("delete_me"))
                toDelete.add(accident);
        }
        
        if(!toDelete.isEmpty()){
            DatabaseConnection.Status expResult = DatabaseConnection.Status.DELETED;
            DatabaseConnection.Status result = DatabaseConnection.deleteAccidents(toDelete,App);
            assertEquals(expResult, result);
        }
        
    }
    
    public void deleteVehicles() {
        System.out.println("deleteVehicles");
        DatabaseConnection.Status expResult = DatabaseConnection.Status.DELETED;
        DatabaseConnection.Status result = DatabaseConnection.deleteVehicles(prepareVehicles());
        assertEquals(expResult, result);
    }
    
}
