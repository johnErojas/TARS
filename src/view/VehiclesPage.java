
package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import model.DatabaseConnection.Status;
import model.Vehicle;
import presenter.PagesHandler;
import view.componets.ListAdapter;
import view.componets.ListConstructor.ListRow;
import view.componets.Theme;
import view.componets.bodypages.VehicleListBody;


/**
 *
 * @author johnrojas
 */
public class VehiclesPage extends PageAbstract{

    private VehicleListBody listBody;
    private ArrayList<Vehicle> vehicles;
    private ArrayList<Vehicle> vehiclesFiltered;
    private Vehicle currnetVehicle;
    private final int listH = 75;
    
    private static final String CMD_ADD = "add";
    private static final String CMD_EDIT = "edit";
    private static final String CMD_DELETE= "delete";
    private static final String CMD_CLEAR_FILTER= "clear";
    
    Alert alert = new Alert();
    //LISTENERS:
    MouseAdapter mouseAdapter = new MouseAdapter() {
        @Override
        public void mousePressed(MouseEvent e) {
            switch(e.getComponent().getName()){
                 case CMD_ADD:
                     viewCtx.getPagesHandler().openPage(PagesHandler.PAGES.NEW_VEHICLE);
                     break;
                case CMD_EDIT:
                    if(currnetVehicle!=null)
                        viewCtx.getPagesHandler().openPage(PagesHandler.PAGES.EDIT_VEHICLE);
                     break;
                 case CMD_DELETE:
                    if(currnetVehicle!=null)
                        deleteVehicle();
                     break;
                case CMD_CLEAR_FILTER:clearFilter();break;
             }
        }       
    };
    
