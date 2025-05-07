package pipy.global;

public class PipyException extends RuntimeException {

    public PipyException(final String message) {
        super(message);
    }

    public PipyException(final String message, final Throwable cause) {
        super(message, cause);
    }
}
