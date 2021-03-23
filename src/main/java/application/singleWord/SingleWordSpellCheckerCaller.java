package application.singleWord;

import static config.Globals.scanner;

public class SingleWordSpellCheckerCaller {
    public SingleWordSpellCheckerCaller() {
        System.out.println("Provide an english word to check if it is correctly spelled:");
        String wordToCheckSpelling = scanner.nextLine();
        new SingleWordSpellchecker(wordToCheckSpelling);
    }
}
