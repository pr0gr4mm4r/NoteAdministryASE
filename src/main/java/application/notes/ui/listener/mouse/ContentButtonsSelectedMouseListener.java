package application.notes.ui.listener.mouse;

import application.notes.ui.frame.DisplayNotes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

public class ContentButtonsSelectedMouseListener implements MouseListener {
    private DisplayNotes displayNotes;
    private JButton jButton;

    public ContentButtonsSelectedMouseListener(final DisplayNotes displayNotes, final JButton jButton) {
        this.displayNotes = displayNotes;
        this.jButton = jButton;
    }

    @Override
    public void mouseClicked(final MouseEvent e) {
        final List<JButton> textManipulationButtons = displayNotes.getTextManipulationButtons();
        final Color defaultBackgroundColor = new JButton().getBackground();
        for (final JButton button : textManipulationButtons) {
            button.setBackground(defaultBackgroundColor);
        }
        final Color color = Color.orange;
        if(jButton.getBackground() == color){
            jButton.setBackground(defaultBackgroundColor);
            return;
        }
        jButton.setBackground(color);
    }

    @Override
    public void mousePressed(final MouseEvent e) {

    }

    @Override
    public void mouseReleased(final MouseEvent e) {

    }

    @Override
    public void mouseEntered(final MouseEvent e) {

    }

    @Override
    public void mouseExited(final MouseEvent e) {

    }
}
