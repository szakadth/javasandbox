package hu.szakadth.exception;

/**
 * Created by bogrea on 2018.02.27..
 */
public class RException extends Exception {

    public RException(String message) {
        super(message);
    }

    public RException(String message, Throwable cause) {
        super(message, cause);
    }

    public RException(Throwable cause) {
        super(cause);
    }

    public RException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
