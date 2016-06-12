package be.renaud11232.streamscraperreloaded.parser;

import be.renaud11232.streamscraperreloaded.ParseException;
import be.renaud11232.streamscraperreloaded.Parser;
import be.renaud11232.streamscraperreloaded.Stream;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.net.URI;
import java.util.Collections;
import java.util.List;

public class ShoutCast1StatusParser implements Parser{
    @Override
    public List<Stream> parse(URI uri, Document src) throws ParseException {
        try {
            return parseSource(uri, src);
        }catch (ParseException e){
            throw e;
        } catch (Exception e){
            throw new ParseException(e);
        }
    }

    private List<Stream> parseSource(URI uri, Document src) throws ParseException {
        Stream stream = new Stream();
        stream.setUri(uri.resolve("/"));
        Elements tables = src.select("table[align=center]");
        for(Element table : tables){
            if(parseTable(table, stream)){
                return Collections.singletonList(stream);
            }
            stream.clear();
        }
        throw new ParseException("Could not parse the status page");
    }

    private boolean parseTable(Element table, Stream stream){
        boolean toReturn = false;
        Elements rows = table.select(":root > tbody > tr");
        for(Element row : rows){
            Elements cols = row.select(":root > td");
            if(cols.size() == 2){
                String name = cols.get(0).text().trim();
                String value = cols.get(1).text();
                toReturn = parseAttribute(name, value, stream) || toReturn;
            }
        }
        return toReturn;
    }

    private boolean parseAttribute(String name, String value, Stream stream){
        switch(name.toLowerCase()){
            case "listener peak:":
                stream.setPeakListenerCount(Integer.parseInt(value));
                return true;
            case "stream title:":
                stream.setTitle(value);
                return true;
            case "content type:":
                stream.setContentType(value);
                return true;
            case "stream genre:":
                stream.setGenre(value);
                return true;
            case "current song:":
                stream.setCurrentSong(value);
                return true;
            default:
                return false;
        }
    }
}
