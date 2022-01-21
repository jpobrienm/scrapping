package co.com.sofka.scrapping.Infrastructure.Scrapper;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class Scrapper {

    private final Document document;

    public Scrapper(String url) throws IOException {
        this.document = Jsoup.connect(url).get();
    }

    public Document getDocument() {
        return document;
    }
}
