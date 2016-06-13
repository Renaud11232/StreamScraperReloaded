# StreamScraperReloaded

StreamScraperReloaded is a java library that can be used to harvest streaming metadata (current song title, genre, current listener count, etc.) from SHOUTcast/IceCast servers.
This project is a continuation of Kohei TAKETA's streamscraper project. But as it is not maintained anymore. It had some compatibility issues with the latest IceCast and ShoutCast servers.
[Original project]( https://code.google.com/archive/p/streamscraper/)

*StreamScraperReloaded Goals*
  * Provide simple methods and classes to harvest streaming metadata
  
*How to use*
```
import be.renaud11232.streamscraperreloaded.Scraper;
import be.renaud11232.streamscraperreloaded.Stream;
import be.renaud11232.streamscraperreloaded.scraper.IceCastScraper;

import java.util.List;
import java.net.URI;

public class Harvester {
    public static void main(String[] args) throws Exception{
        Scraper scraper = new IceCastScraper();
        List<Stream> streams = scraper.scrape(new URI("http://host:port"));
        for(Stream stream : streams){
            System.out.println(stream.getCurrentSong());
        }
    }
}
```

*API Documentation*
  * Will come later
  
*License*
```
Copyright (c) 2016 R. Gaspard

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
```