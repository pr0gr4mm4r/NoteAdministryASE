package base.ui;

import base.notes.processors.MultiNoteProcessor;
import base.ui.model.RhymeDisplayButton;
import base.ui.model.VerbDisplayButton;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static base.config.Globals.path_for_notes;

public class DisplayNotes extends JFrame {
    private List<JLabel> noteNameLabels = new ArrayList<>();
    private List<JButton> noteDisplayButtons = new ArrayList<>();
    private List<JButton> textManipulationButtons = new ArrayList<>();
    private GridLayout masterGrid = new GridLayout(3, 1);
    private final GridLayout textContentGrid = new GridLayout(1, 2);
    private GridLayout manipulatingButtonsGrid;
    private JPanel masterPanel = new JPanel();
    private JPanel capturePanel = new JPanel();
    private JPanel noteButtonPanel = new JPanel();
    private JPanel noteTextPanel = new JPanel();
    private JPanel contentPanel = new JPanel();
    private JTextPane noteText = new JTextPane();
    private JScrollPane jScrollPane;

    public DisplayNotes() {
        this.setTitle("Display of Notes");
        masterPanel.setLayout(masterGrid);
        JLabel capture = new JLabel("Overview of all notes in directory " + path_for_notes);
        Font font = new Font("Arial", Font.BOLD, 18);
        capture.setFont(font);
        capturePanel.add(capture);
        noteText.setEditable(false);
        noteTextPanel.add(noteText);
        jScrollPane = new JScrollPane(noteTextPanel);
        contentPanel.setLayout(textContentGrid);
        contentPanel.add(jScrollPane);
        MultiNoteProcessor multiNoteProcessor = new MultiNoteProcessor();
        List<String> noteNames = new ArrayList<>(multiNoteProcessor.getNoteNames());
        for (int i = 0; i < noteNames.size(); i++) {
            JLabel jLabel = new JLabel(noteNames.get(i));
            noteNameLabels.add(jLabel);
            JButton jButton = new JButton(noteNames.get(i));
            NoteButtonActionListener actionListener = new NoteButtonActionListener(this, jButton.getText());
            jButton.addActionListener(actionListener);
            noteDisplayButtons.add(jButton);
            noteButtonPanel.add(noteDisplayButtons.get(i));
        }
        textManipulationButtons.add(new VerbDisplayButton("Verbs"));
        textManipulationButtons.add(new RhymeDisplayButton("Rhymes"));
        textManipulationButtons.add(new JButton("wordCount"));
        manipulatingButtonsGrid = new GridLayout(textManipulationButtons.size(), 1);
        JPanel manipulatingButtonsPanel = new JPanel();
        for(int i = 0; i < textManipulationButtons.size(); i++){
            textManipulationButtons.get(i).setEnabled(false);
            manipulatingButtonsPanel.add(textManipulationButtons.get(i));
        }
        contentPanel.add(manipulatingButtonsPanel);
        masterPanel.add(capturePanel);
        masterPanel.add(noteButtonPanel);
        masterPanel.add(contentPanel);
        this.add(masterPanel);
        this.setSize(800, 500);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    public List<JButton> getNoteDisplayButtons() {
        return noteDisplayButtons;
    }

    public List<JLabel> getNoteNameLabels() {
        return noteNameLabels;
    }

    public void setNoteDisplayButtons(List<JButton> noteDisplayButtons) {
        this.noteDisplayButtons = noteDisplayButtons;
    }

    public void setNoteNameLabels(List<JLabel> noteNameLabels) {
        this.noteNameLabels = noteNameLabels;
    }

    public GridLayout getMasterGrid() {
        return masterGrid;
    }

    public void setMasterGrid(GridLayout masterGrid) {
        this.masterGrid = masterGrid;
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

    public JTextPane getNoteText() {
        return noteText;
    }

    public void setNoteText(JTextPane noteText) {
        this.noteText = noteText;
    }

    public JScrollPane getjScrollPane() {
        return jScrollPane;
    }

    public void setjScrollPane(JScrollPane jScrollPane) {
        this.jScrollPane = jScrollPane;
    }

    public List<JButton> getTextManipulationButtons() {
        return textManipulationButtons;
    }

    public void setTextManipulationButtons(List<JButton> textManipulationButtons) {
        this.textManipulationButtons = textManipulationButtons;
    }
}
