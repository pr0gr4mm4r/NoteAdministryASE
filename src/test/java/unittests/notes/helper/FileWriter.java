package unittests.notes.helper;

import java.io.File;
import java.io.IOException;

public class FileWriter {
    public FileWriter() {
    }

    public static void writingTestContentToArtificialFile(final File createdFile, final String testContent) {
        try (java.io.FileWriter fileWriter = new java.io.FileWriter(createdFile)) {
            fileWriter.write(testContent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
