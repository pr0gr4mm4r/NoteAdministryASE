package application.notes.spellcheck.model;

public class StringRepresentation {

    private String stringRepresentation;

    public StringRepresentation(String stringRepresentation) {
        this.stringRepresentation = stringRepresentation;
    }

    public void print() {
        System.out.println(stringRepresentation);
        System.out.println();
    }

    public String getStringRepresentation() {
        return stringRepresentation;
    }

    public void setStringRepresentation(String stringRepresentation) {
        this.stringRepresentation = stringRepresentation;
    }

    public void insertLineBreak(){
        stringRepresentation += "\n";
    }
    public void insertSpace(){
        stringRepresentation += " ";
    }

    public void add(String stringToAdd) {
        stringRepresentation += stringToAdd;
    }
}
