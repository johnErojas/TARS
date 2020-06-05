/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import model.Vehicle;
import model.DatabaseConnection.Status;
import presenter.PagesHandler;
import view.componets.Button;
import view.componets.CommandEventListener;
import view.componets.Theme;
import view.componets.bodypages.AddVehicleBody;

/**
 *
 * @author johnrojas
 */
public class AddVehiclePage extends PageAbstract {

    private AddVehicleBody content;
    @Override
    protected void initConfiguration() {
        getSection().setBackground(Theme.Color.OLIVE);
        setPageIcon(Theme.ICON.VEHICLE);
        setPageTitle("Create New Vehicle");
        setPageId(PagesHandler.PAGES.NEW_VEHICLE);
        content = new AddVehicleBody();
        appendBody(content);
        
        content.getSubmit().setCmdListener((CommandEventListener<Button>) (Button cmd) -> {
            cmd.setText("validating...");
            validateData();
        });
    }

    @Override
    public void updateData() {
        activeCloseBack(PagesHandler.PAGES.VEHICLES);
        resetForm();
        Theme.goToSleep(900, () -> {
            disableDashItem(PagesHandler.PAGES.VEHICLES);
        });
    }
    
    private void resetBtn(){
        content.getSubmit().setText("submit");
    }
    
    private void validateData(){
        Vehicle v = parseForm();
        //plate
        if(v.getPlate().isBlank() || v.getPlate().isEmpty() || v.getPlate().length() < 4){
            content.getError().setText("Invalid Plate");
            resetBtn();
            return;
        }
        //model
        if(v.getModel().isBlank() || v.getModel().isEmpty()){
            content.getError().setText("Invalid Model");
            resetBtn();
            return;
        }
        //year
        if(v.getYear() < 1900){
            content.getError().setText("Invalid Year");
            resetBtn();
            return;
        }
        
        //owner name
        if(v.getOwnerName().isBlank() || v.getOwnerName().isEmpty()){
            content.getError().setText("Invalid Owner Name");
            resetBtn();
            return;
        }
        
        //owner address
        if(v.getOwnerAddress().isBlank() || v.getOwnerAddress().isEmpty() || v.getOwnerAddress().length() < 5){
            content.getError().setText("Invalid Owner Address");
            resetBtn();
            return;
        }
        
        //owner phone
        if(v.getOwnerPhone().isBlank() || v.getOwnerPhone().isEmpty() || v.getOwnerPhone().length() < 7 ){
            content.getError().setText("Invalid Owner Phone Number");
            resetBtn();
            return;
        }
        // validation passed:
        content.getSubmit().setText("Saving...");
        Status add = modelCtx.addNewVehicle(v);
        if(add == Status.EXISTS){
            content.getError().setText(v.getPlate()+" already exists");
            resetBtn();
            return;
        }
        
        viewCtx.getPagesHandler().reloadPage(PagesHandler.PAGES.VEHICLES);
    }
    
    private Vehicle parseForm(){
        Vehicle vehicle = new Vehicle();
        vehicle.setPlate(content.getPlateInput().getText());
        vehicle.setModel(content.getModelInput().getText());
        int year;
        try{
            year = Integer.parseInt(content.getYearInput().getText());
        }catch(NumberFormatException e){
            year = 0;
        }
        vehicle.setYear(year);
        vehicle.setOwnerName(content.getOwnerNameInput().getText());
        vehicle.setOwnerAddress(content.getOwnerAddressInput().getText());
        vehicle.setOwnerPhone(content.getOwnerPhoneInput().getText());
        return vehicle;
    }
    
    
    private void resetForm(){
        content.getPlateInput().setText("");
        content.getModelInput().setText("");
        content.getYearInput().setText("");
        content.getOwnerNameInput().setText("");
        content.getOwnerAddressInput().setText("");
        content.getOwnerPhoneInput().setText("");
        content.getError().setText("");
        resetBtn();
    }
    
}
