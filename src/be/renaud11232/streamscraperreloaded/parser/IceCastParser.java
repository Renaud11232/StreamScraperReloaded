package be.renaud11232.streamscraperreloaded.parser;

import be.renaud11232.streamscraperreloaded.ParseException;
import be.renaud11232.streamscraperreloaded.Parser;
import be.renaud11232.streamscraperreloaded.Stream;
import org.jsoup.nodes.Document;

import java.net.URI;
import java.util.List;

public class IceCastParser implements Parser{
    @Override
    public List<Stream> parse(URI uri, Document src) throws ParseException {
        throw new UnsupportedOperationException("Not done yet");
    }
}
