/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presenter;

import view.GUIStructure;



/**
 *
 * @author johnrojas
 */
public class View {
    private final AppPresenter appPresenter;
    private final PagesHandler pagesHandler;
    private final GUIStructure gui;
    public View(AppPresenter app) {
        this.appPresenter = app;
        this.gui = new GUIStructure();
        this.pagesHandler = new PagesHandler(this);
    }
    
    
    public void start(){
        gui.start();
        pagesHandler.start();
        pagesHandler.activeDashboardActions(gui.getNav().getItems(), PagesHandler.PAGES.HOME);
        pagesHandler.openPage(PagesHandler.PAGES.HOME);
    }

    public AppPresenter getAppPresenter() {
        return appPresenter;
    }

    public PagesHandler getPagesHandler() {
        return pagesHandler;
    }

    public GUIStructure getGui() {
        return gui;
    }   
}
