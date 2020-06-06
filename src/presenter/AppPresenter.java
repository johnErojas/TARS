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
    
    
    private Alert alert;
    public void noDatabaseConnection(){
        if(view!=null){
            view.getGui().setVisible(false);
        }
        
        alert = new Alert();
        
        
        
        Theme.EnterFrameInterface enterFrameAdapter = new Theme.EnterFrameInterface() {
            int counter = 15;
            @Override
            public boolean stopNow() {
                return counter < 1;
            }
            @Override
            public void run() {
                if(alert == null)return;
                alert.replaceMessage(buildMessageDBError(counter));
                counter--;
            }
            @Override
            public void onStoped() {
                finishSystemByDBError();
            }
        };
        alert.showMessage("DB Error",buildMessageDBError(20),new Alert.AlertAdapter() {
            @Override
            public void onClosed() {
                finishSystemByDBError();
            }
        }).changeIcon(Theme.ICON.getIcon(Theme.ICON.DB_ERROR));
        Theme.EnterFrame(enterFrameAdapter, 1000);
        
    }
    
    
    private String buildMessageDBError(int counter){
        return "The System cannot connect to the database<br>Please check your connection and try again in <h1 style=\"text-align:center\"> " + counter + "</h1>";
    }
    private void finishSystemByDBError(){
        if(alert !=null){
            alert.closeWindow();
            System.exit(0);
            alert = null;
        }
        
    }
}
