package gui;

import utils.CommonsUtilities;
import components.RectangleListener;
import components.RectangleButton;
import utils.Resources;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Giuseppe on 09/05/2015.
 */
public class MainPanel extends JPanel {

    private Image backgroundImage;
    private RectangleButton exitButton;
    private RectangleButton startButton;
    private ArrayList<RectangleButton> buttonArrayList = new ArrayList<>();
    private RectangleListener rectangleListener;

    public MainPanel(){
        this.setSize(CommonsUtilities.FRAME_SIZE);
        this.backgroundImage = Resources.getImage("/img/main-panel-bg.jpg");

        //Inizializzo i rettangoli e li aggiungo alla lista
        initRectangleButton();
        /*
        Se volete conoscere le coordinate del click del mouse decommentate questo blocco
        CoordinateListener coordinateListener = new CoordinateListener();
        this.addMouseListener(coordinateListener);
        */

        //Inizializzo ed aggiungo i listener
        this.rectangleListener = new RectangleListener(buttonArrayList, this);
        this.addMouseListener(rectangleListener);
        this.addMouseMotionListener(rectangleListener);

    }

    private void initRectangleButton(){
        this.exitButton = CommonsUtilities.exitButton;

        this.startButton = new RectangleButton(214, 251, (485-214), (284-251)) {
            @Override
            public void action() {
                MainFrame.switchPanel(MainFrame.getMainPanel(), MainFrame.getDifficultPanel());
            }
        };

        this.buttonArrayList.add(exitButton);
        this.buttonArrayList.add(startButton);
    }
    @Override
    protected void paintComponent(Graphics graphics) {
        update(graphics);
    }

    @Override
    public void update(Graphics graphics) {
        graphics.drawImage(backgroundImage,0,0, getParent());
    }
}
