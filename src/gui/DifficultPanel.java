package gui;

import components.GameLogic;
import utils.CommonsUtilities;
import components.CoordinateListener;
import components.RectangleButton;
import components.RectangleListener;
import utils.Resources;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Giuseppe on 10/05/2015.
 */
public class DifficultPanel extends JPanel {

    private Image backgroundImage;
    private Image aldoImage;
    private Image puppyImage;
    private Image normalImage;
    private Image olivImage;

    //Buttons list
    private RectangleButton exitButton;
    private RectangleButton aldoRectButton;
    private RectangleButton puppyRectButton;
    private RectangleButton normalRectButton;
    private RectangleButton olivRectButton;

    private ArrayList<RectangleButton> buttonList = new ArrayList<>();

    public DifficultPanel() {
        this.setSize(CommonsUtilities.FRAME_SIZE);
        this.setVisible(false);

        this.backgroundImage = Resources.getImage("/img/difficult-panel-bg.jpg");

        //Inizializzo le immagini
        this.aldoImage = Resources.getImage("/img/aldo_off.png");
        this.puppyImage = Resources.getImage("/img/puppy_off.png");
        this.normalImage = Resources.getImage("/img/normal_off.png");
        this.olivImage = Resources.getImage("/img/oliv_off.png");

        //Inizializzo i pulsanti
        initRectangleButton();

        //Aggiungo i listener
        RectangleListener rectangleListener = new RectangleListener(buttonList, this);
        this.addMouseListener(rectangleListener);
        this.addMouseMotionListener(rectangleListener);
    }

    private void initRectangleButton() {
        this.exitButton = CommonsUtilities.exitButton;

        this.aldoRectButton = new RectangleButton(278, 119, aldoImage.getWidth(null), aldoImage.getHeight(null)) {
            @Override
            public void onMouseEntered() {
                aldoImage = Resources.getImage("/img/aldo_on.png");
                repaint();
            }

            @Override
            public void onMouseExited() {
                aldoImage = Resources.getImage("/img/aldo_off.png");
                repaint();
            }

            @Override
            public void action() {
                GameLogic gameLogic = GameLogic.getInstance();
                gameLogic.setBaseHealtPoint(10);
                gameLogic.setSpeed(1);
                gameLogic.setDifficult(GameLogic.DIFFICULT_ALDO);
                MainFrame.switchPanel(MainFrame.getDifficultPanel(), MainFrame.getGamePanel());
                MainFrame.getGamePanel().getTargetThread().start();
            }
        };

        this.puppyRectButton = new RectangleButton(278, 164, aldoImage.getWidth(null), aldoImage.getHeight(null)) {
            @Override
            public void onMouseEntered() {
                puppyImage = Resources.getImage("/img/puppy_on.png");
                repaint();
            }

            @Override
            public void onMouseExited() {
                puppyImage = Resources.getImage("/img/puppy_off.png");
                repaint();
            }

            @Override
            public void action() {
                GameLogic gameLogic = GameLogic.getInstance();
                gameLogic.setBaseHealtPoint(5);
                gameLogic.setDifficult(GameLogic.DIFFICULT_EASY);
                gameLogic.setSpeed(4);
                MainFrame.switchPanel(MainFrame.getDifficultPanel(), MainFrame.getGamePanel());
                MainFrame.getGamePanel().getTargetThread().start();
            }
        };

        this.normalRectButton = new RectangleButton(278, 210, aldoImage.getWidth(null), aldoImage.getHeight(null)) {
            @Override
            public void onMouseEntered() {
                normalImage = Resources.getImage("/img/normal_on.png");
                repaint();
            }

            @Override
            public void onMouseExited() {
                normalImage = Resources.getImage("/img/normal_off.png");
                repaint();
            }

            @Override
            public void action() {
                GameLogic gameLogic = GameLogic.getInstance();
                gameLogic.setBaseHealtPoint(4);
                gameLogic.setSpeed(8);
                gameLogic.setDifficult(GameLogic.DIFFICULT_NORMAL);
                MainFrame.switchPanel(MainFrame.getDifficultPanel(), MainFrame.getGamePanel());
                MainFrame.getGamePanel().getTargetThread().start();
            }
        };

        this.olivRectButton = new RectangleButton(278, 257, aldoImage.getWidth(null), aldoImage.getHeight(null)) {
            @Override
            public void onMouseEntered() {
                olivImage = Resources.getImage("/img/oliv_on.png");
                repaint();
            }

            @Override
            public void onMouseExited() {
                olivImage = Resources.getImage("/img/oliv_off.png");
                repaint();
            }

            @Override
            public void action() {
                GameLogic gameLogic = GameLogic.getInstance();
                gameLogic.setBaseHealtPoint(1);
                gameLogic.setBaseSpeed(20);
                gameLogic.setDifficult(GameLogic.DIFFICULT_OLIV);
                MainFrame.switchPanel(MainFrame.getDifficultPanel(), MainFrame.getGamePanel());
                MainFrame.getGamePanel().getTargetThread().start();
            }
        };

        if (buttonList != null) {
            buttonList.add(exitButton);
            buttonList.add(aldoRectButton);
            buttonList.add(puppyRectButton);
            buttonList.add(normalRectButton);
            buttonList.add(olivRectButton);
        }
    }

    @Override
    public void update(Graphics graphics) {
        graphics.drawImage(backgroundImage, 0, 0, getParent());
        graphics.drawImage(aldoImage, aldoRectButton.x, aldoRectButton.y, getParent());
        graphics.drawImage(puppyImage, puppyRectButton.x, puppyRectButton.y, getParent());
        graphics.drawImage(normalImage, normalRectButton.x, normalRectButton.y, getParent());
        graphics.drawImage(olivImage, olivRectButton.x, olivRectButton.y, getParent());

    }

    @Override
    protected void paintComponent(Graphics graphics) {
        update(graphics);
    }
}
