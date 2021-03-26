package application.notes.wordcount.single;

import application.notes.wordcount.raw.NoteCounterRaw;

import static application.notes.wordcount.raw.NoteCounterRaw.*;

public class SingleNoteCounter {
    private NoteCounterRaw noteCounterRaw;

    private SingleNoteCounter() {

    }

    public static SingleNoteCounter initializeSingleNoteCounter(final String noteName) {
        final SingleNoteCounter singleNoteCounter = new SingleNoteCounter();
        singleNoteCounter.noteCounterRaw = initializeNoteCounterRaw(noteName);
        final long linesCount = singleNoteCounter.noteCounterRaw.getLineCount();
        final long wordsCount = singleNoteCounter.noteCounterRaw.getWordCount();
        singleNoteCounter.printResults(noteName, linesCount, wordsCount);
        return singleNoteCounter;
    }

    void printResults(final String noteName, final long linesCount, final long wordCount) {
        System.out.println(
                noteName + " contains " + linesCount + " lines and " + wordCount + " words.");
    }
}
