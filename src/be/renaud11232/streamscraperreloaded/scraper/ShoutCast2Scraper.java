/*
 * Copyright (c) 2016 R. Gaspard
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package be.renaud11232.streamscraperreloaded.scraper;

import be.renaud11232.streamscraperreloaded.FetchException;
import be.renaud11232.streamscraperreloaded.ScrapeException;
import be.renaud11232.streamscraperreloaded.Scraper;
import be.renaud11232.streamscraperreloaded.Stream;
import be.renaud11232.streamscraperreloaded.fetcher.HttpFetcher;
import be.renaud11232.streamscraperreloaded.parser.ShoutCast2StatusParser;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.net.URI;
import java.util.LinkedList;
import java.util.List;

public class ShoutCast2Scraper implements Scraper {

    private HttpFetcher fetcher;
    private ShoutCast2StatusParser parser;

    public ShoutCast2Scraper() {
        fetcher = new HttpFetcher();
        parser = new ShoutCast2StatusParser();
    }

    @Override
    public List<Stream> scrape(URI uri) throws ScrapeException {
        try {
            List<Stream> streams = new LinkedList<>();
            List<Document> stats = getAvailableStreams(uri);
            int i = 1;
            for (Document stat : stats) {
                streams.addAll(parser.parse(uri.resolve("/index.html?sid=" + i), stat));
                i++;
            }
            return streams;
        } catch (Exception e) {
            throw new ScrapeException(e);
        }
    }

    private List<Document> getAvailableStreams(URI uri) throws FetchException {
        Document stats = fetcher.fetch(uri.resolve("/index.html?sid=0"));
        Elements streamLinks = stats.select("td.tnl > a:first-of-type");
        List<Document> list = new LinkedList<>();
        for (Element link : streamLinks) {
            String id = link.attr("href").split("sid=")[1];
            list.add(fetcher.fetch(uri.resolve("/stats?sid=" + id)));
        }
        return list;
    }
}
