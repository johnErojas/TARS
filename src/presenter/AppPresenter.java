package presenter;

import view.Alert;
import view.componets.Theme;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author johnrojas
 */
public class AppPresenter 
{
    View view;
    Model model;

    public AppPresenter() {
        view = new View(this);
        model = new Model(this);
    }
    
    
    public void start(){
        //if model runs ok: then view can start:
        if(model.start()){
            view.start();
        }else{
            view = null;
            noDatabaseConnection();
        }
            
    }

    public View getViewPresenter() {
        return view;
    }

    public Model getModelPresenter() {
        return model;
    }
    
    
    
    public void noDatabaseConnection(){
        if(view!=null){
            view.getGui().setVisible(false);
        }
        
        Theme.EnterFrame(new Theme.EnterFrameInterface() {
            int counter = 5;
            String message = "The System cannot connect to the database<br>Please check your connection and try again in " ;
            Alert alert = new Alert().showMessage("CONNECTION ERROR",message + "<h1 style=\"text-align:center\">" + counter + "</h1>");
            @Override
            public boolean stopNow() {
                return counter < 1;
            }

            @Override
            public void run() {
                alert.replaceMessage(message + "<h1 style=\"text-align:center\">" + counter + "</h1>");
                counter--;
            }

            @Override
            public void onStoped() {
                alert.setVisible(false);
                alert.closeWindow();
                System.exit(0);
            }
        }, 1000);
        
    }
    
}
