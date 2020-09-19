package org.jseek.ScraperResponse;

import net.dv8tion.jda.api.entities.MessageEmbed;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jseek.errors.JobNotDefinedError;
import org.jseek.jobs.*;
import org.jseek.response.ResponseGetter;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import static java.lang.System.currentTimeMillis;

public class IndeedResponse extends ScraperResponse {
    private final Logger logger = LogManager.getLogger(IndeedResponse.class);

    private List<MessageEmbed> messages;
    private List<String> urls;

    private String url;
    private String requestedJob;
    private JobStore.Parser parser = JobStore.Parser.INDEED;

    private int pagesToGet;
    private int numResponses;

    private IndeedResponse(){
        this.messages = new ArrayList<>();
        this.pagesToGet = 5;
    }

    public IndeedResponse(List<String> urls) {
        this();
        this.urls = urls;
        execute();
    }

    public IndeedResponse(String url, int numResponses, String requestedJob) {
        this();
        this.url = url;
        this.numResponses = numResponses;
        this.requestedJob = requestedJob;
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
        long execStart = currentTimeMillis();

        try {
            List<Element> elements = fetchElements();
            List<Job> generatedJobs = new ArrayList<>();
            for(Element elem: elements){
                if(messages.size() >= numResponses) break;

                Job job = new IndeedJob(elem);
                if(!JobStore.getInstance().contains(parser, job)){
                    continue;
                }

                messages.add(job.getEmbed());
                JobStore.addJob(parser, job);
                generatedJobs.add(job);
            }



        } catch (JobNotDefinedError e) {
            e.printStackTrace();
        }

        System.out.println(String.format("Adding jobs execution: %s", currentTimeMillis() - execStart));
    }

    private boolean checkSize(){
        return messages.size() >= this.numResponses;
    }

    private List<Element> fetchElements(){
        long execTime = currentTimeMillis();
        List<Element> elements = new ArrayList<>();
        ExecutorService executor = Executors.newFixedThreadPool(pagesToGet);
        List<Future<Elements>> futures = new ArrayList<>();
        String selector = "div.jobsearch-SerpJobCard";

        long loopTime = currentTimeMillis();
        for(int i = 0; i < pagesToGet; i ++){
            try{
                String tempUrl = String.format("%s&start=%s", url, i*10);

                ResponseGetter getter = new ResponseGetter(tempUrl, selector);
                Future<Elements> result = executor.submit(getter);
                futures.add(result);

            }catch (Exception e){
                logger.info("Error getting elements. URL: {}", url);
            }

        }

        logger.info(String.format("Loop time: %s", (currentTimeMillis()-loopTime)));


        loopTime = currentTimeMillis();
        futures.forEach(
                elem -> {
                    try {
                        elements.addAll(elem.get());
                    } catch (InterruptedException | ExecutionException e) {
                        e.printStackTrace();
                    }
                }
        );

        logger.info(String.format("Features adding time: %s", (currentTimeMillis() - loopTime)));

        logger.info(String.format("Fetch elements execution: %s", (currentTimeMillis()-execTime)));
        return elements;
    }

    private void executeMulti() {
        for (String elem : urls) {
            try {
                IndeedJob job = new IndeedJob(elem);
                messages.add(job.getEmbed());
            } catch (IOException e) {
                logger.info("Error with Indeed response execute().");
            }
        }
    }

    public List<MessageEmbed> getJobResponse() {
        return this.messages;
    }

    public void setPagesToGet(int pages){
        this.pagesToGet = pages;
    }
}
