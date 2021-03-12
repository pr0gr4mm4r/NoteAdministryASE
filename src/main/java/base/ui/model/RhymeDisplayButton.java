package base.ui.model;

import base.ui.DisplayNotes;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RhymeDisplayButton extends JButton implements ActionListener {
    public RhymeDisplayButton(String buttonText, DisplayNotes displayNotes){
        super(buttonText);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
    }
}
