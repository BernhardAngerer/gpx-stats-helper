package at.bernhardangerer.gpxStatsHelper.exception;

public final class WebserviceCallException extends Exception {
    public WebserviceCallException() {
    }

    public WebserviceCallException(final String message) {
        super(message);
    }

    public WebserviceCallException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public WebserviceCallException(final Throwable cause) {
        super(cause);
    }
}
