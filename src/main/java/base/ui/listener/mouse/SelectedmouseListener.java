package base.ui.listener.mouse;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class SelectedmouseListener implements MouseListener {

    private JButton jButton;

    public SelectedmouseListener(JButton jButton) {
        this.jButton = jButton;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
jButton.setForeground(Color.yellow);
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
}
