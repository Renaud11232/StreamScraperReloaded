package be.renaud11232.streamscraperreloaded;


import java.net.URI;
import java.util.List;

public interface Scraper {
    public List<Stream> scrape(URI uri) throws ScrapeException;
}
