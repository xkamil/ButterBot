package models;

import java.awt.*;
import java.awt.event.InputEvent;
import java.util.Observable;
import java.util.logging.Logger;

/**
 * Created by kamilwrobel on 10.10.2016.
 */
public class MouseClickAction extends Observable implements Action {
    private static final Logger LOGGER = Logger.getLogger(MouseClickAction.class.getName());

    private int clickInterval;
    private int clickCount;
    private int mouseButton;
    private Point clickPoint;

    public MouseClickAction() {
        initDefault();
    }

    public void initDefault() {
        this.setClickCount(1);
        this.setMouseButton(InputEvent.BUTTON1_DOWN_MASK);
        this.setClickCount(1);
        this.setClickPoint(0, 0);
    }

    public void perform() throws Exception {
        Robot robot = new Robot();

        for (int i = 0; i < clickCount; i++) {
            robot.mouseMove(clickPoint.x, clickPoint.y);
            robot.mousePress(mouseButton);
            robot.mouseRelease(mouseButton);
            LOGGER.info("Mouse button "+ mouseButton +" clicked at: " + clickPoint.x + ", " + clickPoint.y);
            robot.delay(clickInterval);
        }
    }

    public int getClickInterval() {
        return clickInterval;
    }

    public void setClickInterval(int clickInterval) {
        this.clickInterval = clickInterval;
        this.setChanged();
        this.notifyObservers();
    }

    public int getClickCount() {
        return clickCount;
    }

    public void setClickCount(int clickCount) {
        this.clickCount = clickCount;
        this.setChanged();
        this.notifyObservers();
    }

    public int getMouseButton() {
        return mouseButton;
    }

    public void setMouseButton(int mouseButton) {
        this.mouseButton = mouseButton;
        this.setChanged();
        this.notifyObservers();
    }

    public Point getClickPoint() {
        return clickPoint;
    }

    public void setClickPoint(int x, int y) {
        this.clickPoint = new Point(x, y);
        this.setChanged();
        this.notifyObservers();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MouseClickAction that = (MouseClickAction) o;

        if (clickInterval != that.clickInterval) return false;
        if (clickCount != that.clickCount) return false;
        if (mouseButton != that.mouseButton) return false;
        return clickPoint != null ? clickPoint.equals(that.clickPoint) : that.clickPoint == null;

    }

    @Override
    public int hashCode() {
        int result = clickInterval;
        result = 31 * result + clickCount;
        result = 31 * result + mouseButton;
        result = 31 * result + (clickPoint != null ? clickPoint.hashCode() : 0);
        return result;
    }
}
