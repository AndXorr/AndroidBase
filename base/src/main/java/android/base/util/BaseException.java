package android.base.util;

/**
 * Created by sahni on 6/6/16.
 */
public class BaseException extends Exception {

    public BaseException(String detailMessage) {
        super(detailMessage);
    }

    public BaseException(String detailMessage, Throwable throwable) {
        super(detailMessage, throwable);
    }
}
