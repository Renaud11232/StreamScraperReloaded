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

import be.renaud11232.streamscraperreloaded.*;
import be.renaud11232.streamscraperreloaded.fetcher.HttpFetcher;
import be.renaud11232.streamscraperreloaded.parser.IceCastJsonParser;
import be.renaud11232.streamscraperreloaded.parser.IceCastStatusParser;
import org.jsoup.nodes.Document;

import java.net.URI;
import java.util.List;

/**
 * Class used to collect data from an IceCast server
 */
public class IceCastScraper implements Scraper {

    private Fetcher fetcher;
    private Parser parser;
    private IceCastJsonParser jsonParser;

    /**
     * Constructs a new {@link IceCastScraper}
     */
    public IceCastScraper() {
        fetcher = new HttpFetcher();
        parser = new IceCastStatusParser();
        jsonParser = new IceCastJsonParser();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Stream> scrape(URI uri) throws ScrapeException {
        try {
            Document json = fetcher.fetch(uri.resolve("/status-json.xsl"));
            return jsonParser.parse(uri, json);
        } catch (Exception e) {
            try {
                Document doc = fetcher.fetch(uri.resolve("/status.xsl"));
                return parser.parse(uri, doc);
            } catch (Exception e2) {
                throw new ScrapeException(e2);
            }
        }

    }
}
