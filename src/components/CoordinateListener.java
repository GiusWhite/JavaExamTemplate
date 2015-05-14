package components;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by Giuseppe on 13/05/2015.
 */
public class CoordinateListener extends MouseAdapter {

    public CoordinateListener(){}

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {
        Point p = mouseEvent.getPoint();
        System.out.println(p);
    }
}
