package gui;

import components.GameLogic;
import utils.CommonsUtilities;

import javax.swing.*;
import java.util.ArrayList;

/**
 * Created by Giuseppe on 09/05/2015.
 */
public class MainFrame extends JFrame {

    //Lista di tutti i Pannelli del gioco
    private static ArrayList<JPanel> panelArrayList;

    private GameLogic gameLogic;

    private static MainPanel mainPanel;
    private static DifficultPanel difficultPanel;
    private static GamePanel gamePanel;

    public MainFrame() {
        //Setta il nome del programma
        this.setTitle(CommonsUtilities.GAME_NAME);
        //Setta l'azione per la X (chiudere il programma)
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        //elimina il bordo esterno del frame
        this.setUndecorated(true);
        //Imposta la dimensione del frame
        this.setSize(CommonsUtilities.FRAME_SIZE);
        //La finestra verrà aperta al centro dello schermo
        this.setLocationRelativeTo(null);

        //Inizializzo la lista dei pannelli
        panelArrayList = new ArrayList<>();

        //Inizializzo i vari pannelli
        this.mainPanel = new MainPanel();
        this.difficultPanel = new DifficultPanel();
        this.gamePanel = new GamePanel();

        //Aggiungo al frame i pannelli
        this.add(mainPanel);
        this.add(difficultPanel);
        this.add(gamePanel);

        this.gameLogic = GameLogic.getInstance();
    }

    /**
     * Avvia l'applicazione
     *
     * @param args
     */
    public static void main(String[] args) {
        MainFrame mainFrame = new MainFrame();
        mainFrame.setVisible(true);
        mainPanel.setVisible(true);
    }

    public static void switchPanel(JPanel start, JPanel end) {
        start.setVisible(false);
        end.setVisible(true);
    }

    public static MainPanel getMainPanel() {
        return mainPanel;
    }

    public static DifficultPanel getDifficultPanel() {
        return difficultPanel;
    }

    public static GamePanel getGamePanel() {
        return gamePanel;
    }
}
