package be.renaud11232.streamscraperreloaded;

import be.renaud11232.streamscraperreloaded.scraper.IceCastScraper;
import be.renaud11232.streamscraperreloaded.scraper.ShoutCast1Scraper;

import java.net.URI;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        System.out.println("IceCast");
        Scraper sc = new IceCastScraper();
        List<Stream> streams = sc.scrape(new URI("http://109.123.110.250:8000/"));
        //TODO add support for recent icecasts
        //List<Stream> streams = sc.scrape(new URI("http://stream.breddmedia.hu:8060/"));
        for(Stream s : streams){
            System.out.println(s);
        }
        System.out.println("---------------------------------------------------");
        System.out.println("ShoutCast1");
        Scraper sc2 = new ShoutCast1Scraper();
        List<Stream> streams2 = sc2.scrape(new URI("http://92.222.38.225:2012/"));
        //TODO add support for shoutcast 2
        //List<Stream> streams2 = sc2.scrape(new URI("http://192.168.1.211:8000"));
        for(Stream s : streams2){
            System.out.println(s);
        }
        System.out.println("---------------------------------------------------");
    }
}
