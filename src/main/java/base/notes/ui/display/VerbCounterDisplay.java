package base.notes.ui.display;

import base.notes.ui.frame.DisplayNotes;
import base.notes.ui.listener.mouse.ContentButtonsSelectedMouseListener;
import base.notes.ui.listener.mouse.HoverPointerMouseListener;
import base.notes.ui.raw.VerbCounterRaw;

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
        this.addMouseListener(new HoverPointerMouseListener(this));
        this.addMouseListener(new ContentButtonsSelectedMouseListener(displayNotes,this));
    }
}
