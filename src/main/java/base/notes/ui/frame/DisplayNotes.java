package base.notes.ui.frame;

import base.notes.processors.multi.MultiNoteProcessor;
import base.notes.ui.listener.action.NoteButtonActionListener;
import base.notes.ui.listener.mouse.HoverPointerMouseListener;
import base.notes.ui.display.RhymeCounterDisplay;
import base.notes.ui.display.VerbCounterDisplay;
import base.notes.ui.listener.mouse.NoteButtonsSelectedMouseListener;

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

    private JLabel verbCounterLabel = new JLabel();
    private JLabel rhymeCounterLabel = new JLabel();
    private JPanel masterPanel = new JPanel();
    private JPanel capturePanel = new JPanel();
    private JPanel noteButtonPanel = new JPanel();
    private JPanel noteTextPanel = new JPanel();
    private JPanel contentPanel = new JPanel();
    private JPanel manipulatingButtonsPanel = new JPanel();
    private JPanel manipulatingButtonsButtonsPanel = new JPanel();
    private JPanel manipulatingButtonsContentPanel = new JPanel();
    private JTextPane noteText = new JTextPane();
    private JScrollPane jScrollPane;

    public DisplayNotes() {
        this.setTitle("Overview of Notes");
        masterPanel.setLayout(masterGrid);
        JLabel capture = new JLabel("Overview of all Notes in Directory " + path_for_notes);
        Font font = new Font("Arial", Font.BOLD, 18);
        capture.setFont(font);
        capturePanel.add(capture);
        noteText.setEditable(false);
        noteTextPanel.add(noteText);
        jScrollPane = new JScrollPane(noteTextPanel);
        contentPanel.setLayout(textContentGrid);
        contentPanel.add(jScrollPane);
        MultiNoteProcessor multiNoteProcessor = new MultiNoteProcessor(path_for_notes);
        List<String> noteNames = new ArrayList<>(multiNoteProcessor.getNoteNames());
        for (int i = 0; i < noteNames.size(); i++) {
            JLabel jLabel = new JLabel(noteNames.get(i));
            noteNameLabels.add(jLabel);
            JButton jButton = new JButton(noteNames.get(i));
            jButton.addMouseListener(new HoverPointerMouseListener(jButton));
            jButton.addMouseListener(new NoteButtonsSelectedMouseListener(this, jButton));
            jButton.addActionListener(e -> {
                this.getManipulatingButtonsContentPanel().removeAll();
                this.verbCounterLabel = new JLabel(" "); //reset counter
                JPanel jPanel = new JPanel();
                jPanel.add(verbCounterLabel);
                this.getManipulatingButtonsContentPanel().add(jPanel);
                this.invalidate();
                this.validate();
            });
            NoteButtonActionListener actionListener = new NoteButtonActionListener(this, jButton.getText());
            jButton.addActionListener(actionListener);
            noteDisplayButtons.add(jButton);
            noteButtonPanel.add(noteDisplayButtons.get(i));
        }
        textManipulationButtons.add(new VerbCounterDisplay("Verbs", this));
        textManipulationButtons.add(new RhymeCounterDisplay("Rhymes", this));
        manipulatingButtonsGrid = new GridLayout(textManipulationButtons.size(), 1);
        manipulatingButtonsPanel.setLayout(new GridLayout(2, 1));
        for (JButton textManipulationButton : textManipulationButtons) {
            textManipulationButton.setEnabled(false);
            manipulatingButtonsButtonsPanel.add(textManipulationButton);
        }
        manipulatingButtonsPanel.add(manipulatingButtonsButtonsPanel);
        manipulatingButtonsPanel.add(manipulatingButtonsContentPanel);
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

    public List<JLabel> getNoteNameLabels() {
        return noteNameLabels;
    }

    public void setNoteNameLabels(List<JLabel> noteNameLabels) {
        this.noteNameLabels = noteNameLabels;
    }

    public List<JButton> getNoteDisplayButtons() {
        return noteDisplayButtons;
    }

    public void setNoteDisplayButtons(List<JButton> noteDisplayButtons) {
        this.noteDisplayButtons = noteDisplayButtons;
    }

    public List<JButton> getTextManipulationButtons() {
        return textManipulationButtons;
    }

    public void setTextManipulationButtons(List<JButton> textManipulationButtons) {
        this.textManipulationButtons = textManipulationButtons;
    }

    public GridLayout getMasterGrid() {
        return masterGrid;
    }

    public void setMasterGrid(GridLayout masterGrid) {
        this.masterGrid = masterGrid;
    }

    public GridLayout getTextContentGrid() {
        return textContentGrid;
    }

    public GridLayout getManipulatingButtonsGrid() {
        return manipulatingButtonsGrid;
    }

    public void setManipulatingButtonsGrid(GridLayout manipulatingButtonsGrid) {
        this.manipulatingButtonsGrid = manipulatingButtonsGrid;
    }

    public JPanel getMasterPanel() {
        return masterPanel;
    }

    public void setMasterPanel(JPanel masterPanel) {
        this.masterPanel = masterPanel;
    }

    public JPanel getCapturePanel() {
        return capturePanel;
    }

    public void setCapturePanel(JPanel capturePanel) {
        this.capturePanel = capturePanel;
    }

    public JPanel getNoteButtonPanel() {
        return noteButtonPanel;
    }

    public void setNoteButtonPanel(JPanel noteButtonPanel) {
        this.noteButtonPanel = noteButtonPanel;
    }

    public JPanel getNoteTextPanel() {
        return noteTextPanel;
    }

    public void setNoteTextPanel(JPanel noteTextPanel) {
        this.noteTextPanel = noteTextPanel;
    }

    public JPanel getContentPanel() {
        return contentPanel;
    }

    public void setContentPanel(JPanel contentPanel) {
        this.contentPanel = contentPanel;
    }

    public JPanel getManipulatingButtonsPanel() {
        return manipulatingButtonsPanel;
    }

    public void setManipulatingButtonsPanel(JPanel manipulatingButtonsPanel) {
        this.manipulatingButtonsPanel = manipulatingButtonsPanel;
    }

    public JPanel getManipulatingButtonsButtonsPanel() {
        return manipulatingButtonsButtonsPanel;
    }

    public void setManipulatingButtonsButtonsPanel(JPanel manipulatingButtonsButtonsPanel) {
        this.manipulatingButtonsButtonsPanel = manipulatingButtonsButtonsPanel;
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

    public JPanel getManipulatingButtonsContentPanel() {
        return manipulatingButtonsContentPanel;
    }

    public void setManipulatingButtonsContentPanel(JPanel manipulatingButtonsContentPanel) {
        this.manipulatingButtonsContentPanel = manipulatingButtonsContentPanel;
    }

    public JLabel getVerbCounterLabel() {
        return verbCounterLabel;
    }

    public void setVerbCounterLabel(JLabel verbCounterLabel) {
        this.verbCounterLabel = verbCounterLabel;
    }

    public JLabel getRhymeCounterLabel() {
        return rhymeCounterLabel;
    }

    public void setRhymeCounterLabel(JLabel rhymeCounterLabel) {
        this.rhymeCounterLabel = rhymeCounterLabel;
    }
}
