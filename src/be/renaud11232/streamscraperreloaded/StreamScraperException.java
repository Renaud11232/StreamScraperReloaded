package be.renaud11232.streamscraperreloaded;


public class StreamScraperException extends Exception {
    public StreamScraperException() {
        super();
    }

    public StreamScraperException(String message) {
        super(message);
    }

    public StreamScraperException(String message, Throwable cause) {
        super(message, cause);
    }

    public StreamScraperException(Throwable cause) {
        super(cause);
    }
}
