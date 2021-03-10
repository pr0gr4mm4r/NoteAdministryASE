package base.ui;

import base.notes.processors.MultiNoteProcessor;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static base.config.Globals.path_for_notes;

public class DisplayNotes extends JFrame {
    private List<JLabel> noteNameLabels = new ArrayList<>();
    public DisplayNotes() {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        JLabel label = new JLabel("Overview of all notes in directory" + path_for_notes);
        panel.add(label);
        MultiNoteProcessor multiNoteProcessor = new MultiNoteProcessor();
        List<String> noteNames = new ArrayList<>(multiNoteProcessor.getNoteNames());
        for(int i = 0; i < noteNames.size(); i++){
            JLabel jLabel = new JLabel(noteNames.get(i));
            noteNameLabels.add(jLabel);
            panel.add(noteNameLabels.get(i));
        }
        this.add(panel);
        this.setSize(800, 500);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}
