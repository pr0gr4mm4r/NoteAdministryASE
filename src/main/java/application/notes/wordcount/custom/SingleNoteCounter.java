package application.notes.wordcount.custom;

import application.notes.wordcount.raw.NoteCounterRaw;

import static config.Globals.scanner;

public class SingleNoteCounter {
    private NoteCounterRaw noteCounterRaw;

    public SingleNoteCounter() {
        System.out.println("Which note do you want to count the lines and words for?");
        String noteName = scanner.nextLine();
        noteCounterRaw = new NoteCounterRaw(noteName);
        long linesCount = noteCounterRaw.getLineCount();
        long wordsCount = noteCounterRaw.getWordCount();
        System.out.println(
                noteName + " contains " + linesCount + " lines and " + wordsCount + " words.");
    }
}
