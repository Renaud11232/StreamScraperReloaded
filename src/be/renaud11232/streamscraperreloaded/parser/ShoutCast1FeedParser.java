package be.renaud11232.streamscraperreloaded.parser;

import be.renaud11232.streamscraperreloaded.ParseException;
import be.renaud11232.streamscraperreloaded.Parser;
import be.renaud11232.streamscraperreloaded.Stream;
import org.jsoup.nodes.Document;

import java.net.URI;
import java.util.Collections;
import java.util.List;

public class ShoutCast1FeedParser implements Parser {
    @Override
    public List<Stream> parse(URI uri, Document src) throws ParseException {
        try {
            return parseSource(uri, src);
        } catch (ParseException e) {
            throw e;
        } catch (Exception e) {
            throw new ParseException(e);
        }
    }

    private List<Stream> parseSource(URI uri, Document src) throws ParseException {
        String line = src.select("body").text();
        String[] attributes = line.split(",", 7);
        if (attributes.length != 7) {
            throw new ParseException("ShoutCast 1 feed does not match the required format. Needed 7 fields and got " + attributes.length);
        }
        // attrs[0] : curListeners
        // attrs[1] : server status (0=down, 1=up)
        // attrs[2] : listener peak
        // attrs[3] : max listeners
        // attrs[4] : unique listeners
        // attrs[5] : bit rate
        // attrs[6] : song title
        Stream stream = new Stream();
        stream.setUri(uri.resolve("/"));
        stream.setCurrentListenerCount(Integer.parseInt(attributes[0]));
        if (Integer.parseInt(attributes[1]) == 0) {
            throw new ParseException("Server is down");
        }
        stream.setPeakListenerCount(Integer.parseInt(attributes[2]));
        stream.setMaxListenerCount(Integer.parseInt(attributes[3]));
        stream.setBitRate(attributes[5]);
        stream.setCurrentSong(attributes[6]);
        return Collections.singletonList(stream);
    }
}
