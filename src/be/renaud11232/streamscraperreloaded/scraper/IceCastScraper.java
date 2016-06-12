package be.renaud11232.streamscraperreloaded.scraper;

import be.renaud11232.streamscraperreloaded.*;
import be.renaud11232.streamscraperreloaded.fetcher.HttpFetcher;
import be.renaud11232.streamscraperreloaded.parser.IceCastJsonParser;
import be.renaud11232.streamscraperreloaded.parser.IceCastStatusParser;
import org.jsoup.nodes.Document;

import java.net.URI;
import java.util.List;

public class IceCastScraper implements Scraper {

    private Fetcher fetcher;
    private Parser parser;
    private IceCastJsonParser jsonParser;

    public IceCastScraper() {
        fetcher = new HttpFetcher();
        parser = new IceCastStatusParser();
        jsonParser = new IceCastJsonParser();
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

    public IceCastJsonParser getJsonParser() {
        return jsonParser;
    }

    public void setJsonParser(IceCastJsonParser jsonParser) {
        this.jsonParser = jsonParser;
    }

    @Override
    public List<Stream> scrape(URI uri) throws ScrapeException {
        try{
            Document json = fetcher.fetch(uri.resolve("/status-json.xsl"));
            return jsonParser.parse(uri, json);
        } catch (Exception e){
            try {
                Document doc = fetcher.fetch(uri.resolve("/status.xsl"));
                return parser.parse(uri, doc);
            } catch (Exception e2) {
                throw new ScrapeException(e2);
            }
        }

    }
}
