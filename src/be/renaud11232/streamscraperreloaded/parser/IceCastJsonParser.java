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
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.net.URI;
import java.util.LinkedList;
import java.util.List;

public class IceCastJsonParser implements Parser {
    @Override
    public List<Stream> parse(URI uri, Document src) throws ParseException {
        try {
            Element body = src.body();
            JsonObject json = new JsonParser().parse(body.text()).getAsJsonObject();
            JsonArray sources = json.get("icestats").getAsJsonObject().get("source").getAsJsonArray();
            List<Stream> streams = new LinkedList<>();
            for (JsonElement source : sources) {
                JsonObject sourceObject = source.getAsJsonObject();
                Stream stream = new Stream();
                stream.setTitle(sourceObject.get("server_name") == null ? null : sourceObject.get("server_name").getAsString());
                stream.setDescription(sourceObject.get("server_description") == null ? null : sourceObject.get("server_description").getAsString());
                stream.setUri(sourceObject.get("listenurl") == null ? null : uri.resolve(sourceObject.get("listenurl").getAsString().substring(sourceObject.get("listenurl").getAsString().lastIndexOf("/"))));
                stream.setCurrentListenerCount(sourceObject.get("listeners") == null ? -1 : sourceObject.get("listeners").getAsInt());
                stream.setPeakListenerCount(sourceObject.get("listener_peak") == null ? -1 : sourceObject.get("listener_peak").getAsInt());
                stream.setBitRate(sourceObject.get("bitrate") == null ? null : sourceObject.get("bitrate").getAsInt() + "");
                stream.setCurrentSong(sourceObject.get("title") == null ? null : sourceObject.get("title").getAsString());
                stream.setContentType(sourceObject.get("server_type") == null ? null : sourceObject.get("server_type").getAsString());
                stream.setGenre(sourceObject.get("genre") == null ? null : sourceObject.get("genre").getAsString());
                streams.add(stream);
            }
            return streams;
        } catch (Exception e) {
            throw new ParseException(e);
        }
    }
}
