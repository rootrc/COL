package core.window;

import java.awt.MouseInfo;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import core.game.Game;

public class RoomMouseListener implements MouseListener {
    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println("The Room Is Being Clicked");
        if (Game.DEBUG) {
            System.out.println(e.getY() / 16 + " " + e.getX() / 16);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    public int getX() {
        return (int) Math.round(MouseInfo.getPointerInfo().getLocation().getX());
    }

    public int getY() {
        return (int) Math.round(MouseInfo.getPointerInfo().getLocation().getY());
    }

    public boolean onRectangle(int x1, int y1, int x2, int y2) {
        if (x1 < getX() && getX() < x2 && y1 < getY() && getY() < y2) {
            return true;
        }
        return false;
    }

    public boolean onRectangle(Rectangle rect) {
        return onRectangle((int) rect.getMinX(), (int) rect.getMinY(), (int) rect.getMaxX(), (int) rect.getMaxY());
    }

}