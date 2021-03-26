package application.notes.ui.listener.mouse;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class HoverPointerMouseListener implements MouseListener {
    private JButton jButton;

    public HoverPointerMouseListener(final JButton jButton) {
        this.jButton = jButton;
    }

    @Override
    public void mouseClicked(final MouseEvent e) {

    }

    @Override
    public void mousePressed(final MouseEvent e) {

    }

    @Override
    public void mouseReleased(final MouseEvent e) {

    }

    @Override
    public void mouseEntered(final MouseEvent e) {
        final Cursor cursor = new Cursor(Cursor.HAND_CURSOR);
        this.jButton.setCursor(cursor);
    }

    @Override
    public void mouseExited(final MouseEvent e) {

    }
}
