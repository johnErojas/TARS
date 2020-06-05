/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import model.DatabaseConnection.Status;
import model.Vehicle;
import model.VehicleQueries;
import presenter.PagesHandler;
import view.componets.Button;
import view.componets.CommandEventListener;
import view.componets.Theme;
import view.componets.bodypages.EditVehicleBody;

/**
 *
 * @author johnrojas
 */
public class EditVehiclePage extends PageAbstract
{
    private EditVehicleBody form;
    private Vehicle vehicle;
    @Override
    protected void initConfiguration() {
        getSection().setBackground(Theme.Color.OLIVE);
        setPageIcon(Theme.ICON.VEHICLE);
        setPageId(PagesHandler.PAGES.NEW_VEHICLE);
        form = new EditVehicleBody();
        appendBody(form);
        form.getSubmit().setCmdListener((CommandEventListener<Button>) (Button cmd) -> {
            validateData();
        });
    }

    @Override
    public void updateData() {
        form.getSubmit().setText("Update");
        PageAbstract listPage = viewCtx.getPagesHandler().getPage(PagesHandler.PAGES.VEHICLES);
        vehicle = ((VehiclesPage)listPage).getSelectedVehicle();
        if(vehicle == null){
            viewCtx.getPagesHandler().openPage(PagesHandler.PAGES.VEHICLES);
            return;
        }
        setPageTitle("Edit Vehicle: "+vehicle.getPlate());
        activeCloseBack(PagesHandler.PAGES.VEHICLES);
        fillForm();
    }
    
    public void fillForm(){
        form.getPlateInput().setText(vehicle.getPlate());
        form.getModelInput().setText(vehicle.getModel());
        form.getYearInput().setText(String.valueOf(vehicle.getYear()));
        form.getOwnerNameInput().setText(vehicle.getOwnerName());
        form.getOwnerAddressInput().setText(vehicle.getOwnerAddress());
        form.getOwnerPhoneInput().setText(vehicle.getOwnerPhone());
    }
    
    private Vehicle parseForm(){
        Vehicle clone = new Vehicle();
        clone.setPlate(form.getPlateInput().getText());
        clone.setModel(form.getModelInput().getText());
        int year;
        try{
            year = Integer.parseInt(form.getYearInput().getText());
        }catch(NumberFormatException e){
            year = 0;
        }
        clone.setYear(year);
        clone.setOwnerName(form.getOwnerNameInput().getText());
        clone.setOwnerAddress(form.getOwnerAddressInput().getText());
        clone.setOwnerPhone(form.getOwnerPhoneInput().getText());
        return clone;
    }
    
    private void resetBtn(){
        form.getSubmit().setText("update");
    }
    
    private void validateData(){
        Vehicle v = parseForm();
        //plate
        if(v.getPlate().isBlank() || v.getPlate().isEmpty() || v.getPlate().length() < 4){
            form.getError().setText("Invalid Plate");
            resetBtn();
            return;
        }
        //model
        if(v.getModel().isBlank() || v.getModel().isEmpty()){
            form.getError().setText("Invalid Model");
            resetBtn();
            return;
        }
        //year
        if(v.getYear() < 1900){
            form.getError().setText("Invalid Year");
            resetBtn();
            return;
        }
        
        //owner name
        if(v.getOwnerName().isBlank() || v.getOwnerName().isEmpty()){
            form.getError().setText("Invalid Owner Name");
            resetBtn();
            return;
        }
        
        //owner address
        if(v.getOwnerAddress().isBlank() || v.getOwnerAddress().isEmpty() || v.getOwnerAddress().length() < 5){
            form.getError().setText("Invalid Owner Address");
            resetBtn();
            return;
        }
        
        //owner phone
        if(v.getOwnerPhone().isBlank() || v.getOwnerPhone().isEmpty() || v.getOwnerPhone().length() < 7 ){
            form.getError().setText("Invalid Owner Phone Number");
            resetBtn();
            return;
        }
        // validation passed:
        form.getSubmit().setText("Saving...");
        Status update = modelCtx.updateVehicle(vehicle.getPlate(),v);
        if(update == Status.ERROR){
            form.getError().setText("Error updating.");
            resetBtn();
            return;
        }
        if(update == Status.EXISTS){
            form.getError().setText(v.getPlate()+" already exists");
            resetBtn();
            return;
        }
        
        vehicle = v;
        viewCtx.getPagesHandler().reloadPage(PagesHandler.PAGES.VEHICLES);
    }
    
}
