package exceptions;

public class ReadFailException extends RuntimeException {

    public ReadFailException(String message) {
        super(message);
    }
}
