package application.notes.ui.listener.mouse;

import application.notes.ui.frame.DisplayNotes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

public class NoteButtonsSelectedMouseListener implements MouseListener {

    private DisplayNotes displayNotes;
    private JButton jButton;

    public NoteButtonsSelectedMouseListener(DisplayNotes displayNotes, JButton jButton) {
        this.jButton = jButton;
        this.displayNotes = displayNotes;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        List<JButton> noteDisplayButtons = displayNotes.getNoteDisplayButtons();
        List<JButton> textManipulationButtons = displayNotes.getTextManipulationButtons();
        Color defaultBackgroundColor = new JButton().getBackground();
        for (JButton button : noteDisplayButtons) {
            button.setBackground(defaultBackgroundColor);
        }
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
