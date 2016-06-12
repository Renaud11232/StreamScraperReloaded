package be.renaud11232.streamscraperreloaded.scraper;

import be.renaud11232.streamscraperreloaded.ScrapeException;
import be.renaud11232.streamscraperreloaded.Scraper;
import be.renaud11232.streamscraperreloaded.Stream;
import be.renaud11232.streamscraperreloaded.fetcher.HttpFetcher;
import be.renaud11232.streamscraperreloaded.parser.ShoutCast1FeedParser;
import be.renaud11232.streamscraperreloaded.parser.ShoutCast1StatusParser;
import org.jsoup.nodes.Document;

import java.net.URI;
import java.util.List;

public class ShoutCast1Scraper implements Scraper {

    private HttpFetcher fetcher;
    private ShoutCast1StatusParser statusParser;
    private ShoutCast1FeedParser feedParser;

    public ShoutCast1Scraper(){
        fetcher = new HttpFetcher();
        statusParser = new ShoutCast1StatusParser();
        feedParser = new ShoutCast1FeedParser();
    }

    public HttpFetcher getFetcher() {
        return fetcher;
    }

    public void setFetcher(HttpFetcher fetcher) {
        this.fetcher = fetcher;
    }

    public ShoutCast1StatusParser getStatusParser() {
        return statusParser;
    }

    public void setStatusParser(ShoutCast1StatusParser statusParser) {
        this.statusParser = statusParser;
    }

    public ShoutCast1FeedParser getFeedParser() {
        return feedParser;
    }

    public void setFeedParser(ShoutCast1FeedParser feedParser) {
        this.feedParser = feedParser;
    }

    @Override
    public List<Stream> scrape(URI uri) throws ScrapeException {
        try{
            Document status = fetcher.fetch(uri.resolve("/"));
            Document feed = fetcher.fetch(uri.resolve("/7.html"));
            List<Stream> statusStreams = statusParser.parse(uri, status);
            List<Stream> feedStreams = feedParser.parse(uri, feed);
            if(statusStreams.size() == 1 && feedStreams.size() == 1){
                statusStreams.get(0).merge(feedStreams.get(0));
            }
            return statusStreams;
        }catch(Exception e){
            throw new ScrapeException(e);
        }
    }
}
