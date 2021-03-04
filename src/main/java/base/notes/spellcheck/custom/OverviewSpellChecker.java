package base.notes.spellcheck.custom;

import base.logfiles.crud.declare.LogFileDeclarator;
import base.notes.processors.MultiNoteProcessor;
import base.notes.spellcheck.raw.SpellCheckerRaw;
import base.notes.spellcheck.model.WordExistenceModel;

import java.util.*;

import static base.config.Globals.scanner;
import static base.start.NoteAdministryStart.programIsRunning;


public class OverviewSpellChecker {
    private final List<WordExistenceModel> wordExistenceOverview = new ArrayList<>();
    private final String confirmation;


    public OverviewSpellChecker() {
        final MultiNoteProcessor multiNoteProcessor = new MultiNoteProcessor();
        final List<String[]> wordListList = multiNoteProcessor.getWordListList();
        for (String[] wordList : wordListList) {
            SpellCheckerRaw spellCheckerRaw = new SpellCheckerRaw(wordList);
            spellCheckerRaw.checkSpelling(wordList);
            List<String> wordsInLexicon = spellCheckerRaw.getWordsInLexicon();
            List<String> wordsNotInLexicon = spellCheckerRaw.getWordsNotInLexicon();
            WordExistenceModel wordExistenceModel = new WordExistenceModel();
            wordExistenceModel.fill(wordsInLexicon, wordsNotInLexicon);
            String resultString = wordExistenceModel.formatWordExistenceOverview();
            wordExistenceOverview.add(wordExistenceModel);
        }
        System.out.println(wordExistenceOverview);
        System.out.println("Do you want to save the output to a logfile?");
        confirmation = scanner.nextLine();
        if (confirmation.equals("yes")) {
            LogFileDeclarator logFileDeclarator = new LogFileDeclarator("");
        }

    }

    public List<WordExistenceModel> getWordExistenceOverview() {
        return wordExistenceOverview;
    }
}
