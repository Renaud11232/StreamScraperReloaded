package be.renaud11232.streamscraperreloaded;

import be.renaud11232.streamscraperreloaded.scraper.IceCastScraper;
import be.renaud11232.streamscraperreloaded.scraper.ShoutCast1Scraper;
import be.renaud11232.streamscraperreloaded.scraper.ShoutCast2Scraper;

import java.net.URI;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        System.out.println("IceCast");
        Scraper sc = new IceCastScraper();
        //List<Stream> streams = sc.scrape(new URI("http://109.123.110.250:8000/"));
        //List<Stream> streams = sc.scrape(new URI("http://stream.breddmedia.hu:8060/"));
        List<Stream> streams = sc.scrape(new URI("http://192.168.1.210:8000/"));
        for(Stream s : streams){
            System.out.println(s);
        }
        System.out.println("---------------------------------------------------");
        System.out.println("ShoutCast1");
        Scraper sc2 = new ShoutCast1Scraper();
        List<Stream> streams2 = sc2.scrape(new URI("http://92.222.38.225:2012/"));
        for(Stream s : streams2){
            System.out.println(s);
        }
        System.out.println("---------------------------------------------------");
        System.out.println("ShoutCast2");
        Scraper sc3 = new ShoutCast2Scraper();
        List<Stream> streams3 = sc3.scrape(new URI("http://192.168.1.211:8000"));
        for(Stream s : streams3){
            System.out.println(s);
        }
    }
}
