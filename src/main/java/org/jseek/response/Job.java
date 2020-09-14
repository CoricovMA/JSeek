package org.jseek.response;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;

public class Job {
    private String title;
    private String company;
    private String url;
    private String salary;
    private String description;
    private Element element;

    public Job(String url) throws IOException {
        Document doc = Jsoup.connect(url).get();

        this.title = doc.select("h1.jobsearch-JobInfoHeader-title").text();
        

    }

}
