package base.ui.listener.mouse;

import base.ui.frame.DisplayNotes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

public class ContentButtonsSelectedMouseListener implements MouseListener {
    private DisplayNotes displayNotes;
    private JButton jButton;

    public ContentButtonsSelectedMouseListener(DisplayNotes displayNotes, JButton jButton) {
        this.displayNotes = displayNotes;
        this.jButton = jButton;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        List<JButton> textManipulationButtons = displayNotes.getTextManipulationButtons();
        Color defaultBackgroundColor = new JButton().getBackground();
        for (JButton button : textManipulationButtons) {
            button.setBackground(defaultBackgroundColor);
        }
        Color color = Color.orange;
        if(jButton.getBackground() == color){
            jButton.setBackground(defaultBackgroundColor);
            return;
        }
        jButton.setBackground(color);
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
