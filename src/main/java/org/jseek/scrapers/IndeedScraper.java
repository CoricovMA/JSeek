package org.jseek.scrapers;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import org.jseek.ScraperResponse.IndeedResponse;
import org.jseek.ScraperResponse.ScraperResponse;
import org.jseek.requests.JobRequest;
import org.jseek.response.IJSeekResponse;
import org.jseek.util.Util;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * FLOW
 * request -> parser -> scraperResponse -> setScraperResponse in response
 */
public class IndeedScraper implements SeekScraper {

    private ScraperResponse scraperResponse;
    private String givenUrl;
    private List<String> urls = new ArrayList<>();
    private String baseUrl = "https://ca.indeed.com";
    private JobRequest request;
    private long queryStart = System.currentTimeMillis();

    @Override
    public void execute(JobRequest request) throws IOException {
        this.request = request;
        givenUrl = String.format("https://ca.indeed.com/jobs?q=%s&l=%s",
                request.getRequestedJob(),
                request.getLocation());
        givenUrl = Util.checkUrl(givenUrl);
        System.out.println(givenUrl);
        this.scraperResponse = new IndeedResponse(givenUrl, this.request.getNumResults());
    }

    @Override
    public ScraperResponse retrieveResponses() {
        return this.scraperResponse;
    }

    /**
     * Old method
     */
    private void getUrls(){
        int page = 0;
        while(true){

            String tempUrl = String.format("%s&start=%o", givenUrl, (page*10));
            Document doc;

            try{

                doc = Jsoup.connect(tempUrl).get();
                Elements jobs = doc.select("div.jobsearch-SerpJobCard");

                for(Element element: jobs){
                    urls.add(String.format("%s%s",
                            baseUrl,
                            element.select("a.jobtitle").attr("href")));
                    if(checkAmount()) break;
                }

                if(checkAmount()) break;
                page++;
            }catch (Exception e){
                break;
            }
        }
    }

    private boolean checkAmount(){
        return urls.size() >= this.request.getNumResults();
    }

}
