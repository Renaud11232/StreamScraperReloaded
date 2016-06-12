package be.renaud11232.streamscraperreloaded;

import be.renaud11232.streamscraperreloaded.scraper.IceCastScraper;

import java.net.URI;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        Scraper sc = new IceCastScraper();
        List<Stream> streams = sc.scrape(new URI("http://109.123.110.250:8000/"));
        for(Stream s : streams){
            System.out.println(s);
        }
    }
}
