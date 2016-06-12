package be.renaud11232.streamscraperreloaded;

import org.jsoup.nodes.Document;

import java.net.URI;
import java.util.List;

public interface Parser {
    public List<Stream> parse(URI uri, Document src) throws ParseException;
}
