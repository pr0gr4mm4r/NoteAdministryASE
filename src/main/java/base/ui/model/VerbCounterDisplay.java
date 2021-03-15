package base.ui.model;

import base.ui.DisplayNotes;
import base.ui.model.raw.VerbCounterRaw;

import javax.swing.*;

public class VerbCounterDisplay extends JButton {
    DisplayNotes displayNotes;

    public VerbCounterDisplay(String buttonText, DisplayNotes displayNotes) {
        super(buttonText);
        this.displayNotes = displayNotes;
        this.addActionListener(e -> {
            String text = displayNotes.getNoteText().getText();
            VerbCounterRaw verbCounterRaw = new VerbCounterRaw(text);
            int verbCount = verbCounterRaw.countWords();
            JLabel jLabel = new JLabel();
            jLabel.setText("" + verbCount);
            JPanel jPanel = new JPanel();
            jPanel.add(jLabel);
            displayNotes.getManipulatingButtonsContentPanel().add(jPanel);
            displayNotes.invalidate();
            displayNotes.validate();
        });
    }
}
