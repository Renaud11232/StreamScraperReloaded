package be.renaud11232.streamscraperreloaded.scraper;

import be.renaud11232.streamscraperreloaded.*;
import be.renaud11232.streamscraperreloaded.fetcher.HttpFetcher;
import be.renaud11232.streamscraperreloaded.parser.IceCastParser;
import org.jsoup.nodes.Document;

import java.net.URI;
import java.util.List;

public class IceCastScraper implements Scraper {

    private Fetcher fetcher;
    private Parser parser;

    public IceCastScraper() {
        fetcher = new HttpFetcher();
        parser = new IceCastParser();
    }

    public Fetcher getFetcher() {
        return fetcher;
    }

    public void setFetcher(Fetcher fetcher) {
        this.fetcher = fetcher;
    }

    public Parser getParser() {
        return parser;
    }

    public void setParser(Parser parser) {
        this.parser = parser;
    }

    @Override
    public List<Stream> scrape(URI uri) throws ScrapeException {
        try {
            Document doc = fetcher.fetch(uri.resolve("/status.xsl"));
            return parser.parse(uri, doc);
        } catch (Exception e) {
            throw new ScrapeException(e);
        }
    }
}
