package views;

import controllers.MainController;
import controllers.utils.ImageLoader;
import models.MouseClickAction;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.image.BufferedImage;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by kamilwrobel on 10.10.2016.
 */
public class ActionView extends JPanel{
    private MouseClickAction action;
    private MainController controller;

    private JLabel clickCountLabel;
    private JTextField clickCountField;
    private JLabel clickIntervalLabel;
    private JTextField clickIntervalField;
    private JLabel mouseButtonLabel;
    private JTextField mouseButtonField;
    private JLabel clickPointXField;
    private JLabel clickPointYField;
    private JButton deleteActionButton;
    private JButton showClickPointViewButton;

    private JLabel mouseLPMLabel;
    private JLabel mousePPMLabel;
    private JRadioButton mouseLPMRadio;
    private JRadioButton mousePPMRadio;


    public ActionView(MouseClickAction action, MainController controller){
        this.action = action;
        this.controller = controller;
        init();
    }

    public void init(){
        setLayout(new GridLayout(1,10));
        setBorder(BorderFactory.createEmptyBorder(3,3,3,3));
        setPreferredSize(new Dimension(400,50));

        clickCountLabel = new JLabel(){
            @Override
            public void paintComponent(Graphics g){
                super.paintComponent(g);
                BufferedImage image = ImageLoader.getImage("counter.png");
                g.drawImage(image, 10,10, getWidth()-20, getHeight()-20, null);
            }
        };
        add(clickCountLabel);
        clickCountField = new JTextField();
        clickCountField.setHorizontalAlignment(SwingConstants.CENTER);
        add(clickCountField);
        clickIntervalLabel = new JLabel(){
            @Override
            public void paintComponent(Graphics g){
                super.paintComponent(g);
                BufferedImage image = ImageLoader.getImage("delay.png");
                g.drawImage(image, 10,10, getWidth()-20, getHeight()-20, null);
            }
        };
        add(clickIntervalLabel);
        clickIntervalField = new JTextField();
        clickIntervalField.setHorizontalAlignment(SwingConstants.CENTER);
        add(clickIntervalField);

        mouseLPMLabel = new JLabel(){
            @Override
            public void paintComponent(Graphics g){
                super.paintComponent(g);
                BufferedImage image = ImageLoader.getImage("mouse_lpm.png");
                g.drawImage(image, 0,0, getWidth(), getHeight(), null);
            }
        };
        add(mouseLPMLabel);
        mouseLPMRadio = new JRadioButton();
        mouseLPMRadio.setActionCommand("LPM");
        mouseLPMRadio.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                update();
            }
        });
        add(mouseLPMRadio);

        mousePPMLabel = new JLabel(){
            @Override
            public void paintComponent(Graphics g){
                super.paintComponent(g);
                BufferedImage image = ImageLoader.getImage("mouse_ppm.png");
                g.drawImage(image, 0,0, getWidth(), getHeight(), null);
            }
        };
        add(mousePPMLabel);
        mousePPMRadio = new JRadioButton();
        mousePPMRadio.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                update();
            }
        });
        mousePPMRadio.setActionCommand("PPM");
        add(mousePPMRadio);



        //TODO REMOVE THIS
        mouseButtonLabel = new JLabel("button");
        mouseButtonField = new JTextField();
        mouseButtonField.setHorizontalAlignment(SwingConstants.CENTER);

        showClickPointViewButton = new JButton(){
            @Override
            public void paintComponent(Graphics g){
                super.paintComponent(g);
                BufferedImage image = ImageLoader.getImage("pointer.png");
                g.drawImage(image, 5,5, getWidth()-10, getHeight()-10, null);
            }
        };
        showClickPointViewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new ClickPositionView(action, controller);
            }
        });

        add(showClickPointViewButton);

        clickPointXField = new JLabel();
        clickPointXField.setHorizontalAlignment(SwingConstants.CENTER);
        add(clickPointXField);
        clickPointYField = new JLabel();
        clickPointYField.setHorizontalAlignment(SwingConstants.CENTER);
        add(clickPointYField);

        deleteActionButton = new JButton(){
            @Override
            public void paintComponent(Graphics g){
                super.paintComponent(g);
                BufferedImage image = ImageLoader.getImage("delete.png");
                g.drawImage(image, 10,10, getWidth()-20, getHeight()-20, null);
            }
        };
        deleteActionButton.setBackground(Color.RED);
        deleteActionButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                deleteAction(action);
            }
        });
        add(deleteActionButton);

        clickCountField.setText(String.valueOf(action.getClickCount()));
        clickIntervalField.setText(String.valueOf(action.getClickInterval()));
        if(action.getMouseButton() == InputEvent.BUTTON1_DOWN_MASK){
            mouseLPMRadio.setSelected(true);
        }else{
            mousePPMRadio.setSelected(true);
        }
        clickPointXField.setText(String.valueOf(action.getClickPoint().x));
        clickPointYField.setText(String.valueOf(action.getClickPoint().y));

        new CustomDocumentListener(clickCountField);
        new CustomDocumentListener(clickIntervalField);
        new CustomDocumentListener(mouseButtonField);

        ButtonGroup radioGroup = new ButtonGroup();
        radioGroup.add(mouseLPMRadio);
        radioGroup.add(mousePPMRadio);

    }

    public void deleteAction(MouseClickAction action){
        controller.deleteAction(action);
    }

    private class CustomDocumentListener implements DocumentListener{
        private JTextField field;

        public CustomDocumentListener(JTextField field){
            this.field = field;
            field.getDocument().addDocumentListener(this);
        }

        public void insertUpdate(DocumentEvent e) {
            update();
        }

        public void removeUpdate(DocumentEvent e) {
            update();
        }

        public void changedUpdate(DocumentEvent e) {
            update();
        }
    }

    private void update(){
        controller.updateAction(action,
                (clickCountField.getText()),
                (clickIntervalField.getText()),
                mouseLPMRadio.isSelected() ? String.valueOf(InputEvent.BUTTON1_DOWN_MASK) : String.valueOf(InputEvent.BUTTON2_MASK),
                (clickPointXField.getText()),
                (clickPointYField.getText())
        );
    }

}
