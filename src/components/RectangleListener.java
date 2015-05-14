package components;

import utils.CommonsUtilities;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

/**
 * Created by Giuseppe on 12/05/2015.
 */
public class RectangleListener extends MouseAdapter {

    private ArrayList<RectangleButton> buttonList;
    private JPanel panel;
    private boolean containMouse;
    private RectangleButton magicRectangle;

    public RectangleListener(ArrayList<RectangleButton> pList) {
        this.buttonList = pList;
        this.containMouse = false;
        this.magicRectangle = null;
    }

    public RectangleListener(ArrayList<RectangleButton> pList, JPanel pPanel) {
        this.buttonList = pList;
        this.panel = pPanel;
        this.containMouse = false;
        this.magicRectangle = null;
    }

    public RectangleListener(RectangleButton rectangleButton, JPanel pPanel) {
        this.buttonList = new ArrayList<>();
        this.panel = pPanel;
        this.buttonList.add(rectangleButton);
        this.containMouse = false;
        this.magicRectangle = null;
    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {
        Point p = mouseEvent.getPoint();
        for (RectangleButton rectangleButton : buttonList) {
            if (rectangleButton.contains(p)) {
                rectangleButton.doInAction();
            } else {
                rectangleButton.doOutAction();
            }
        }
    }

    @Override
    public void mouseMoved(MouseEvent mouseEvent) {
        Point p = mouseEvent.getPoint();
        if (panel != null) {
            if (!this.containMouse) {
                for (RectangleButton rectangleButton : buttonList) {
                    if (rectangleButton.contains(p)) {
                        panel.setCursor(CommonsUtilities.getCursor(CommonsUtilities.HAND_CURSOR));
                        this.containMouse = true;
                        this.magicRectangle = rectangleButton;
                        rectangleButton.doEnteredAction();
                        break;
                    }
                }
            } else {
                if (!magicRectangle.contains(p)) {
                    this.containMouse = false;
                    this.magicRectangle.doExitedAction();
                    this.magicRectangle = null;
                    this.panel.setCursor(CommonsUtilities.getCursor(CommonsUtilities.STANDARD_CURSOR));
                }
            }
        }
    }
}
