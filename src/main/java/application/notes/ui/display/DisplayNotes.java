package application.notes.ui.display;

import application.notes.processors.multi.NoFilesInDirectoryException;
import application.notes.processors.multi.NoteStack;
import application.notes.ui.listener.action.NoteButtonActionListener;
import application.notes.ui.listener.mouse.HoverPointerMouseListener;
import application.notes.ui.buttons.RhymeCounterDisplayButton;
import application.notes.ui.buttons.VerbCounterDisplayButton;
import application.notes.ui.listener.mouse.NoteButtonsSelectedMouseListener;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static application.notes.processors.multi.NoteStack.initializeNoteStack;
import static config.Globals.path_for_notes;

public class DisplayNotes extends JFrame implements DisplayTechnology {
    private final List<JLabel> noteNameLabels = new ArrayList<>();
    private final List<JButton> noteDisplayButtons = new ArrayList<>();
    private final List<JButton> textManipulationButtons = new ArrayList<>();
    private final GridLayout masterGrid = new GridLayout(3, 1);
    private final GridLayout textContentGrid = new GridLayout(1, 2);
    private final GridLayout textManipulationButtonsGrid = new GridLayout(2, 1);
    private JLabel verbCounterLabel = new JLabel();
    private final JLabel rhymeCounterLabel = new JLabel();
    private final JPanel masterPanel = new JPanel();
    private final JPanel capturePanel = new JPanel();
    private final JPanel noteButtonPanel = new JPanel();
    private final JPanel noteTextPanel = new JPanel();
    private final JPanel contentPanel = new JPanel();
    private final JPanel textManipulationButtonsPanel = new JPanel();
    private final JPanel manipulatingButtonsButtonsPanel = new JPanel();
    private final JPanel manipulatingButtonsContentPanel = new JPanel();
    private final JTextPane noteText = new JTextPane();
    private JScrollPane jScrollPane;

    private DisplayNotes() {

    }

    public static void initializeDisplayNotes() throws NoFilesInDirectoryException, IOException { // Monstermethode von 53 Zeilen auf 17 Zeilen refactored
        DisplayNotes display = new DisplayNotes();
        display.defineGeneralFrameSettings();
        display.addCapture();
        display.definePanelLayouts();
        display.noteText.setEditable(false);
        display.noteTextPanel.add(display.noteText);
        display.jScrollPane = new JScrollPane(display.noteTextPanel);
        display.contentPanel.add(display.jScrollPane);
        display.addNoteButtons();
        display.fillTextManipulationButtons();
        display.prepareTextManipulationButtons();
        display.fillTextManipulationButtonsPanel();
        display.contentPanel.add(display.textManipulationButtonsPanel);
        display.addPanelsToMasterPanel();
        display.add(display.masterPanel);
        display.setVisible(true);
    }


    private void defineGeneralFrameSettings(){
        setTitle("Overview of Notes");
        setSize(800, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    private void addCapture() {
        final JLabel capture = new JLabel("Overview of all Notes in Directory " + path_for_notes);
        final Font font = new Font("Arial", Font.BOLD, 18);
        capture.setFont(font);
        capturePanel.add(capture);
    }

    private void definePanelLayouts() {
        masterPanel.setLayout(masterGrid);
        contentPanel.setLayout(textContentGrid);
        textManipulationButtonsPanel.setLayout(textManipulationButtonsGrid);
    }

    private void prepareTextManipulationButtons() {
        for (final JButton textManipulationButton : textManipulationButtons) {
            textManipulationButton.setEnabled(false);
            manipulatingButtonsButtonsPanel.add(textManipulationButton);
        }
    }

    private void fillTextManipulationButtons() {
        textManipulationButtons.add(new VerbCounterDisplayButton("Verbs", this));
        textManipulationButtons.add(new RhymeCounterDisplayButton("Rhymes", this));
    }

    private void addNoteButtons() throws NoFilesInDirectoryException, IOException {
        final NoteStack noteStack = initializeNoteStack(path_for_notes);
        final List<String> noteNames = new ArrayList<>(noteStack.getNoteNames());
        for (int i = 0; i < noteNames.size(); i++) {
            final JLabel jLabel = new JLabel(noteNames.get(i));
            noteNameLabels.add(jLabel);
            final JButton jButton = new JButton(noteNames.get(i));
            jButton.addMouseListener(new HoverPointerMouseListener(jButton));
            jButton.addMouseListener(new NoteButtonsSelectedMouseListener(this, jButton));
            jButton.addActionListener(e -> {
                getManipulatingButtonsContentPanel().removeAll();
                verbCounterLabel = new JLabel(" ");
                final JPanel jPanel = new JPanel();
                jPanel.add(verbCounterLabel);
                getManipulatingButtonsContentPanel().add(jPanel);
                invalidate();
                validate();
            });
            final NoteButtonActionListener actionListener = new NoteButtonActionListener(this, jButton.getText());
            jButton.addActionListener(actionListener);
            noteDisplayButtons.add(jButton);
            noteButtonPanel.add(noteDisplayButtons.get(i));
        }
    }

    private void fillTextManipulationButtonsPanel() {
        textManipulationButtonsPanel.add(manipulatingButtonsButtonsPanel);
        textManipulationButtonsPanel.add(manipulatingButtonsContentPanel);
    }

    private void addPanelsToMasterPanel() {
        masterPanel.add(capturePanel);
        masterPanel.add(noteButtonPanel);
        masterPanel.add(contentPanel);
    }

    public List<JButton> getNoteDisplayButtons() {
        return noteDisplayButtons;
    }

    public List<JButton> getTextManipulationButtons() {
        return textManipulationButtons;
    }

    public JTextPane getNoteText() {
        return noteText;
    }

    public JPanel getManipulatingButtonsContentPanel() {
        return manipulatingButtonsContentPanel;
    }

    public JLabel getVerbCounterLabel() {
        return verbCounterLabel;
    }

    public JLabel getRhymeCounterLabel() {
        return rhymeCounterLabel;
    }
}
