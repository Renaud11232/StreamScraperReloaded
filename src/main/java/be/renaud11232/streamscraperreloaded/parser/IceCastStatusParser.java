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
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class used to parse the IceCast server status page into streams
 */
public class IceCastStatusParser implements Parser {

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Stream> parse(URI uri, Document src) throws ParseException {
        try {
            List<Stream> streams = new LinkedList<>();
            parseSource(uri, src, streams);
            return streams;
        } catch (ParseException e) {
            throw e;
        } catch (Exception e) {
            throw new ParseException(e);
        }
    }

    private void parseSource(URI uri, Document src, List<Stream> streams) throws ParseException {
        Elements containers = src.select("div.newscontent");
        for (Element container : containers) {
            parseContainer(uri, container, streams);
        }
    }

    private void parseContainer(URI uri, Element container, List<Stream> streams) throws ParseException {
        Stream stream = new Stream();
        parseMountPoint(uri, container, stream);
        parseAttributes(container, stream);
        streams.add(stream);
    }

    private void parseMountPoint(URI uri, Element container, Stream stream) throws ParseException {
        String mountPoint = parseMountPoint231(container);
        if (mountPoint == null) {
            mountPoint = parseMountPoint232(container);
        }
        if (mountPoint != null) {
            stream.setUri(uri.resolve(mountPoint));
        } else {
            throw new ParseException("Error while parsing mount point, no valid mount point were found");
        }
    }

    private String parseMountPoint231(Element container) {
        Element a = container.select(":root > h3:first-of-type > a").first();
        if (a == null) {
            return null;
        }
        String value = a.attr("href");
        if (value == null || value.equals("")) {
            return null;
        }
        Matcher m = Pattern.compile("^(.*)\\.m3u$").matcher(value);
        if (!m.matches()) {
            return null;
        }
        return m.group(1);
    }

    private String parseMountPoint232(Element container) {
        Element h3 = container.select(":root > div.streamheader:first-of-type > table:first-of-type > tbody:first-of-type > tr:first-of-type > td:first-of-type > h3").first();
        if (h3 == null) {
            return null;
        }
        String value = h3.text();
        if (value == null || value.equals("")) {
            return null;
        }
        Matcher m = Pattern.compile("^Mount Point (.*)$").matcher(value);
        if (!m.matches()) {
            return null;
        }
        return m.group(1);
    }

    private void parseAttributes(Element container, Stream stream) throws ParseException {
        Elements rows = container.select(":root > table:first-of-type > tbody > tr");
        for (Element row : rows) {
            Elements cols = row.select(":root > td");
            if (cols.size() == 2) {
                String name = cols.get(0).text();
                String value = cols.get(1).text();
                switch (name.toLowerCase()) {
                    case "stream title:":
                        stream.setTitle(value);
                        break;
                    case "stream description:":
                        stream.setDescription(value);
                        break;
                    case "content type:":
                        stream.setContentType(value);
                        break;
                    case "bitrate:":
                        stream.setBitRate(value);
                        break;
                    case "current listeners:":
                        try {
                            stream.setCurrentListenerCount(Integer.parseInt(value));
                        } catch (NumberFormatException e) {
                            throw new ParseException(e);
                        }
                        break;
                    case "peak listeners:":
                        try {
                            stream.setPeakListenerCount(Integer.parseInt(value));
                        } catch (NumberFormatException e) {
                            throw new ParseException(e);
                        }
                        break;
                    case "stream genre:":
                        stream.setGenre(value);
                        break;
                    case "current song:":
                        stream.setCurrentSong(value);
                        break;
                }
            }

        }
    }
}
