package base.notes.crud.edit;

import base.notes.wordcount.raw.NoteCounterRaw;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static base.config.Globals.scanner;

public class NoteLineEditor {
    private long lineLength = 0;

    public NoteLineEditor(Path path, String fileName) {
        boolean noteSuccessfullyDisplayed = displayFileWithLineNumbers(path);
        if (!noteSuccessfullyDisplayed) {
            openErrorDialogue(path);
            return;
        }
        NoteCounterRaw noteCounterRaw = new NoteCounterRaw(fileName);
        lineLength = noteCounterRaw.getLineCount();
        if (lineLength > 1) {
            System.out.println("Which line do you want to overwrite? The manipulation range is: 2 - " + lineLength);
            int lineNumber = scanner.nextInt();
            scanner.nextLine();
            if (noteHasEnoughLines(path, lineNumber)) {
                openChangeDialogue(path, lineNumber);
            } else {
                openErrorDialogue(path);
            }
        }
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
            overwriteLine(completePath, indexLineNumber, overwriteLine);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void overwriteLine(Path completePath, int indexLineNumber, String replacementLine) throws IOException {
        List<String> lines;
        lines = Files.readAllLines(Paths.get(String.valueOf(completePath)));
        lines.set(indexLineNumber, replacementLine);
        Files.write(Paths.get(String.valueOf(completePath)), Collections.singleton(String.join("\n", lines)));
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

    private boolean displayFileWithLineNumbers(Path completePath) {
        try (LineNumberReader lineNumberReader = new LineNumberReader(
                new InputStreamReader(Files.newInputStream(completePath)))) {
            String str;
            while ((str = lineNumberReader.readLine()) != null) {
                System.out.println(lineNumberReader.getLineNumber() + ": " + str);
            }
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
