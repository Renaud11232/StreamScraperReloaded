package be.renaud11232.streamscraperreloaded;

import java.net.URI;
import java.util.List;

public interface Parser {
    List<Stream> parse(URI uri, byte[] src) throws ParseException;
}
