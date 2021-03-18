package base.notes.crud.edit.single;

import base.notes.crud.edit.model.LineOverwriterInformation;
import base.notes.wordcount.raw.NoteCounterRaw;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static base.config.Globals.scanner;
import static base.notes.crud.edit.single.DisplayState.Failure;
import static base.notes.crud.edit.single.DisplayState.Successful;

public class NoteLineEditor {

    public NoteLineEditor(Path path, String fileName) {
        DisplayState displayState = displayFileWithLineNumbers(path);
        if (displayState.equals(Failure)) {
            openErrorDialogue(path);
            return;
        }
        long lineLength = countLineLength(fileName);
        final int lowerManipulationRangeCap = 1;
        if (lineLength > lowerManipulationRangeCap) {
            System.out.println("Which line do you want to overwrite? The manipulation range is: "
                    + lowerManipulationRangeCap + 1 + " - " + lineLength);
            int lineNumber = scanner.nextInt();
            if (noteHasEnoughLines(path, lineNumber)) {
                openChangeDialogue(path, lineNumber);
            } else {
                openErrorDialogue(path);
            }
        }
    }

    private long countLineLength(String fileName) {
        return new NoteCounterRaw(fileName).getLineCount();
    }

    private void openErrorDialogue(Path completePath) {
        try (Stream<String> lineStream = Files.lines(completePath)) {
            long lineLength = lineStream.count();
            System.out.println("error -> allowed manipulation range is: 2 - " +
                    lineLength + "for this note");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void openChangeDialogue(Path completePath, int lineNumber) {
        try {
            final int indexLineNumber = lineNumber - 1;
            String lineToChange = Files.readAllLines(completePath).get(indexLineNumber);
            System.out.println(lineToChange);
            System.out.println("Type in a line to replace above line. If you don't want to change anything, type '_' without '' and hit enter");
            String overwriteLine = scanner.nextLine();
            if (overwriteLine.equals("_")) {
                return;
            }
            LineOverwriterInformation lineOverwriterInformation = new LineOverwriterInformation.Builder()
                    .path(completePath)
                    .indexLineNumber(indexLineNumber)
                    .replacementLine(overwriteLine)
                    .build();
            overwriteLine(lineOverwriterInformation);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void overwriteLine(LineOverwriterInformation lineOverwriterInformation) throws IOException {
        List<String> lines;
        Path path = lineOverwriterInformation.getCompletePath();
        int indexLineNumber = lineOverwriterInformation.getIndexLineNumber();
        String replacementLine = lineOverwriterInformation.getReplacementLine();
        lines = Files.readAllLines(Paths.get(String.valueOf(path)));
        lines.set(indexLineNumber, replacementLine);
        Files.write(Paths.get(String.valueOf(path)), Collections.singleton(String.join("\n", lines)));
        System.out.println("Edit was successfull!");
    }

    private static boolean noteHasEnoughLines(Path completePath, int lineNumber) {
        try (Stream<String> stringStream = Files.lines(completePath)) {
            long lineCount = stringStream.count();
            if (lineCount < lineNumber || lineNumber < 2) {
                return false;
            }
        } catch (IOException e) {
           e.printStackTrace();
        }
        return true;
    }

    private DisplayState displayFileWithLineNumbers(Path completePath) {
        try (LineNumberReader lineNumberReader = new LineNumberReader(
                new InputStreamReader(Files.newInputStream(completePath)))) {
            String str;
            while ((str = lineNumberReader.readLine()) != null) {
                System.out.println(lineNumberReader.getLineNumber() + ": " + str);
            }
            return Successful;
        } catch (IOException e) {
            e.printStackTrace();
            return Failure;
        }
    }
}
