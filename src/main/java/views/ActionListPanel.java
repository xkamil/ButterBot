package views;

import controllers.MainController;
import controllers.utils.ImageLoader;
import models.Action;
import models.ActionManager;
import models.MouseClickAction;
import sun.applet.Main;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by kamilwrobel on 10.10.2016.
 */
public class ActionListPanel extends JPanel implements Observer{
    private ActionManager actionManager;
    private MainController controller;

    public ActionListPanel(ActionManager actionManager, MainController controller){
        this.actionManager = actionManager;
        this.controller = controller;
        actionManager.addObserver(this);
        init();
    }

    public void init(){
        update(null, null);
        setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
        setLayout (new BoxLayout (this, BoxLayout.Y_AXIS));
    }

    public void update(Observable o, Object arg) {
        System.out.println("Updating action list");
        this.removeAll();
        for(Action action : actionManager.getActionList()){
            this.add(new ActionView((MouseClickAction)action, controller));
        }
        invalidate();
        validate();
        repaint();
    }

}
