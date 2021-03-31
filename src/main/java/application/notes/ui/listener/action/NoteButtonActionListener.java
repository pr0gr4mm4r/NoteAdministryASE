package application.notes.ui.listener.action;

import application.notes.processors.single.Note;
import application.notes.ui.display.DisplayNotes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static application.notes.processors.single.Note.*;

public class NoteButtonActionListener implements ActionListener {

    private final DisplayNotes displayNotes;
    private final String noteName;

    public NoteButtonActionListener(final DisplayNotes displayNotes, final String noteName) {
        this.displayNotes = displayNotes;
        this.noteName = noteName;
    }

    @Override
    public void actionPerformed(final ActionEvent e) {
        final Note note = initializeNote(this.noteName);
        final String content = note.getContentForGraphicalProcessing();
        final JEditorPane jEditorPane = displayNotes.getNoteText();
        jEditorPane.setText(content);
        final Font font = new Font("Arial", Font.PLAIN, 16);
        jEditorPane.setFont(font);
        displayNotes.getTextManipulationButtons().forEach(button -> button.setEnabled(true));
    }
}
