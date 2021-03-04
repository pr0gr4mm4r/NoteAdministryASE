package base.notes.crud.read;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static base.config.Globals.scanner;
import static base.notes.crud.declare.NoteDeclarator.createCompletePath;

public class NoteReader {

    public NoteReader() {
        System.out.println("Please provide a name for the note to read:");
        String noteName = scanner.nextLine();
        Path path = createCompletePath(noteName);
        String note = readNote(path);
        System.out.println(note);
    }

    public static String readNote(Path completePath) {
        try(Stream<String> stringStream = Files.lines(completePath)) {
            return stringStream.collect(
                    Collectors.joining("\n"));
        } catch (IOException e) {
            e.printStackTrace();
            return "fail to read";
        }
    }

    public static String readNoteForFurtherProcessing(Path completePath) {
        try(Stream<String> stringStream = Files.lines(completePath)) {
            return stringStream.collect(
                    Collectors.joining(" "));
        } catch (IOException e) {
            e.printStackTrace();
            return "fail to read";
        }
    }
}
