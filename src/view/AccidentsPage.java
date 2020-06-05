/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import model.Accident;
import model.Vehicle;
import presenter.PagesHandler;
import view.componets.CommandEventListener;
import view.componets.ListAdapter;
import view.componets.ListConstructor.ListRow;
import view.componets.Theme;
import view.componets.bodypages.AccidentDetails;
import view.componets.bodypages.AccidentsBody;


/**
 *
 * @author johnrojas
 */
public class AccidentsPage extends PageAbstract {
    ArrayList<Accident> accidents;//all accidents
    ArrayList<Accident> accidentsFiltered;//To print al filter accidents
    ArrayList<Vehicle> vehicles;//all vehicles
    AccidentsBody content;
    AccidentDetails details;
    //detailled
    Accident detailledAccident;//to show in detail
    ArrayList<Vehicle> detailledVehicles;//which are involved in detailled accident
    Alert alert;//to inform alert messages
    @Override
    protected void initConfiguration() {
       getSection().setBackground(Theme.Color.CLEAR);
       setPageIcon(Theme.ICON.ACCIDENT);
       setPageTitle("Road Acidents");
       setPageId(PagesHandler.PAGES.ACCITENS);
       content  = new AccidentsBody();
       appendBody(content);
       
       details = new AccidentDetails();
       details.setAlwaysOnTop(true);
       details.setLocationRelativeTo(this);
       details.getCmdClose().setCursor(new Cursor(Cursor.HAND_CURSOR));
       details.getListPanel().setRowHeight(30);
       details.getListPanel().setRowBG(Theme.getColor(Theme.GRAY,50));
       details.getListPanel().setRowColor(Theme.getColor(Theme.CLEAR,100));
       
       content.getListPanel().setRowHeight(30);
       content.getListPanel().setBackground(Theme.Color.BG);
       content.getListPanel().setRowColor(Theme.Color.CLEAR);
       content.getListPanel().setRowBGover(Theme.Color.OLIVE);
       
       
    }

    
    @Override
    public void updateData() {
        accidents = modelCtx.getAccidentsList();
        vehicles = modelCtx.getVehicleList();
        accidentsFiltered = accidents;
        content.getTotalOut().setText(String.valueOf(accidents.size()));
        content.getResultsOut().setVisible(false);
        content.getListPanel().setEventListener(new ListAdapter() {
            @Override
            public void onRowSelected(ListRow row) {
                seeAccident(row);
            }
            
        });
        
        
        details.getCmdClose().addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                details.setVisible(false);
                viewCtx.getGui().setVisible(true);
                content.getListPanel().unselectAll();
            }
            
        });
        activeFilter();
        
        printAccidents();
    }

    private void activeFilter(){
        content.getCmdSearch().setCmdListener((CommandEventListener) (Object cmd) -> {
            
            Alert.AlertAdapter adapter = new Alert.AlertAdapter() {
                    @Override
                    public void onClosed() {
                        if(alert !=null){
                            if(alert.isVisible())
                                alert.setVisible(false);
                            //alert.closeWindow();
                        }
                        accidentsFiltered = accidents;
                        content.getSearchLabel().setText("Enter a vehicle plate to see wich accidents it is involved");
                        printAccidents();
                    }
            };
            
            String plate = content.getPlateIN().getText();
            if(plate.isBlank() || plate.isEmpty())return;
            Vehicle v = findVehicle(plate);
            String error = "No results found to the plate number "+plate;
            String title = "Not Results";
            
            if(v == null){//no match on loaded accidents:
                alert = new Alert().showMessage(title,error, adapter);
            }else{//it is a match: proccess database query
                accidentsFiltered = modelCtx.getAccidentsByVehiclePlate(plate);
                if(accidentsFiltered.isEmpty()){// not match at db:
                    alert = new Alert().showMessage(title,error, adapter);
                }else{//all good, show results
                    String s = accidentsFiltered.size() > 1 ? "s":"";
                    content.getSearchLabel().setText(v.getPlate()+" is involved in "+accidentsFiltered.size() + "  accident" + s);
                    printAccidents();
                }
                
            }
        });
        
        content.getCmdClear().setCmdListener((CommandEventListener) (Object cmd) -> {
            if(accidentsFiltered.size() == vehicles.size())return;//it is no filtered
            content.getPlateIN().setText("");
            content.getSearchLabel().setText("Enter a vehicle plate to see wich accidents it is involved");
            accidentsFiltered = accidents;
            printAccidents();
        });
    }
    
    private void printAccidents() {
        content.getListPanel().reset();
        for(Accident item: accidentsFiltered){
            ArrayList<String> labels = new ArrayList<>();
            labels.add(String.valueOf(item.getID()));
            labels.add(item.getDate().format(Theme.getDateTimeFeromater()));
            labels.add(item.getLocation());
            labels.add(String.valueOf(item.getPlates().size()));
            content.getListPanel().addRow(""+item.getID(), labels);
        }
        
        content.getListPanel().printList();
        
        content.getListPanel().getRows().forEach((row)->{
            row.setCursor(new Cursor(Cursor.HAND_CURSOR));
        });
    }
    
    private void seeAccident(ListRow row){
        content.getListPanel().unselectAllExcept(row);
        detailledAccident = findAccident(Integer.parseInt(row.getName()));
        if(detailledAccident == null)return;
        detailledVehicles = new ArrayList<>();
        for(String plate: detailledAccident.getPlates()){
            Vehicle involded = findVehicle(plate);
            if(involded!=null)detailledVehicles.add(involded);
        }
        
        details.getAccidentId().setText(""+detailledAccident.getID());
        details.getAccidentLocation().setText(detailledAccident.getLocation());
        details.getAccidentDate().setText(detailledAccident.getDate().format(Theme.getDateTimeFeromater()));
        details.getAccidentComments().setText(commenstHTML(detailledAccident.getComments()));
        details.getListPanel().reset();
        for(Vehicle vehicle: detailledVehicles){
            ArrayList<String> labels = new ArrayList<>();
            labels.add(vehicle.getPlate());
            labels.add(vehicle.getModel());
            labels.add(""+vehicle.getYear());
            labels.add(vehicle.getOwnerName());
            labels.add(vehicle.getOwnerAddress());
            labels.add(vehicle.getOwnerPhone());
            details.getListPanel().addRow(vehicle.getPlate(), labels);
        }
        
        java.awt.EventQueue.invokeLater(() -> {
            viewCtx.getGui().setVisible(false);
            details.setVisible(true);
            details.getListPanel().printList();
            
            details.getListPanel().getRows().forEach((item) -> {
                item.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 0, 1, 0, Theme.getColor(Theme.LIME, 20)));
            });
            
        });
    }
    
    private String commenstHTML(String comments){
        String html =  "<html>"
                +"  <body style=\"background-color:#282828; color:rgb(105,105,54);\">" 
                +"    <p style=\"margin: 5px;\">" 
                + comments
                +"    </p>" 
                +"  </body>" 
                +"</html>";
        return html;
    }
    
    private Accident findAccident(int id){
        for(Accident accident: accidents){
            if(accident.getID() == id)
                return accident;
        }
        return null;
    }
    
    private Vehicle findVehicle(String id){
        for(Vehicle vehicle: vehicles){
            if(vehicle.getPlate().toLowerCase().equals(id.toLowerCase()))
                return vehicle;
        }
        return null;
    }
    
}

