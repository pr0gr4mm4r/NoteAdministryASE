package application.notes.ui.display;

import application.notes.ui.frame.DisplayNotes;
import application.notes.ui.listener.mouse.ContentButtonsSelectedMouseListener;
import application.notes.ui.listener.mouse.HoverPointerMouseListener;
import application.notes.ui.raw.VerbCounterRaw;

import javax.swing.*;

public class VerbCounterDisplay extends JButton {
    DisplayNotes displayNotes;

    public VerbCounterDisplay(final String buttonText, final DisplayNotes displayNotes) {
        super(buttonText);
        this.displayNotes = displayNotes;
        this.addActionListener(e -> {
            final String text = displayNotes.getNoteText().getText();
            final VerbCounterRaw verbCounterRaw = new VerbCounterRaw(text);
            final int verbCount = verbCounterRaw.countVerbs();
            final JLabel verbCounterLabel = displayNotes.getVerbCounterLabel();
            verbCounterLabel.setText("" + verbCount);
            final JPanel jPanel = new JPanel();
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
