package views;

import controllers.MainController;
import models.MouseClickAction;
import sun.applet.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

/**
 * Created by kamilwrobel on 10.10.2016.
 */
public class ClickPositionView extends JDialog {
    private MouseClickAction action;
    private MainController controller;
    private JPanel contetPanel;

    public ClickPositionView(MouseClickAction action, MainController controller) {
        this.controller = controller;
        this.action = action;
        init();
    }

    public void init() {

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setSize(screenSize);
        this.setUndecorated(true);
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                captureClickPoint(e.getPoint());
            }
        });
        setOpacity(0.2F);
        setVisible(true);
    }

    public void captureClickPoint(Point p) {
        controller.updateActionPoint(action, p.x, p.y);
        this.dispose();
    }

}
