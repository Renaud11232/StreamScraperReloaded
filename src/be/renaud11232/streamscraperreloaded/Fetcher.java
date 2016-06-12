package be.renaud11232.streamscraperreloaded;

import org.jsoup.nodes.Document;

import java.net.URI;

public interface Fetcher {
    public Document fetch(URI uri) throws FetchException;
}