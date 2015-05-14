package utils;

import components.RectangleButton;

import java.awt.*;

/**
 * Questa classe conterrà tutte le variabili ed i metodi comuni tra molti elementi
 * Created by Giuseppe on 13/05/2015.
 */
public class CommonsUtilities {

    //Variabili statiche per definire la grandezza della finestra
    public static final int WIDTH = 700;
    public static final int HEIGHT = 360;
    public static final Dimension FRAME_SIZE = new Dimension(WIDTH,HEIGHT);
    //Variabile statica per il titolo del programma
    public static final String GAME_NAME = "Gioco Figo";

    //Variabili statiche per i cursori
    private static Cursor defaultCursor;
    private static Cursor handCursor;
    public static final int STANDARD_CURSOR = 0;
    public static final int HAND_CURSOR = 1;

    /**
     * Metodo statico che restituisce un cursore in base all'intero passato come parametro
     * @param pCursor int per farsi restituire un cursore tra quello standard e quello a manina
     * @return restituisce il cursore desiderato
     */
    public static Cursor getCursor(int pCursor){
        if(pCursor == STANDARD_CURSOR){
            if (defaultCursor == null){
                defaultCursor = new Cursor(Cursor.DEFAULT_CURSOR);
                return defaultCursor;
            } else {
                return defaultCursor;
            }
        } else if (pCursor == HAND_CURSOR){
            if (handCursor == null){
                handCursor = new Cursor(Cursor.HAND_CURSOR);
                return handCursor;
            } else {
                return handCursor;
            }
        } else
            return new Cursor(Cursor.DEFAULT_CURSOR);
    }

    public static RectangleButton exitButton = new RectangleButton(652, 11, (46-11), (686-652)) {
        @Override
        public void action() {
            System.exit(-1);
        }
    };
}
