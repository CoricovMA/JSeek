package org.jseek.scrapers;

import org.jseek.requests.JobRequest;
import org.jseek.response.IJSeekResponse;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;


/**
 * FLOW
 * request -> parser -> scraperResponse -> setScraperResponse in response
 */
public class IndeedScraper implements SeekScraper{

    @Override
    public void execute(JobRequest request) throws IOException {
        String url = String.format("https://ca.indeed.com/jobs?q=%s&l=%s",
                request.getRequestedJob(),
                request.getLocation());
        Document doc = Jsoup.connect(url).get();
        Elements jobs = doc.select("div.jobsearch-SerpJobCard");
        System.out.println(jobs);
    }

    @Override
    public IJSeekResponse[] retrieveResponses() {
        return new IJSeekResponse[0];
    }
}
