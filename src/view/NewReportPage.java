package view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import model.Accident;
import model.DatabaseConnection.Status;
import model.Vehicle;
import presenter.PagesHandler;
import view.componets.Button;
import view.componets.CommandEventListener;
import view.componets.ListAdapter;
import view.componets.ListConstructor.ListRow;
import view.componets.Selector;
import view.componets.Theme;
import view.componets.bodypages.NewReportBody;
import view.componets.bodypages.NewReportVehicleList;

/**
 *
 * @author johnrojas
 */
public class NewReportPage extends PageAbstract implements CommandEventListener<Button>{
    
    private NewReportBody form;
    private NewReportVehicleList listSelector;

    private enum ACTIONS {
        SHOW_LIST, FILTER_LIST, FINISH_LIST,
        CANCEL_LISTL,NEW_VEHICHLE, CLEAR_FILTER,
        SUBMIT
    }
    
    private ArrayList<Vehicle> vehicles;
    private ArrayList<Vehicle> vehiclesOnList;
    private ArrayList<Vehicle> vehiclesInvolved;
    
    //LISTENERS:
    private final ActionListener al = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if("plate".equals(e.getActionCommand())){
                listSelector.getCheckPlate().setSelected(true);
                listSelector.getCheckPlate().removeActionListener(al);
                listSelector.getCheckPlate().setEnabled(false);
                
                
                listSelector.getCheckOwner().setSelected(false);
                listSelector.getCheckOwner().addActionListener(al);
                listSelector.getCheckOwner().setEnabled(true);
                
                listSelector.getFilterLabel().setText("Filter by plate number");
                
            }else{
                listSelector.getCheckPlate().setSelected(false);
                listSelector.getCheckPlate().addActionListener(al);
                listSelector.getCheckPlate().setEnabled(true);
                
                
                listSelector.getCheckOwner().setSelected(true);
                listSelector.getCheckOwner().removeActionListener(al);
                listSelector.getCheckOwner().setEnabled(false);
                
                listSelector.getFilterLabel().setText("Filter by owner name");
            }
        }
        
    };
    
    private final KeyListener kl = new KeyAdapter() {
        @Override
        public void keyReleased(KeyEvent e) {
            if(listSelector.getCheckOwner().isSelected()){
                filterByOwner();
            }else{
                filterByPlate();
            }
        }
    };
    
    private final ListAdapter listAdapter = new ListAdapter() {
        @Override
        public void onRowUnSelected(ListRow row) {
            System.out.println(row.getName()+" unselected");
            Vehicle car = findVehicle(row.getName());
            if(car == null)return;
            vehiclesInvolved.remove(car);
            listSelector.getSelectedOutput().setText(vehiclesInvolved.size()+"");
        }

        @Override
        public void onRowSelected(ListRow row) {
            System.out.println(row.getName()+" selected");
            Vehicle car = findVehicle(row.getName());
            if(car == null)return;
            vehiclesInvolved.add(car);
            listSelector.getSelectedOutput().setText(vehiclesInvolved.size()+"");
        }
        
    };
    
    
    
    @Override
    protected void initConfiguration() {
        setPageIcon(Theme.ICON.REPORT);
        setPageTitle("New Accident Report");
        setPageId(PagesHandler.PAGES.NEW_REPORT);
        form  = new NewReportBody();
        form.getListPanel().setRowHeight(30);
        form.getListPanel().setBackground(Theme.Color.DARK);
        form.getListPanel().setRowBG(Theme.getColor(Theme.DARK, 50));
        form.getListPanel().setRowColor(Theme.Color.OLIVE_CLEAR);
        appendBody(form);
        
        listSelector = new NewReportVehicleList();
        listSelector.setLocationRelativeTo(this);
        listSelector.getListPanel().setRowHeight(30);
        listSelector.getListPanel().setRowBG(Theme.Color.DARK);
        listSelector.getListPanel().setRowBGover(Theme.Color.BG);
        
        
        form.getCmdVehiclesList().setName(ACTIONS.SHOW_LIST.toString());
        form.getSubmit().setName(ACTIONS.SUBMIT.toString());      
        
        listSelector.getCmdCancel().setName(ACTIONS.CANCEL_LISTL.toString());
        listSelector.getCmdFinish().setName(ACTIONS.FINISH_LIST.toString());
        listSelector.getCmdNew().setName(ACTIONS.NEW_VEHICHLE.toString());
        listSelector.getCmdClearFilter().setName(ACTIONS.CLEAR_FILTER.toString());
        
        // TOO LONG LOCATION:
        form.getLocationIN().addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyReleased(java.awt.event.KeyEvent evt) {
                String location =form.getLocationIN().getText();
                if(location.length()>100){
                    form.getErrorLabel().setForeground(Theme.Color.DANGER);
                    form.getErrorLabel().setText("Too long location. Please use simple address and use comments to describe location in detail");
                    form.getLocationIN().setText("");
                }
            }
        });
        
        //
        LocalDateTime now = Theme.getNow();
        int index = now.getDayOfMonth()-1;
        // DATE:
        form.getDayIN().initRendering();
        for(int i =0;i<31;i++){
            String day = (i<9 ? "0":"") + String.valueOf(((i)+1));
            form.getDayIN().addItem(new Selector.Option(String.valueOf(((i)+1)), day));
        }
        form.getDayIN().setSelectedIndex(index);
        
        //
        String[] months = new String[]{
            "January","Febrary","March","April","May","June","July",
            "August","September","November","December"
        };
        index = now.getMonthValue()-1;
        form.getMonthIN().initRendering();
        for(int i =0;i<months.length;i++){
            String month =  (i<9 ? "0":"") + String.valueOf(((i)+1));
            form.getMonthIN().addItem(new Selector.Option(month, months[i]));
        }
        form.getMonthIN().setSelectedIndex(index);
        
        
        int year = now.getYear() - 30;
        form.getYearIN().initRendering();
        for(int i =30;i>0;i--){
            String txt = String.valueOf((year)+i);
            form.getYearIN().addItem(new Selector.Option(txt, txt));
        }
        
        
        
        // TIME:
        index = now.getHour();
        index = index > 12 ? (index % 12)-1 : index -1 ;
        form.getHoursIN().initRendering();
        for(int i =0;i<12;i++){
            String hour = (i<9 ? "0":"") +  String.valueOf(((i)+1));
            form.getHoursIN().addItem(new Selector.Option(hour,hour));
        }
        form.getHoursIN().setSelectedIndex(index);
        
        
        index = now.getMinute();
        form.getMinutesIN().initRendering();
        for(int i =0;i<60;i++){
            String min = (i<10 ? "0":"") + String.valueOf(((i)));
            form.getMinutesIN().addItem(new Selector.Option(min,min));
        }
        form.getMinutesIN().setSelectedIndex(index);
        
        index = now.getHour() > 12 ? 1: 0;
        form.getAmpmIN().initRendering();
        form.getAmpmIN().addItem(new Selector.Option("am", "AM"));
        form.getAmpmIN().addItem(new Selector.Option("pm", "PM"));
        form.getAmpmIN().setSelectedIndex(index);
    }
    
    @Override
    public void updateData() {
        form.getCmdVehiclesList().setCmdListener(this);
        listSelector.getCmdCancel().setCmdListener(this);
        listSelector.getCmdFinish().setCmdListener(this);
        listSelector.getCmdNew().setCmdListener(this);
        listSelector.getCmdClearFilter().setCmdListener(this);
        form.getSubmit().setCmdListener(this);
        //
        listSelector.getCheckOwner().setMnemonic(KeyEvent.VK_ENTER);
        listSelector.getCheckPlate().setMnemonic(KeyEvent.VK_ENTER);
        listSelector.getCheckOwner().setActionCommand("owner");
        listSelector.getCheckPlate().setActionCommand("plate");
        
        //default:
        listSelector.getCheckPlate().setSelected(true);
        listSelector.getCheckPlate().setEnabled(false);

        listSelector.getCheckOwner().setSelected(false);
        listSelector.getCheckOwner().addActionListener(al);
        listSelector.getCheckOwner().setEnabled(true);
        
        listSelector.getFilterInput().addKeyListener(kl);
        listSelector.getListPanel().setEventListener(listAdapter);
        //
        
        vehicles = modelCtx.getVehicleList();
        vehiclesOnList = vehicles;
        vehiclesInvolved = new ArrayList<>();
        
        resetForm();
    }
     

    @Override
    public void onCommandClicked(Button cmd) {
        switch(parseAction(cmd.getName())){
            case SHOW_LIST:showList();break;
            case CANCEL_LISTL:
                closeList();
                clearFilter();
                break;
            case FINISH_LIST:printInvolvedList();break;
            case CLEAR_FILTER:clearFilter();break;
            case NEW_VEHICHLE:
                closeList();
                viewCtx.getPagesHandler().reloadPage(PagesHandler.PAGES.NEW_VEHICLE);
                break;
            case SUBMIT:validateData();break;
        }
    }
    
    private ACTIONS parseAction(String action){
        ACTIONS match = null;
        switch(action){
            case "SHOW_LIST":match = ACTIONS.SHOW_LIST;break;
            case "FILTER_LIST":match = ACTIONS.FILTER_LIST;break;
            case "CLEAR_FILTER":match = ACTIONS.CLEAR_FILTER;break;
            case "CANCEL_LISTL":match = ACTIONS.CANCEL_LISTL;break;
            case "FINISH_LIST":match = ACTIONS.FINISH_LIST;break;
            case "NEW_VEHICHLE":match = ACTIONS.NEW_VEHICHLE;break;
            case "SUBMIT":match = ACTIONS.SUBMIT;break;
        }
        return match;
    }
    
    
    // lists ineractions:
    private Vehicle findVehicle(String plate){
        for(Vehicle item:vehicles){
            if(item.getPlate().equals(plate))
                return item;
        }
        return null;
    }

    /*::::::::|VEHICLE SELECTOR|:::::::::::::*/
    private void showList(){
        System.out.println("showing linst");
        java.awt.EventQueue.invokeLater(() -> {
            viewCtx.getGui().setVisible(false);
            listSelector.setVisible(true);
            prepateSelectorScroll();
            printList();
        });
    }
    
    private void closeList(){
        listSelector.setVisible(false);
        viewCtx.getGui().setVisible(true);
    }
    
    private void prepateSelectorScroll(){
        listSelector.getCheckPlate().setSelected(true);
        listSelector.getCheckOwner().setSelected(false);
        listSelector.getListPanel().setBackground(Theme.Color.DARK);
        
        //listSelector.getScrollSelector().setRowBG(Theme.Color.BG);
        //listSelector.getScrollSelector().setRowBGover(Theme.DARK,200);
        //listSelector.getScrollSelector().setRowHeight(30);
    }
    
    
    // List of all vehicles to selection
    private void printList(){
        listSelector.getListPanel().reset();
        // If there are selected vehicles keep on top
        if(!vehiclesInvolved.isEmpty()){
            ArrayList<Vehicle> repository = vehiclesOnList;//
            vehiclesOnList = new ArrayList<>();
            for (Vehicle item : vehiclesInvolved) {
                vehiclesOnList.add(item);
            }

            for (Vehicle item : repository) {
                if(!vehiclesInvolved.contains(item)){//avoid clones
                    vehiclesOnList.add(item);
                }
            }
        }
        
        for(Vehicle vehicle: vehiclesOnList){
            ArrayList<String> labels = new ArrayList<>();
            labels.add(vehicle.getPlate());
            labels.add(vehicle.getModel());
            labels.add(String.valueOf(vehicle.getYear()));
            labels.add(vehicle.getOwnerName());
            labels.add(vehicle.getOwnerAddress());
            labels.add(vehicle.getOwnerPhone());
            listSelector.getListPanel().addRow(vehicle.getPlate(), labels);
        }
        listSelector.getListPanel().printList();
        if(vehiclesOnList.isEmpty())return;
        //active already selected:
        Color bg = listSelector.getListPanel().getRowBGover();
        Color color = listSelector.getListPanel().getRowColorOver();
        vehiclesInvolved.forEach((item)->{
            ListRow row = listSelector.getListPanel().findRow(item.getPlate());
            if(row!=null){
                row.setSelected(true);
                if(bg!=null)row.setBackground(bg);
                if(color!=null){
                    row.getLabels().forEach((label)->{
                        label.setForeground(color);
                    });
                }
                
            }
        });
    }
    
    private void filterByPlate(){
        // filter:
        String pattern = listSelector.getFilterInput().getText();
        ArrayList<Vehicle> matchs = new ArrayList<>();
        //keep:selected:
        vehiclesInvolved.forEach((selected) -> {
            matchs.add(selected);
        });
        
        for(Vehicle item:vehicles){
            if(item.getPlate().toLowerCase().contains(pattern.toLowerCase())){
                boolean exists = matchs.contains(item);
                if(!exists)matchs.add(item);
            }
        }
        if(matchs.isEmpty())
            listSelector.getResultsOutput().setText("0");
        else
            listSelector.getResultsOutput().setText(matchs.size()+"");    
        
        vehiclesOnList = matchs;
        printList();
    }
    
    private void filterByOwner(){
         // filter:
        String pattern = listSelector.getFilterInput().getText();
        ArrayList<Vehicle> matchs = new ArrayList<>();
        //keep:selected:
        vehiclesInvolved.forEach((selected) -> {
            matchs.add(selected);
        });
        
        for(Vehicle item:vehicles){
            if(item.getOwnerName().toLowerCase().contains(pattern.toLowerCase())){
                boolean exists = matchs.contains(item);
                if(!exists)matchs.add(item);
            }
        }
        vehiclesOnList = matchs;
        printList();
        
    }
    
    private void clearFilter() {
        listSelector.getFilterInput().setText("");
        vehiclesOnList = vehicles;
        printList();
    }
    
    // List of selected vehicles
    private void printInvolvedList(){
        closeList();
        form.getListPanel().reset();
        for(Vehicle vehicle: vehiclesInvolved){
            ArrayList<String> labels = new ArrayList<>();
            labels.add(vehicle.getPlate());
            labels.add(vehicle.getModel());
            labels.add(String.valueOf(vehicle.getYear()));
            labels.add(vehicle.getOwnerName());
            labels.add(vehicle.getOwnerAddress());
            labels.add(vehicle.getOwnerPhone());
            form.getListPanel().addRow(vehicle.getPlate(), labels);
        }
        form.getListPanel().printList();
        form.getInvolvedCars().setText(String.valueOf(vehiclesInvolved.size()));
    }
    
    private void validateData(){
        //location:
        String location = form.getLocationIN().getText();
        if(location.isBlank() || location.isEmpty()){
            printError(("A location is required").toUpperCase());
            return;
        }
        
        if(location.length()<5){
             printError(("Invalid Location").toUpperCase());
            return;
        }
        
        // Details
        String comments = form.getCommentsIN().getText();
        if(comments.isBlank() || comments.isEmpty()){
            printError(("Comments are required").toUpperCase());
            return;
        }
        
        
        if(vehiclesInvolved.isEmpty()){
            printError(("At least a vehicle must be included in the accident").toUpperCase());
            return;
        }
        
        String day = ((Selector.Option)form.getDayIN().getSelectedItem()).getKey();
        String month = ((Selector.Option)form.getMonthIN().getSelectedItem()).getValue();
        String year = ((Selector.Option)form.getYearIN().getSelectedItem()).getKey();
        String date = day +" "+ month + " " + year;
        if(Theme.parseDate(date) == null){
            printError("ERROR ON DATE");
            return;
        }
        
        String hour = ((Selector.Option)form.getHoursIN().getSelectedItem()).getKey();
        String minute = ((Selector.Option)form.getMinutesIN().getSelectedItem()).getValue();
        String ampm = ((Selector.Option)form.getAmpmIN().getSelectedItem()).getValue();
        String datetime = date + " " +hour +":"+ minute + " " + ampm;
        if(Theme.parseDateTime(datetime) == null){
            printError("ERROR ON TIME");
            return;
        }
        
        Accident accident = new Accident();
        accident.setLocation(location);
        accident.setComments(comments);
        accident.setDate(Theme.parseDateTime(datetime));
        saveAccident(accident);
    }
    
    private void saveAccident(Accident accident){
        successMessage("Saving...");
        Status create = modelCtx.saveAccident(accident, vehiclesInvolved);
        if(create == Status.ERROR){
            printError("Error saving... Please wait to sync again");
            Theme.goToSleep(1000, () -> {
                viewCtx.getPagesHandler().reloadPage(PagesHandler.PAGES.ACCITENS);
            });
        }
        
        if(create == Status.CREATED){
            resetForm();
            form.getErrorLabel().setText("Created, Redirecting to accidents page");
            Theme.goToSleep(1000, () -> {
                viewCtx.getPagesHandler().reloadPage(PagesHandler.PAGES.ACCITENS);
            });
        }
    }
    
    private void resetForm(){
        form.getErrorLabel().setText("");
        form.getLocationIN().setText("");
        form.getCommentsIN().setText("");
        successMessage("");
        printInvolvedList();
    }
    
    private void printError(String error){
        form.getErrorLabel().setOpaque(true);
        form.getErrorLabel().setBackground(Theme.Color.DANGER);
        form.getErrorLabel().setForeground(Theme.Color.CLEAR);
        form.getErrorLabel().setText(error);
    }
    
    private void clearError(){
        form.getErrorLabel().setBackground(null);
        form.getErrorLabel().setForeground(Theme.getColor(Theme.BLUE_GRAY));
        form.getErrorLabel().setText("");
    }
    
    private void successMessage(String message){
        clearError();
        form.getErrorLabel().setText(message);
    }
   
}
