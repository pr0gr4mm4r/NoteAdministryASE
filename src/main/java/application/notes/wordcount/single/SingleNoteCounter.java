package application.notes.wordcount.single;

import application.notes.wordcount.raw.NoteCounterRaw;

import static application.notes.wordcount.raw.NoteCounterRaw.*;
import static config.Globals.scanner;

public class SingleNoteCounter {
    private NoteCounterRaw noteCounterRaw;

    public SingleNoteCounter(String noteName) {
        noteCounterRaw = initializeNoteCounterRaw(noteName);
        long linesCount = noteCounterRaw.getLineCount();
        long wordsCount = noteCounterRaw.getWordCount();
        System.out.println(
                noteName + " contains " + linesCount + " lines and " + wordsCount + " words.");
    }
}
