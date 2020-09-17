package org.jseek.response;

import org.jseek.ScraperResponse.IndeedResponse;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.concurrent.Callable;

public class ResponseGetter implements Callable<Elements> {

    private final String url;
    private final String selectorString;

    public ResponseGetter(String url, String selectorString) {
        this.url = url;
        this.selectorString = selectorString;
    }

    @Override
    public Elements call() throws Exception {
        Document doc = Jsoup.connect(url).get();
        return doc.select(selectorString);
    }
}
