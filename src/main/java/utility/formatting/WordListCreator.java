package utility.formatting;

public class WordListCreator {
    public WordListCreator() {
    }
    public static String[] createWordList(final String string){
        return string.split(" ");
    }
}
