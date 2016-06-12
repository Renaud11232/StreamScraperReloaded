package be.renaud11232.streamscraperreloaded;

public class ScrapeException extends StreamScraperException {

    public ScrapeException() {
        super();
    }

    public ScrapeException(String message) {
        super(message);
    }

    public ScrapeException(String message, Throwable cause) {
        super(message, cause);
    }

    public ScrapeException(Throwable cause) {
        super(cause);
    }
}
