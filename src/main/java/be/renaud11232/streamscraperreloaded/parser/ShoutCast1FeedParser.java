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

import java.net.URI;
import java.util.Collections;
import java.util.List;

/**
 * Class used to parse the ShoutCast 1 7.html page into streams
 */
public class ShoutCast1FeedParser implements Parser {

    /**
     * {@inheritDoc}
     */
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
