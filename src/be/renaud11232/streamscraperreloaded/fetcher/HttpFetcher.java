package be.renaud11232.streamscraperreloaded.fetcher;

import be.renaud11232.streamscraperreloaded.FetchException;
import be.renaud11232.streamscraperreloaded.Fetcher;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.net.URI;

public class HttpFetcher implements Fetcher{

    public HttpFetcher(){}

    @Override
    public Document fetch(URI uri) throws FetchException {
        try {
            return Jsoup.connect(uri.toString())
                    .ignoreHttpErrors(false)
                    .userAgent("Mozilla/5.0 (compatible; StreamScraperReloaded/1.0; +https://gitlab.com/Renaud11232/StreamScraperReloaded)")
                    .timeout(10000)
                    .get();
        } catch (IOException e) {
            throw new FetchException(e);
        }
    }
}
