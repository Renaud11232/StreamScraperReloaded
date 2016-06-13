package be.renaud11232.streamscraperreloaded.parser;

import be.renaud11232.streamscraperreloaded.ParseException;
import be.renaud11232.streamscraperreloaded.Parser;
import be.renaud11232.streamscraperreloaded.Stream;
import org.jsoup.nodes.Document;

import java.net.URI;
import java.util.Collections;
import java.util.List;

public class ShoutCast2StatusParser implements Parser{
    @Override
    public List<Stream> parse(URI uri, Document src) throws ParseException {
        try {
            Stream stream = new Stream();
            stream.setTitle(src.select("servertitle").text());
            stream.setUri(uri);
            stream.setCurrentListenerCount(Integer.parseInt(src.select("currentlisteners").text()));
            stream.setMaxListenerCount(Integer.parseInt(src.select("maxlisteners").text()));
            stream.setPeakListenerCount(Integer.parseInt(src.select("peaklisteners").text()));
            stream.setBitRate(src.select("bitrate").text());
            stream.setCurrentSong(src.select("songtitle").text());
            stream.setContentType(src.select("content").text());
            stream.setGenre(src.select("servergenre").text());
            return Collections.singletonList(stream);
        }catch(Exception e){
            throw new ParseException(e);
        }
    }
}
