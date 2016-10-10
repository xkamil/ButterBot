package views;

import controllers.MainController;
import models.ActionManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.AWTEventListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by kamilwrobel on 10.10.2016.
 */
public class MainFrame extends JFrame implements Observer{
    private ActionManager actionManager;
    private MainController controller;
    private JButton performActionsButton;
    private JButton addActionButton;
    private JPanel controlPanel;

    private JPanel contentPanel;

    public MainFrame(ActionManager manager, MainController controller){
        this.actionManager = manager;
        actionManager.addObserver(this);
        this.controller = controller;
        init();
    }

    public void init(){
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        contentPanel = new JPanel(new BorderLayout());
        controlPanel = new JPanel(new GridLayout(1,2));
        controlPanel.setPreferredSize(new Dimension(460,40));
        contentPanel.add(controlPanel, BorderLayout.NORTH);

        performActionsButton = new JButton("perform all actions");
        performActionsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                controller.performAllActions();
            }
        });

        controlPanel.add(performActionsButton);

        addActionButton = new JButton("add action");
        addActionButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                controller.addNewAction();
            }
        });

        controlPanel.add(addActionButton);
        contentPanel.add(new ActionListPanel(actionManager,controller), BorderLayout.CENTER);
        this.setSize(430,250);
        this.setContentPane(contentPanel);
        this.setLocationRelativeTo(null);
    }

    public void update(Observable o, Object arg) {
        this.invalidate();
        this.validate();
        this.repaint();
        pack();
    }
}
