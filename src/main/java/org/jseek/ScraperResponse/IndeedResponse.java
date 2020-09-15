package org.jseek.ScraperResponse;

import net.dv8tion.jda.api.entities.MessageEmbed;
import org.jseek.jobs.Job;
import org.jseek.log.Logger;
import org.jseek.jobs.IndeedJob;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class IndeedResponse extends ScraperResponse {

    private List<MessageEmbed> messages;
    private List<String> urls;
    private String url;

    public IndeedResponse(List<String> urls) {
        this.urls = urls;
        this.messages = new ArrayList<>();
        execute();
    }

    public IndeedResponse(String url, int numResponse) {
        this.url = url;
        this.messages = new ArrayList<>();
        execute();
    }

    @Override
    protected void execute() {
        if (urls == null && url != null) {
            executeSingles();
        } else if (urls != null && url == null) {
            executeMulti();
        }
    }

    private void executeSingles() {
        Document doc;
        try {
            doc = Jsoup.connect(url).get();
            Elements jobs = doc.select("div.jobsearch-SerpJobCard");
            for(Element ele: jobs){
                IndeedJob j = new IndeedJob(ele);
                messages.add(j.getEmbed());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void executeMulti() {
        for (String elem : urls) {
            try {
                IndeedJob job = new IndeedJob(elem);
                messages.add(job.getEmbed());
            } catch (IOException e) {
                Logger.getInstance().log("Error with Indeed response execute().");
            }
        }
    }

    public List<MessageEmbed> getJobResponse() {
        return this.messages;
    }
}
