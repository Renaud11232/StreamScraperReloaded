package be.renaud11232.streamscraperreloaded;

public class FetchException extends StreamScraperException {
    public FetchException() {
        super();
    }

    public FetchException(String message) {
        super(message);
    }

    public FetchException(String message, Throwable cause) {
        super(message, cause);
    }

    public FetchException(Throwable cause) {
        super(cause);
    }
}