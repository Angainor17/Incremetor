package exceptions;

public class WrongMaxNumberException extends Exception {

    private int number;

    public WrongMaxNumberException(int number) {
        this.number = number;
    }

    @Override
    public String getMessage() {
        return String.format("Number can't be < 0. Current value = %s.", number);
    }
}
