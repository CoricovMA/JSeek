package org.jseek.scrapers;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import org.jseek.requests.JobRequest;
import org.jseek.response.IJSeekResponse;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * FLOW
 * request -> parser -> scraperResponse -> setScraperResponse in response
 */
public class IndeedScraper implements SeekScraper {

    private IJSeekResponse response;
    private String givenUrl;
    private List<String> urls = new ArrayList<>();

    @Override
    public void execute(JobRequest request) throws IOException {
        givenUrl = String.format("https://ca.indeed.com/jobs?q=%s&l=%s",
                request.getRequestedJob(),
                request.getLocation());
//        Document doc = Jsoup.connect(url).get();
//        Elements jobs = doc.select("div.jobsearch-SerpJobCard");
//        System.out.println(jobs);
    }

    @Override
    public IJSeekResponse retrieveResponses() {
        return null;
    }

    private static MessageEmbed messageEmbed(Document doc){
        EmbedBuilder eb = new EmbedBuilder();


        return eb.build();
    }

    private void getUrls() throws IOException {
        Document doc = Jsoup.connect(givenUrl).get();
        
        int pages;



    }
}
