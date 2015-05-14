package components;

import java.awt.*;

/**
 * Created by Giuseppe on 12/05/2015.
 */
public abstract class RectangleButton extends Rectangle{

    public RectangleButton(int x, int y, int width, int height){
        super(x,y,width,height);
    }

    public void action(){}

    public void outerAction(){}

    public void onMouseEntered(){}

    public void onMouseExited(){}

    public void doInAction(){
        action();
    }

    public void doOutAction(){
        outerAction();
    }

    public void doEnteredAction(){
        onMouseEntered();
    }

    public void doExitedAction(){
        onMouseExited();
    }
}
