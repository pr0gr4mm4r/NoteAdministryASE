package base.ui.model;

import base.interfaces.Highlighter;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VerbDisplayButton extends JButton implements Highlighter, ActionListener {

    public VerbDisplayButton(String buttonText){
        super(buttonText);
    }

    @Override
    public void hightlight() {

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        hightlight();
    }
}