    // filter by:
   private final KeyListener kl = new KeyAdapter() {
        @Override
        public void keyReleased(KeyEvent e) {
            if(listBody.getCheckOwner().isSelected()){
                filterByOwner();
            }else{
                filterByPlate();
            }
        }

        private void filterByOwner() {
            String pattern = listBody.getFilterInput().getText();
            vehiclesFiltered = new ArrayList<>();

            for(Vehicle item:vehicles){
                if(item.getOwnerName().toLowerCase().contains(pattern.toLowerCase())){
                    boolean exists = vehiclesFiltered.contains(item);
                    if(!exists)vehiclesFiltered.add(item);
                }
            }
            printRows();
        }

        private void filterByPlate() {
            String pattern = listBody.getFilterInput().getText();
            vehiclesFiltered = new ArrayList<>();

            for(Vehicle item:vehicles){
                if(item.getPlate().toLowerCase().contains(pattern.toLowerCase())){
                    boolean exists = vehiclesFiltered.contains(item);
                    if(!exists)vehiclesFiltered.add(item);
                }
            }
            printRows();
        }
    };
    
    
    private final ActionListener al = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if("plate".equals(e.getActionCommand())){
                listBody.getCheckPlate().setSelected(true);
                listBody.getCheckPlate().removeActionListener(al);
                listBody.getCheckPlate().setEnabled(false);
                
                
                listBody.getCheckOwner().setSelected(false);
                listBody.getCheckOwner().addActionListener(al);
                listBody.getCheckOwner().setEnabled(true);
                
                //listBody.getFilterLabel().setText("Filter by plate number");
                
            }else{
                listBody.getCheckPlate().setSelected(false);
                listBody.getCheckPlate().addActionListener(al);
                listBody.getCheckPlate().setEnabled(true);
                
                
                listBody.getCheckOwner().setSelected(true);
                listBody.getCheckOwner().removeActionListener(al);
                listBody.getCheckOwner().setEnabled(false);
                
                //listBody.getFilterLabel().setText("Filter by owner name");
            }
        }
        
    };    
    @Override
    protected void initConfiguration() {
        getSection().setBackground(Theme.Color.OLIVE);
        setPageIcon(Theme.ICON.VEHICLE);
        setPageTitle("Registered Vehicle List");
        setPageId(PagesHandler.PAGES.VEHICLES);
        listBody = new VehicleListBody();
        appendBody(listBody);
        listBody.getListPanel().setBackground(Theme.Color.DARK);
        listBody.getListPanel().setRowColor(Theme.Color.OLIVE);
        listBody.getListPanel().setRowBG(Theme.Color.BG);
        listBody.getListPanel().setRowBGover(Theme.getColor(Theme.DARK,200));
        listBody.getListPanel().setRowHeight(30);
        
        //FILTER
        listBody.getCheckOwner().setMnemonic(KeyEvent.VK_ENTER);
        listBody.getCheckPlate().setMnemonic(KeyEvent.VK_ENTER);
        listBody.getCheckOwner().setActionCommand("owner");
        listBody.getCheckPlate().setActionCommand("plate");
        
        listBody.getCheckPlate().setSelected(true);
        listBody.getCheckPlate().setEnabled(false);

        listBody.getCheckOwner().setSelected(false);
        listBody.getCheckOwner().addActionListener(al);
        listBody.getCheckOwner().setEnabled(true);
        
        
        setupNames();
    }
    
    private void setupNames(){
        listBody.getAddCmd().setName(CMD_ADD);
        listBody.getEditCmd().setName(CMD_EDIT);
        listBody.getDeleteCmd().setName(CMD_DELETE);
        listBody.getCmdClearFilter().setName(CMD_CLEAR_FILTER);
    }
    
    @Override
    public void updateData() {
        listBody.getAddCmd().addMouseListener(mouseAdapter);
        listBody.getEditCmd().addMouseListener(mouseAdapter);
        listBody.getDeleteCmd().addMouseListener(mouseAdapter);
        listBody.getCmdClearFilter().addMouseListener(mouseAdapter);
        listBody.getFilterInput().addKeyListener(kl);
        //reload list:
        vehicles = modelCtx.getVehicleList();
        String result = "Registered Vehicles: " + vehicles.size();
        listBody.getGuideLabel().setText(result);
        clearFilter();
        
    }
    
    private void printRows(){
        listBody.getListPanel().setEventListener(new ListAdapter() {
            @Override
            public void onRowUnSelected(ListRow row) {
                clearSelection();
            }
            
            @Override
            public void onRowSelected(ListRow row) {
                // get vechicle:
                String id = row.getName();
                currnetVehicle = findVehicle(id);
                printSelection();
                
                listBody.getListPanel().unselectAllExcept(row);
            }
        });
        
        listBody.getListPanel().reset();
        
        vehiclesFiltered.forEach((vehicle) -> {
            ArrayList<String> labels = new ArrayList<>();
            labels.add(vehicle.getPlate());
            labels.add(vehicle.getModel());
            labels.add(String.valueOf(vehicle.getYear()));
            labels.add(String.valueOf(vehicle.getAccidents().size()));
            labels.add(vehicle.getOwnerName());
            labels.add(vehicle.getOwnerAddress());
            labels.add(vehicle.getOwnerPhone());
            listBody.getListPanel().addRow(vehicle.getPlate(),labels);
        });
        listBody.getListPanel().printList();
    }
    
    public Vehicle getSelectedVehicle(){
        return currnetVehicle;
    }
    
    public Vehicle findVehicle(String plate){
        for(Vehicle match : vehicles){
            if(match.getPlate().equals(plate))
                return match;
        }
        //no match
        return null;
    }
    
    private void printSelection(){
        if(currnetVehicle == null){
            clearSelection();
        }else{
            listBody.getCurrentVehicleName().setText(currnetVehicle.getOwnerName());
            listBody.getCurrentVehiclePlate().setText(currnetVehicle.getPlate());    
        }
        
    }
    
    private void clearSelection(){
        currnetVehicle = null;
        listBody.getCurrentVehicleName().setText("-");
        listBody.getCurrentVehiclePlate().setText("No Selected");
    }
    
    private void clearFilter(){
        listBody.getFilterInput().setText("");
        vehiclesFiltered = vehicles;
        printRows();
    }
    
    // DELETE ENTRY
    private void deleteVehicle() {
        String message = "The vehicle whit plate number <strong style='font-size:1.5em;'>"+currnetVehicle.getPlate() +"</strong> will be deleted.<br>"
                + " It also cause removing from any accidents where this vehicle has been involved."
                + "<br><strong>Are you sure you want to coninue?</strong>";
        //int result = JOptionPane.showConfirmDialog(viewCtx.getGui(), message,"Alert",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
        //if(result == JOptionPane.YES_OPTION){
           //listBody.getScroll().clearScroll();
           
        //}
        alert.showQueryMessage(message,new Alert.AlertAdapter() {
            @Override
            public void onYesClicked() {
                alert.closeWindow();
                Status delete = modelCtx.deleteVehicle(currnetVehicle);
                if(delete == Status.ERROR){
                    showErrorDeleteHandle();
                }else{
                   exit();
                }
            }
        });
    }
    
    public void CloseAlert(){
        if(alert.isVisible())
            alert.closeWindow();
    }
    
    private void exit(){
        viewCtx.getPagesHandler().closePage(this);
        viewCtx.getPagesHandler().reloadPage(PagesHandler.PAGES.VEHICLES);
    }
    
    private void showErrorDeleteHandle(){
         String message = "The current vehicle could not be deleted.<br>Please, try again";
        new Alert().showMessage("Error",message);
    }
    
}

