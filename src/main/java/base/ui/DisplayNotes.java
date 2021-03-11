package base.ui;

import base.notes.processors.MultiNoteProcessor;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static base.config.Globals.path_for_notes;

public class DisplayNotes extends JFrame {
    private List<JLabel> noteNameLabels = new ArrayList<>();
    private List<JButton> jButtonList = new ArrayList<>();
    private GridLayout gridLayout = new GridLayout(5, 1);
    private JPanel masterPanel = new JPanel();
    private JPanel capturePanel = new JPanel();
    public DisplayNotes() {
        masterPanel.setLayout(gridLayout);
        JPanel notePanel = new JPanel();
        capturePanel.setLayout(new FlowLayout());
        JLabel label = new JLabel("Overview of all notes in directory" + path_for_notes);
        capturePanel.add(label);
        MultiNoteProcessor multiNoteProcessor = new MultiNoteProcessor();
        List<String> noteNames = new ArrayList<>(multiNoteProcessor.getNoteNames());;
        for(int i = 0; i < noteNames.size(); i++){
            JLabel jLabel = new JLabel(noteNames.get(i));
            noteNameLabels.add(jLabel);
            JButton jButton = new JButton(noteNames.get(i));
            CustomButtonActionListener actionListener = new CustomButtonActionListener(this, jButton.getText());
            jButton.addActionListener(actionListener);
            jButtonList.add(jButton);
            notePanel.add(jButtonList.get(i));

        }
        masterPanel.add(capturePanel);
        masterPanel.add(notePanel);
        this.add(masterPanel);
        this.setSize(800, 500);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    public List<JButton> getjButtonList() {
        return jButtonList;
    }

    public List<JLabel> getNoteNameLabels() {
        return noteNameLabels;
    }

    public void setjButtonList(List<JButton> jButtonList) {
        this.jButtonList = jButtonList;
    }

    public void setNoteNameLabels(List<JLabel> noteNameLabels) {
        this.noteNameLabels = noteNameLabels;
    }

    public GridLayout getGridLayout() {
        return gridLayout;
    }

    public void setGridLayout(GridLayout gridLayout) {
        this.gridLayout = gridLayout;
    }

    public JPanel getCapturePanel() {
        return capturePanel;
    }

    public void setCapturePanel(JPanel capturePanel) {
        this.capturePanel = capturePanel;
    }

    public JPanel getMasterPanel() {
        return masterPanel;
    }

    public void setMasterPanel(JPanel masterPanel) {
        this.masterPanel = masterPanel;
    }
}
