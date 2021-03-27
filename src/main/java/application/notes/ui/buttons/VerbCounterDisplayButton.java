package application.notes.ui.buttons;

import application.notes.ui.display.DisplayNotes;
import application.notes.ui.listener.mouse.ContentButtonsSelectedMouseListener;
import application.notes.ui.listener.mouse.HoverPointerMouseListener;
import application.notes.ui.raw.VerbCounterRaw;

import javax.swing.*;

public class VerbCounterDisplayButton extends JButton {
    DisplayNotes displayNotes;

    public VerbCounterDisplayButton(final String buttonText, final DisplayNotes displayNotes) {
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
