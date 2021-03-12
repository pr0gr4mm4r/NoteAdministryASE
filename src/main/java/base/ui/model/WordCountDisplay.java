package base.ui.model;

import base.notes.wordcount.raw.NoteCounterRaw;
import base.ui.DisplayNotes;

import javax.swing.*;

public class WordCountDisplay extends JButton {
    DisplayNotes displayNotes;

    public WordCountDisplay(String buttonText, DisplayNotes displayNotes) {
        super(buttonText);
        this.displayNotes = displayNotes;
        this.addActionListener(e -> {
            NoteCounterRaw noteCounterRaw = new NoteCounterRaw();
            int wordCount = noteCounterRaw.getWordCount();
            int verbCount = 0;
            JLabel jLabel = new JLabel();
            jLabel.setText("" + wordCount);
            JPanel jPanel = new JPanel();
            jPanel.add(jLabel);
            displayNotes.getManipulatingButtonsContentPanel().add(jPanel);
            displayNotes.invalidate();
            displayNotes.validate();
        });
    }
}
