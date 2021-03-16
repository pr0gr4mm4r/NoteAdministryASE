package base.ui.listener.action;

import base.notes.processors.SingleNoteProcessor;
import base.ui.frame.DisplayNotes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NoteButtonActionListener implements ActionListener {

    private final DisplayNotes displayNotes;
    private final String noteName;

    public NoteButtonActionListener(DisplayNotes displayNotes, String noteName) {
        this.displayNotes = displayNotes;
        this.noteName = noteName;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        SingleNoteProcessor singleNoteProcessor = new SingleNoteProcessor(this.noteName);
        String note = singleNoteProcessor.getNoteForGraphicalProcessing();
        JEditorPane jEditorPane = displayNotes.getNoteText();
        jEditorPane.setText(note);
        Font font = new Font("Arial", Font.PLAIN, 16);
        jEditorPane.setFont(font);
        displayNotes.getTextManipulationButtons().forEach(button -> button.setEnabled(true));
    }
}
