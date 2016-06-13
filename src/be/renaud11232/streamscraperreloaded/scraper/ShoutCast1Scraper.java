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

import be.renaud11232.streamscraperreloaded.ScrapeException;
import be.renaud11232.streamscraperreloaded.Scraper;
import be.renaud11232.streamscraperreloaded.Stream;
import be.renaud11232.streamscraperreloaded.fetcher.HttpFetcher;
import be.renaud11232.streamscraperreloaded.parser.ShoutCast1FeedParser;
import be.renaud11232.streamscraperreloaded.parser.ShoutCast1StatusParser;
import org.jsoup.nodes.Document;

import java.net.URI;
import java.util.List;

public class ShoutCast1Scraper implements Scraper {

    private HttpFetcher fetcher;
    private ShoutCast1StatusParser statusParser;
    private ShoutCast1FeedParser feedParser;

    public ShoutCast1Scraper() {
        fetcher = new HttpFetcher();
        statusParser = new ShoutCast1StatusParser();
        feedParser = new ShoutCast1FeedParser();
    }

    @Override
    public List<Stream> scrape(URI uri) throws ScrapeException {
        try {
            Document feed = fetcher.fetch(uri.resolve("/7.html"));
            Document status = fetcher.fetch(uri.resolve("/"));
            List<Stream> feedStreams = feedParser.parse(uri, feed);
            List<Stream> statusStreams = statusParser.parse(uri, status);
            if (statusStreams.size() == 1 && feedStreams.size() == 1) {
                feedStreams.get(0).merge(statusStreams.get(0));
            }
            return feedStreams;
        } catch (Exception e) {
            throw new ScrapeException(e);
        }
    }
}
