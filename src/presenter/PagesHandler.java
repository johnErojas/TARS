/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presenter;

import java.util.ArrayList;
import javax.swing.JPanel;
import view.AccidentsPage;
import view.AddVehiclePage;
import view.EditVehiclePage;
import view.HomePage;
import view.LoadingPage;
import view.NewReportPage;
import view.PageAbstract;
import view.VehiclesPage;
import view.componets.CommandEventListener;
import view.componets.Dashboard;
import view.componets.DashboardItem;
import view.componets.Theme;

/**
 *
 * @author johnrojas
 */
public class PagesHandler implements PageHandlerInterface{

    public enum PAGES {
        HOME, LOADING,
        ACCITENS,
        VEHICLES, NEW_VEHICLE,EDIT_VEHICLE,
        NEW_REPORT
    };
    //
    
    private final View viewCtx;
    private final Dashboard dashboad;
    private final JPanel viewContainer;
    
    private final CommandEventListener onDashboarItemInterface;
    //pages:
    private PageAbstract currentPage;
    //
    private LoadingPage loadingPage;
    private HomePage home;
    private NewReportPage report;
    private AccidentsPage accidents;
    private VehiclesPage vehicles;
    private AddVehiclePage addVehicle;
    private EditVehiclePage editVehicle;
    
    
    public PagesHandler(View viewContext) {
        this.viewCtx = viewContext;
        this.dashboad = viewContext.getGui().getNav();
        this.viewContainer = viewContext.getGui().getView();
        this.onDashboarItemInterface = (CommandEventListener<DashboardItem>) this::openPage;
    }
    
    public void start(){
        loadingPage = new LoadingPage();
        home = new HomePage();
        report = new  NewReportPage();
        accidents = new AccidentsPage();
        vehicles = new VehiclesPage();
        addVehicle = new AddVehiclePage();
        editVehicle = new EditVehiclePage();
        
        loadingPage.start(viewCtx.getAppPresenter());
        home.start(viewCtx.getAppPresenter());
        report.start(viewCtx.getAppPresenter());
        accidents.start(viewCtx.getAppPresenter());
        vehicles.start(viewCtx.getAppPresenter());
        addVehicle.start(viewCtx.getAppPresenter());
        editVehicle.start(viewCtx.getAppPresenter());
        
        loadingPage.setName("loadingPage");
        home.setName("homePage");
        report.setName("reportPage");
        accidents.setName("accidentsPage");
        //
        vehicles.setName("vehiclesPage");
        addVehicle.setName("addVehiclePage");
        editVehicle.setName("editVehiclePage");
    }
    
    
    
    public void activeDashboardActions(ArrayList<DashboardItem> list, PAGES current){
        list.forEach((item) -> {
            item.setEventClick(onDashboarItemInterface);
            if(current!=null && current == item.getPage())item.disableListener();
        });
    }
    
    public void openPage(DashboardItem item){
        openPage(item.getPage());
    }

    private final int timeToDisableDashItem = 300;
    @Override
    public void openPage(PAGES page) {
        switch(page){
            case LOADING:
                openPage(loadingPage);
                break;
            case HOME:
                openPage(home);
                Theme.goToSleep(timeToDisableDashItem, home::disableDashItem);
                break;
            case NEW_REPORT:
                openPage(report);
                Theme.goToSleep(timeToDisableDashItem, report::disableDashItem);
                break;
            case ACCITENS:
                openPage(accidents);
                Theme.goToSleep(timeToDisableDashItem, accidents::disableDashItem);
                break;
            case VEHICLES:
                openPage(vehicles);
                Theme.goToSleep(timeToDisableDashItem, vehicles::disableDashItem);
                break;
            case NEW_VEHICLE:
                openPage(addVehicle);
                break;
            case EDIT_VEHICLE:
                openPage(editVehicle);
                break;
            default:
                System.err.println("No page found");
                break;
        }
    }
    
    @Override
    public void openPage(PageAbstract page){
        if(currentPage != null && currentPage.getPageId() == page.getPageId())return;
        if(currentPage != null)viewContainer.remove(currentPage);
        viewContainer.add(page,page.getName()); 
        viewCtx.getGui().setCurrent(page);
        viewCtx.getGui().pack();
        page.updateData();//calls model to update changes
        currentPage = page;
        Theme.goToSleep(300, ()->{
            page.disableDashItem();
        });
    }
    
    public void closePage(PageAbstract page){
        if(currentPage == page){
            viewContainer.remove(page);
            currentPage = null;
        }
    }
    
    public void reloadPage(PAGES page){
        loadingPage.setFuturePage(page);
        openPage(PAGES.LOADING);
    }
    
    public PageAbstract getPage(PAGES page){
        PageAbstract match = null;
        switch(page){
            case LOADING:match = loadingPage;break;
            case HOME:match = home;break;
            case NEW_REPORT:match = report;break;
            case ACCITENS:match = accidents;break;
            case VEHICLES:match = vehicles;break;
            case NEW_VEHICLE:match = addVehicle;break;
            case EDIT_VEHICLE:match = editVehicle;break;
        }
        return match;
    }

    @Override
    public DashboardItem getDashItemByPage(PAGES name) {
        for(DashboardItem item:dashboad.getItems()){
            if(item.getPage() == name)return item;
        }
        return null;
    }
    
        
    @Override
    public void disableDashboardItem(DashboardItem item){
        if(item == null)return;
        dashboad.getItems().forEach((match) ->{
            if(match.getPage() == item.getPage()){
                match.disableListener();
            }else{
                match.enableListener();
            }
        });
    }
    
    public void closeAlerts(){
        //run close alert for all pages thar use Alert queries
        vehicles.CloseAlert();
    }
}
