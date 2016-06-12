package be.renaud11232.streamscraperreloaded;

import java.net.URI;

public interface Fetcher {
    public byte[] fetch(URI uri) throws FetchException;
}