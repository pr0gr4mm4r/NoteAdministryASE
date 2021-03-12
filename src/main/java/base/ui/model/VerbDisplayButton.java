package base.ui.model;

import base.ui.DisplayNotes;

import javax.swing.*;

public class VerbDisplayButton extends JButton {
    DisplayNotes displayNotes;

    public VerbDisplayButton(String buttonText, DisplayNotes displayNotes) {
        super(buttonText);
        this.displayNotes = displayNotes;
        this.addActionListener(e -> {
            System.out.println("test");
            int verbCount = 0;
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
