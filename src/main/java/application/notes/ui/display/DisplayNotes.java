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
import java.util.ArrayList;
import java.util.List;

import static application.notes.processors.multi.NoteStack.initializeNoteStack;
import static config.Globals.path_for_notes;

public class DisplayNotes extends JFrame implements DisplayTechnology {
    private List<JLabel> noteNameLabels = new ArrayList<>();
    private final List<JButton> noteDisplayButtons = new ArrayList<>();
    private final List<JButton> textManipulationButtons = new ArrayList<>();
    private GridLayout masterGrid = new GridLayout(3, 1);
    private final GridLayout textContentGrid = new GridLayout(1, 2);
    private GridLayout manipulatingButtonsGrid;

    private JLabel verbCounterLabel = new JLabel();
    private final JLabel rhymeCounterLabel = new JLabel();
    private JPanel masterPanel = new JPanel();
    private JPanel capturePanel = new JPanel();
    private JPanel noteButtonPanel = new JPanel();
    private JPanel noteTextPanel = new JPanel();
    private JPanel contentPanel = new JPanel();
    private JPanel manipulatingButtonsPanel = new JPanel();
    private JPanel manipulatingButtonsButtonsPanel = new JPanel();
    private final JPanel manipulatingButtonsContentPanel = new JPanel();
    private final JTextPane noteText = new JTextPane();
    private JScrollPane jScrollPane;

    public DisplayNotes() throws NoFilesInDirectoryException {
        this.setTitle("Overview of Notes");
        masterPanel.setLayout(masterGrid);
        final JLabel capture = new JLabel("Overview of all Notes in Directory " + path_for_notes);
        final Font font = new Font("Arial", Font.BOLD, 18);
        capture.setFont(font);
        capturePanel.add(capture);
        noteText.setEditable(false);
        noteTextPanel.add(noteText);
        jScrollPane = new JScrollPane(noteTextPanel);
        contentPanel.setLayout(textContentGrid);
        contentPanel.add(jScrollPane);
        final NoteStack noteStack = initializeNoteStack(path_for_notes);
        final List<String> noteNames = new ArrayList<>(noteStack.getNoteNames());
        for (int i = 0; i < noteNames.size(); i++) {
            final JLabel jLabel = new JLabel(noteNames.get(i));
            noteNameLabels.add(jLabel);
            final JButton jButton = new JButton(noteNames.get(i));
            jButton.addMouseListener(new HoverPointerMouseListener(jButton));
            jButton.addMouseListener(new NoteButtonsSelectedMouseListener(this, jButton));
            jButton.addActionListener(e -> {
                this.getManipulatingButtonsContentPanel().removeAll();
                this.verbCounterLabel = new JLabel(" "); //reset counter
                final JPanel jPanel = new JPanel();
                jPanel.add(verbCounterLabel);
                this.getManipulatingButtonsContentPanel().add(jPanel);
                this.invalidate();
                this.validate();
            });
            final NoteButtonActionListener actionListener = new NoteButtonActionListener(this, jButton.getText());
            jButton.addActionListener(actionListener);
            noteDisplayButtons.add(jButton);
            noteButtonPanel.add(noteDisplayButtons.get(i));
        }
        textManipulationButtons.add(new VerbCounterDisplayButton("Verbs", this));
        textManipulationButtons.add(new RhymeCounterDisplayButton("Rhymes", this));
        manipulatingButtonsGrid = new GridLayout(textManipulationButtons.size(), 1);
        manipulatingButtonsPanel.setLayout(new GridLayout(2, 1));
        for (final JButton textManipulationButton : textManipulationButtons) {
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
