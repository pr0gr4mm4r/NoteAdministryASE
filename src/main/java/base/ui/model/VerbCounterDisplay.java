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
            JLabel verbCounterLabel = displayNotes.getVerbCounterLabel();
            verbCounterLabel.setText("" + verbCount);
            JPanel jPanel = new JPanel();
            jPanel.add(verbCounterLabel);
            displayNotes.getManipulatingButtonsContentPanel().removeAll();
            displayNotes.getManipulatingButtonsContentPanel().add(jPanel);
            displayNotes.invalidate();
            displayNotes.validate();
        });
        this.addMouseListener(new CustomMouseLiatener(this));
    }
}
