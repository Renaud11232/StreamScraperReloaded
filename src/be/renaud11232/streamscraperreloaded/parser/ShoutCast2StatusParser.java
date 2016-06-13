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
 * Class used to parse the ShoutCast 2 XML Status page
 */
public class ShoutCast2StatusParser implements Parser {
    /**
     * {@inheritDoc}
     */
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
        } catch (Exception e) {
            throw new ParseException(e);
        }
    }
}
