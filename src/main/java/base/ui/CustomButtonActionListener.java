package base.ui;

import base.notes.processors.SingleNoteProcessor;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CustomButtonActionListener implements ActionListener {

    private final DisplayNotes displayNotes;
    private final String noteName;

    public CustomButtonActionListener(DisplayNotes displayNotes, String noteName){
        this.displayNotes = displayNotes;
        this.noteName = noteName;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        SingleNoteProcessor singleNoteProcessor = new SingleNoteProcessor(this.noteName);
        String[] wordList = singleNoteProcessor.getWordList();
        JOptionPane jOptionPane = new JOptionPane();
        JOptionPane.showMessageDialog(displayNotes, wordList[0], "title", JOptionPane.INFORMATION_MESSAGE);
    }

    private void getData(String noteName) {

    }
}
