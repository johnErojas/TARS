/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import presenter.AppPresenter;
import presenter.PagesHandler;
import view.componets.Theme;
import view.componets.bodypages.HomeBody;


/**
 *
 * @author johnrojas
 */
public class HomePage extends PageAbstract {

    private HomeBody homeBody;
    public HomePage() {
    }
    
    
    @Override
    protected void initConfiguration() {
        getSection().setBackground(Theme.Color.BG);
        setPageIcon(Theme.ICON.HOME);
        setPageTitle("Home");
        setPageId(PagesHandler.PAGES.HOME);
        homeBody = new HomeBody();
        appendBody(homeBody);
    }
    
    @Override
    public void updateData() {
        printTotalAccidents();
    }

    public void printTotalAccidents(){
        String result = "Reported Accitends: " + modelCtx.getTotalAccidents();
        homeBody.getTotalAccidentsLabel().setText(result);
    }


}

