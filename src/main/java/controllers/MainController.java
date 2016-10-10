package controllers;

import models.ActionManager;
import models.MouseClickAction;
import views.MainFrame;

import javax.swing.*;

/**
 * Created by kamilwrobel on 10.10.2016.
 */
public class MainController {
    private ActionManager actionManager;
    private MainFrame view;

    public MainController(ActionManager actionManager){
        this.actionManager = actionManager;
        init();
    }

    public void init(){

    }

    public void startApplication(){
        try{
            SwingUtilities.invokeAndWait(new Runnable() {
                public void run() {
                    view = new MainFrame(actionManager, MainController.this);
                    view.setVisible(true);
                }
            });
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void deleteAction(MouseClickAction action){
        actionManager.removeAction(action);
    }

    public void addNewAction(){
        actionManager.addAction(new MouseClickAction());
    }

    public void updateAction(MouseClickAction action, Object count, Object interval, Object button, Object x, Object y){
        action.setClickPoint(parseInteger(x),parseInteger(y));
        action.setMouseButton(parseInteger(button));
        action.setClickInterval(parseInteger(interval));
        action.setClickCount(parseInteger(count));
    }

    public void updateActionPoint(MouseClickAction action,Object x, Object y){
        action.setClickPoint(parseInteger(x),parseInteger(y));
        actionManager.forceUpdate();
    }

    public void performAllActions(){
        try {
            view.setVisible(false);
            actionManager.perform();
            view.setVisible(true);
            view.setAlwaysOnTop(true);
            view.setAlwaysOnTop(false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private int parseInteger(Object obj){
        int intVal = 0;
        try{
            intVal = Integer.valueOf(obj.toString());
        }catch (Exception ex){

        }
        return intVal;
    }
}
