package base.ui.display;

import base.ui.frame.DisplayNotes;
import base.ui.listener.mouse.ContentButtonsSelectedMouseListener;
import base.ui.listener.mouse.HoverPointerMouseListener;
import base.ui.listener.mouse.NoteButtonsSelectedMouseListener;
import base.ui.raw.RhymeCounterRaw;

import javax.swing.*;

public class RhymeCounterDisplay extends JButton {
    DisplayNotes displayNotes;
    public RhymeCounterDisplay(String buttonText, DisplayNotes displayNotes){
        super(buttonText);
        this.displayNotes = displayNotes;
        this.addActionListener(e -> {
            String text = displayNotes.getNoteText().getText();
            RhymeCounterRaw rhymeCounterRaw = new RhymeCounterRaw(text);
            int rhymeCount = rhymeCounterRaw.calcRhymes();
            JLabel rhymeCounterLabel = displayNotes.getRhymeCounterLabel();
            rhymeCounterLabel.setText("" + rhymeCount);
            JPanel jPanel = new JPanel();
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
