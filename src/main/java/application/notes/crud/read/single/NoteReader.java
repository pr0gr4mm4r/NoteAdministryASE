package application.notes.crud.read.single;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class NoteReader {

    public NoteReader() {
    }

    protected void displayNote(final String note) {
        System.out.println(note);
    }

    public String readNote(final Path completePath) {
        try(Stream<String> stringStream = Files.lines(completePath)) {
            return stringStream.collect(
                    Collectors.joining("\n"));
        } catch (IOException e) {
            e.printStackTrace();
            return "fail to read";
        }
    }

    public static String readNoteForNoteProcessing(final Path completePath) {
        try(Stream<String> stringStream = Files.lines(completePath)) {
            return stringStream.collect(
                    Collectors.joining(" "));
        } catch (IOException e) {
            e.printStackTrace();
            return "fail to read";
        }
    }
}
