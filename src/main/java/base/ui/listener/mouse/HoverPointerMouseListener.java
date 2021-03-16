package base.ui.listener.mouse;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class HoverPointerMouseListener implements MouseListener {
    private JButton jButton;

    public HoverPointerMouseListener(JButton jButton) {
        this.jButton = jButton;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        Cursor cursor = new Cursor(Cursor.HAND_CURSOR);
        this.jButton.setCursor(cursor);
    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
