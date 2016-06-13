/*
 * Copyright (c) 2016 R. Gaspard
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package be.renaud11232.streamscraperreloaded.fetcher;

import be.renaud11232.streamscraperreloaded.FetchException;
import be.renaud11232.streamscraperreloaded.Fetcher;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.net.URI;

public class HttpFetcher implements Fetcher {

    public HttpFetcher() {
    }

    @Override
    public Document fetch(URI uri) throws FetchException {
        try {
            return Jsoup.connect(uri.toString())
                    .ignoreHttpErrors(false)
                    .userAgent("Mozilla/5.0 (compatible; StreamScraperReloaded/1.0; +https://gitlab.com/Renaud11232/StreamScraperReloaded)")
                    .timeout(10000)
                    .ignoreContentType(true)
                    .get();
        } catch (IOException e) {
            throw new FetchException(e);
        }
    }
}
