package application.notes.crud.edit.single;

import application.notes.crud.edit.model.LineOverwriterInformation;
import application.notes.wordcount.raw.NoteCounterRaw;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static application.notes.wordcount.raw.NoteCounterRaw.initializeNoteCounterRaw;
import static config.Globals.scanner;
import static application.notes.crud.edit.single.DisplayState.ERROR;
import static application.notes.crud.edit.single.DisplayState.SUCCESS;

public class NoteLineEditor {

    private String successMessage = "Edit was successfull!";

    public NoteLineEditor() {
    }

    public long countLineLength(final String noteName) {
        final NoteCounterRaw noteCounterRaw = initializeNoteCounterRaw(noteName);
        return noteCounterRaw.getLineCount();
    }

    protected void openErrorDialogue(final Path completePath) {
        try (Stream<String> lineStream = Files.lines(completePath)) {
            final long lineLength = lineStream.count();
            System.out.println("error -> allowed manipulation range is: 2 - " +
                    lineLength + "for this note");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected void openChangeDialogue(final Path completePath, final int lineNumber) {
        try {
            final int indexLineNumber = lineNumber - 1;
            final String lineToChange = Files.readAllLines(completePath).get(indexLineNumber);
            System.out.println(lineToChange);
            System.out.println("Type in a line to replace above line. If you don't want to change anything, type '_' without '' and hit enter");
            final String overwriteLine = scanner.nextLine();
            System.out.println(overwriteLine);
            if (overwriteLine.equals("_")) {
                return;
            }
            final LineOverwriterInformation lineOverwriterInformation = new LineOverwriterInformation.Builder()
                    .replacementLine(overwriteLine)
                    .path(completePath)
                    .indexLineNumber(indexLineNumber)
                    .build();
            overwriteLine(lineOverwriterInformation);
            printSuccessMessage();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void printSuccessMessage() {
        System.out.println(successMessage);
    }

    public void overwriteLine(final LineOverwriterInformation lineOverwriterInformation) throws IOException {
        List<String> lines;
        final Path path = lineOverwriterInformation.getCompletePath();
        final int indexLineNumber = lineOverwriterInformation.getIndexLineNumber();
        final String replacementLine = lineOverwriterInformation.getReplacementLine();
        lines = Files.readAllLines(Paths.get(String.valueOf(path)));
        lines.set(indexLineNumber, replacementLine);
        Files.write(Paths.get(String.valueOf(path)), Collections.singleton(String.join("\n", lines)));
    }

    public boolean noteHasEnoughLines(final Path completePath, final int lineNumber) throws IOException {
        try (Stream<String> stringStream = Files.lines(completePath)) {
            final long lineCount = stringStream.count();
            if (lineCount < lineNumber || lineNumber < 1) {
                return false;
            }
        }
        return true;
    }

    protected DisplayState displayLineByLinesOfNote(final Path completePath) {
        try (LineNumberReader lineNumberReader = new LineNumberReader(
                new InputStreamReader(Files.newInputStream(completePath)))) {
            String str;
            while ((str = lineNumberReader.readLine()) != null) {
                System.out.println(lineNumberReader.getLineNumber() + ": " + str);
            }
            return SUCCESS;
        } catch (IOException e) {
            e.printStackTrace();
            return ERROR;
        }
    }
}
