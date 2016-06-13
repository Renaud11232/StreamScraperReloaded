package be.renaud11232.streamscraperreloaded.scraper;

import be.renaud11232.streamscraperreloaded.FetchException;
import be.renaud11232.streamscraperreloaded.ScrapeException;
import be.renaud11232.streamscraperreloaded.Scraper;
import be.renaud11232.streamscraperreloaded.Stream;
import be.renaud11232.streamscraperreloaded.fetcher.HttpFetcher;
import be.renaud11232.streamscraperreloaded.parser.IceCast2StatusParser;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.net.URI;
import java.util.LinkedList;
import java.util.List;

public class ShoutCast2Scraper implements Scraper{

    private HttpFetcher fetcher;
    private IceCast2StatusParser parser;

    public ShoutCast2Scraper(){
        fetcher = new HttpFetcher();
        parser = new IceCast2StatusParser();
    }

    @Override
    public List<Stream> scrape(URI uri) throws ScrapeException {
        try {
            List<Stream> streams = new LinkedList<>();
            List<Document> stats = getAvailableStreams(uri);
            int i = 1;
            for(Document stat : stats){
                streams.addAll(parser.parse(uri.resolve("/index.html?sid=" + i), stat));
                i++;
            }
            return streams;
        } catch (Exception e) {
            throw new ScrapeException(e);
        }
    }

    private List<Document> getAvailableStreams(URI uri) throws FetchException {
        Document stats = fetcher.fetch(uri.resolve("/index.html?sid=0"));
        Elements streamLinks = stats.select("td.tnl > a:first-of-type");
        List <Document> list = new LinkedList<>();
        for(Element link : streamLinks){
            String id = link.attr("href").split("sid=")[1];
            list.add(fetcher.fetch(uri.resolve("/stats?sid=" + id)));
        }
        return list;
    }
}
