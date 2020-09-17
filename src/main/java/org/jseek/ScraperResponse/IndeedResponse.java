package org.jseek.ScraperResponse;

import net.dv8tion.jda.api.entities.MessageEmbed;
import org.jseek.errors.JobNotDefinedError;
import org.jseek.jobs.Job;
import org.jseek.jobs.JobStore;
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
    private int numResponses;
    private JobStore.Parser parser = JobStore.Parser.INDEED;

    public IndeedResponse(List<String> urls) {
        this.urls = urls;
        this.messages = new ArrayList<>();
        execute();
    }

    public IndeedResponse(String url, int numResponses) {
        this.url = url;
        this.messages = new ArrayList<>();
        this.numResponses = numResponses;
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
            for(Element elem: jobs){
                Job job = new IndeedJob(elem);
                if(JobStore.getInstance().contains(parser, job)){
                    continue;
                }
                messages.add(job.getEmbed());
                JobStore.addJob(parser, job);

            }
            for(int i = 0; i < numResponses; i++){
                Job job = new IndeedJob(jobs.get(i));
                if(!JobStore.getInstance().contains(parser, job)){
                    messages.add(job.getEmbed());
                    JobStore.addJob(parser, job);
                }else{
                    i--;
                }
            }

        } catch (IOException | JobNotDefinedError e) {
            e.printStackTrace();
        }

    }

    private boolean checkSize(){
        return messages.size() >= this.numResponses;
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
