package utility.confirmationString;

public class ConfirmationString {
    private final String confirmation;

    public ConfirmationString(String confirmation) {
        this.confirmation = confirmation;
    }

    public boolean confirm(){
        return confirmation.equals("yes");
    }
}
