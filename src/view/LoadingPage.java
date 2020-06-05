/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import presenter.PagesHandler.PAGES;
import view.componets.Theme;
import view.componets.bodypages.LoadingBody;

/**
 *
 * @author johnrojas
 */
public class LoadingPage extends PageAbstract {

    
    public static final int TIME = 1000;
    private PAGES futurePage;
    private int count;
    private LoadingBody lb;
    @Override
    protected void initConfiguration() {
        setPageTitle("");
        lb = new LoadingBody();
        appendBody(lb);
    }

    @Override
    public void updateData() {
        //close any openned  alert:
        viewCtx.getPagesHandler().closeAlerts();
        count = 0;
        lb.getProgressBar().setValue(count);
        Theme.EnterFrame(new Theme.EnterFrameInterface() {
            @Override
            public boolean stopNow() {
                return count >= 100;
            }

            @Override
            public void run() {
                count+=5;
                lb.getProgressBar().setValue(count);
            }

            @Override
            public void onStoped() {
                viewCtx.getPagesHandler().openPage(futurePage);
            }
        }, 100);
    }

    public void setFuturePage(PAGES futurePage) {
        this.futurePage = futurePage;
    }
    
}
