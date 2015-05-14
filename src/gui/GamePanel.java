package gui;

import components.GameLogic;
import components.RectangleButton;
import components.RectangleListener;
import utils.CommonsUtilities;
import utils.Resources;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Giuseppe on 14/05/2015.
 */
public class GamePanel extends JPanel {

    public Image background;
    public Image targetImage;
    public Image lifeImage;

    public RectangleButton targetRectangle;
    public GameLogic gameLogic;

    private ArrayList<String> imageNameList = new ArrayList<>();

    private TargetThread targetThread;

    public GamePanel() {
        this.setSize(CommonsUtilities.FRAME_SIZE);
        this.setVisible(false);

        this.gameLogic = GameLogic.getInstance();
        this.background = Resources.getImage("/img/background_game.jpg");
        this.imageNameList.add("/img/linux.png");
        this.imageNameList.add("/img/mario.png");
        this.imageNameList.add("/img/monster.png");
        this.imageNameList.add("/img/yoshy.png");

        this.lifeImage = Resources.getImage("/img/life.png");

        this.targetImage = randomImage();
        this.targetRectangle = new RectangleButton(0 - targetImage.getWidth(null), randomY(), targetImage.getWidth(null), targetImage.getHeight(null)) {
            @Override
            public void action() {
                System.out.println("Colpito");
                gameLogic.setScore(gameLogic.getScore() + 1);
                setRectAndImage();
                if(gameLogic.getScore() % 3 == 0){
                    gameLogic.setSpeed(gameLogic.getSpeed() + gameLogic.getDifficult());
                }
                repaint();
            }

            @Override
            public void outerAction() {
                gameLogic.setHealtPoint(gameLogic.getHealtPoint() - 1);
                targetThread.stop();
                repaint();
                if(gameLogic.getHealtPoint() <= 0){
                    String[] buttons = {"Riprova", "Scegli difficoltà", "Esci"};
                    int n = JOptionPane.showOptionDialog(MainFrame.getGamePanel(),
                            "Hai perso! Ma anche ai perdenti è permesso fare una scelta...",
                            "You Suck",
                            JOptionPane.YES_NO_CANCEL_OPTION,
                            JOptionPane.QUESTION_MESSAGE,
                            null,
                            buttons,
                            buttons[2]);
                    switch (n){
                        case 0:
                            gameLogic.resetAll();
                            targetThread.start();
                            break;
                        case 1:
                            gameLogic.resetAll();
                            MainFrame.switchPanel(MainFrame.getGamePanel(), MainFrame.getDifficultPanel());
                            break;
                        case 2:
                            System.exit(-1);
                    }
                }
            }
        };

        targetThread = new TargetThread();
        RectangleListener rectangleListener = new RectangleListener(targetRectangle, this);
        this.addMouseListener(rectangleListener);
        this.addMouseMotionListener(rectangleListener);
    }

    private Image randomImage() {
        Random r = new Random();
        int i = r.nextInt(imageNameList.size());
        return Resources.getImage(imageNameList.get(i));
    }

    private int randomY() {
        Random r = new Random();
        int i = r.nextInt(360-targetImage.getHeight(null));
        return i;
    }

    private int randomX(){
        Random r = new Random();
        int i = r.nextInt(700-targetImage.getWidth(null));
        return i;
    }

    private void setRectAndImage() {
        targetImage = randomImage();
        targetRectangle.setSize(targetImage.getWidth(null), targetImage.getHeight(null));
        targetRectangle.x = randomX();
        targetRectangle.y = randomY();
    }

    public TargetThread getTargetThread(){
        if(targetThread == null){
            targetThread = new TargetThread();
        }
        return targetThread;
    }
    @Override
    public void update(Graphics graphics) {
        graphics.drawImage(targetImage, targetRectangle.x, targetRectangle.y, getParent());
        graphics.drawString("Punteggio: "+gameLogic.getScore(), 20, 20);
        graphics.drawString("Velocita: "+gameLogic.getSpeed(), 20, 40);
        int lastLifePosition = 700 - lifeImage.getWidth(null) - 10;
        for(int i=0; i<gameLogic.getHealtPoint(); i++){
            graphics.drawImage(lifeImage, lastLifePosition, 20, getParent());
            lastLifePosition -= lifeImage.getWidth(null)+4;
        }
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        graphics.drawImage(background, 0, 0, getParent());
        graphics.setFont(new Font("OpenSans", Font.PLAIN, 17));
        graphics.setColor(Color.WHITE);
        update(graphics);
    }

    public class TargetThread implements Runnable {
        Thread runner;

        @Override
        public void run() {
            while (true){
                if(targetRectangle.x > 700+targetImage.getWidth(null)){
                    targetRectangle.x = 0-targetImage.getWidth(null);
                    targetRectangle.y = randomY();
                } else {
                    targetRectangle.x += gameLogic.getSpeed();
                }
                repaint();
                try {
                    Thread.sleep(30);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        public void start(){
            if(runner == null){
                runner = new Thread(this);
            }
            runner.start();
        }

        public void stop(){
            if(runner != null){
                runner = null;
            }
        }
    }

}
