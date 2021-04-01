package application.notes.spellcheck.model;

public class Result {
    private String resultString;

    public Result(String resultString) {
        this.resultString = resultString;
    }

    public void print() {
        System.out.println(resultString);
        System.out.println();
    }

    public String getResultString() {
        return resultString;
    }

    public void setResultString(String resultString) {
        this.resultString = resultString;
    }

    public void insertLineBreak(){
        resultString += "\n";
    }
    public void insertSpace(){
        resultString += " ";
    }

    public void add(String stringToAdd) {
        resultString += stringToAdd;
    }
}
