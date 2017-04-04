/*
 * Copyright (c) 2016 R. Gaspard
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

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

/**
 * Class used to parse the ShoutCast 1 status page
 */
public class ShoutCast1StatusParser implements Parser {
    /**
     * {@inheritDoc}
     */
    @Override
    public List<Stream> parse(URI uri, Document src) throws ParseException {
        try {
            return parseSource(uri, src);
        } catch (Exception e) {
            throw new ParseException(e);
        }
    }

    private List<Stream> parseSource(URI uri, Document src) {
        Stream stream = new Stream();
        stream.setUri(uri.resolve("/"));
        Elements tables = src.select("table[align=center]");
        for (Element table : tables) {
            if (parseTable(table, stream)) {
                return Collections.singletonList(stream);
            }
            stream.clear();
        }
        return Collections.emptyList();
    }

    private boolean parseTable(Element table, Stream stream) {
        boolean toReturn = false;
        Elements rows = table.select(":root > tbody > tr");
        for (Element row : rows) {
            Elements cols = row.select(":root > td");
            if (cols.size() == 2) {
                String name = cols.get(0).text().trim();
                String value = cols.get(1).text();
                toReturn = parseAttribute(name, value, stream) || toReturn;
            }
        }
        return toReturn;
    }

    private boolean parseAttribute(String name, String value, Stream stream) {
        switch (name.toLowerCase()) {
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
