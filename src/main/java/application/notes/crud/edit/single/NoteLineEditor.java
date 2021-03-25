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

import static config.Globals.scanner;
import static application.notes.crud.edit.single.DisplayState.ERROR;
import static application.notes.crud.edit.single.DisplayState.SUCCESS;

public class NoteLineEditor {

    public NoteLineEditor() {
    }

    public long countLineLength(String noteName) {
        NoteCounterRaw noteCounterRaw = NoteCounterRaw.initializeNoteCounterRaw(noteName);
        return noteCounterRaw.getLineCount();
    }

    protected void openErrorDialogue(Path completePath) {
        try (Stream<String> lineStream = Files.lines(completePath)) {
            long lineLength = lineStream.count();
            System.out.println("error -> allowed manipulation range is: 2 - " +
                    lineLength + "for this note");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected void openChangeDialogue(Path completePath, int lineNumber) {
        try {
            final int indexLineNumber = lineNumber - 1;
            String lineToChange = Files.readAllLines(completePath).get(indexLineNumber);
            System.out.println(lineToChange);
            System.out.println("Type in a line to replace above line. If you don't want to change anything, type '_' without '' and hit enter");
            String overwriteLine = scanner.nextLine();
            System.out.println(overwriteLine);
            if (overwriteLine.equals("_")) {
                return;
            }
            LineOverwriterInformation lineOverwriterInformation = new LineOverwriterInformation.Builder()
                    .replacementLine(overwriteLine)
                    .path(completePath)
                    .indexLineNumber(indexLineNumber)
                    .build();
            overwriteLine(lineOverwriterInformation);
            System.out.println("Edit was successfull!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void overwriteLine(LineOverwriterInformation lineOverwriterInformation) throws IOException {
        List<String> lines;
        Path path = lineOverwriterInformation.getCompletePath();
        int indexLineNumber = lineOverwriterInformation.getIndexLineNumber();
        String replacementLine = lineOverwriterInformation.getReplacementLine();
        lines = Files.readAllLines(Paths.get(String.valueOf(path)));
        lines.set(indexLineNumber, replacementLine);
        Files.write(Paths.get(String.valueOf(path)), Collections.singleton(String.join("\n", lines)));
    }

    public boolean noteHasEnoughLines(Path completePath, int lineNumber) {
        try (Stream<String> stringStream = Files.lines(completePath)) {
            long lineCount = stringStream.count();
            if (lineCount < lineNumber || lineNumber < 1) {
                return false;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    protected DisplayState displayLineByLinesOfNote(Path completePath) {
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
