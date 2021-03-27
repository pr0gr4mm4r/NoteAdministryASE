package application.notes.ui.buttons;

import application.notes.ui.display.DisplayNotes;
import application.notes.ui.listener.mouse.ContentButtonsSelectedMouseListener;
import application.notes.ui.listener.mouse.HoverPointerMouseListener;
import application.notes.ui.raw.RhymeCounterRaw;

import javax.swing.*;

public class RhymeCounterDisplayButton extends JButton {
    DisplayNotes displayNotes;
    public RhymeCounterDisplayButton(final String buttonText, final DisplayNotes displayNotes){
        super(buttonText);
        this.displayNotes = displayNotes;
        this.addActionListener(e -> {
            final String text = displayNotes.getNoteText().getText();
            final RhymeCounterRaw rhymeCounterRaw = new RhymeCounterRaw(text);
            final int rhymeCount = rhymeCounterRaw.calcRhymes();
            final JLabel rhymeCounterLabel = displayNotes.getRhymeCounterLabel();
            rhymeCounterLabel.setText("" + rhymeCount);
            final JPanel jPanel = new JPanel();
            jPanel.add(rhymeCounterLabel);
            displayNotes.getManipulatingButtonsContentPanel().removeAll();
            displayNotes.getManipulatingButtonsContentPanel().add(jPanel);
            displayNotes.invalidate();
            displayNotes.validate();
        });
        this.addMouseListener(new HoverPointerMouseListener(this));
        this.addMouseListener(new ContentButtonsSelectedMouseListener(displayNotes,this));
    }
}
