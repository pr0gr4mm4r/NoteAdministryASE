package base.notes.crud.edit;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

import static base.notes.crud.declare.NoteDeclarator.createCompletePath;

public class NoteLineEditor {
    final private Scanner scanner = new Scanner(System.in);
    private long lineLength = 0;

    public NoteLineEditor() {
        System.out.println("Which note do you want to change?");
        final String fileName = scanner.nextLine();
        final Path completePath = createCompletePath(fileName);
        if (displayFileWithLineNumbers(completePath)) {
            return;
        }
        lineLength = getLineLength(completePath);
        if (lineLength != 0) {
            System.out.println("Which line do you want to overwrite? The manipulation range is: 2 - " + lineLength);
            int lineNumber = scanner.nextInt();
            scanner.nextLine();
            if (noteHasEnoughLines(completePath, lineNumber)) {
                openChangeDialogue(completePath, lineNumber);
            } else {
                openErrorDialogue(completePath);
            }
        }
    }

    private long getLineLength(Path completePath) {
        try (Stream<String> lineStream = Files.lines(completePath)) {
            lineLength = lineStream.count();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lineLength;
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
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return true;
        }
    }
}
