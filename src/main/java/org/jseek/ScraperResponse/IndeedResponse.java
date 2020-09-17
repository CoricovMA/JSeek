package org.jseek.ScraperResponse;

import net.dv8tion.jda.api.entities.MessageEmbed;
import org.apache.logging.log4j.LogManager;
import org.jseek.errors.JobNotDefinedError;
import org.jseek.jobs.IndeedJob;
import org.jseek.jobs.Job;
import org.jseek.jobs.JobStore;
import org.jseek.response.ResponseGetter;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Stream;

public class IndeedResponse extends ScraperResponse {

    private List<MessageEmbed> messages;
    private List<String> urls;
    private String url;
    private int numResponses;
    private JobStore.Parser parser = JobStore.Parser.INDEED;
    private final org.apache.logging.log4j.Logger logger = LogManager.getLogger(IndeedResponse.class);
    private int pagesToGet;

    private IndeedResponse(){
        this.messages = new ArrayList<>();
        this.pagesToGet = 5;
    }

    public IndeedResponse(List<String> urls) {
        this();
        this.urls = urls;
        execute();
    }

    public IndeedResponse(String url, int numResponses) {
        this();
        this.url = url;
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
        long execStart = System.currentTimeMillis();

        try {
            List<Element> elements = fetchElements();

            for(Element elem: elements){
                if(messages.size() >= numResponses) break;

                Job job = new IndeedJob(elem);
                if(!JobStore.getInstance().contains(parser, job)){
                    continue;
                }

                messages.add(job.getEmbed());
                JobStore.addJob(parser, job);
            }

        } catch (JobNotDefinedError e) {
            e.printStackTrace();
        }

        System.out.println(String.format("Adding jobs execution: %s",System.currentTimeMillis() - execStart));
    }

    private boolean checkSize(){
        return messages.size() >= this.numResponses;
    }

    private List<Element> fetchElements(){
        long execTime = System.currentTimeMillis();
        List<Element> elements = new ArrayList<>();
        ExecutorService executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(pagesToGet);
        List<Future<Elements>> futures = new ArrayList<>();
        String selector = "div.jobsearch-SerpJobCard";

        for(int i = 0; i < pagesToGet; i ++){
            try{
                long simpleGet = System.currentTimeMillis();
                String tempUrl = String.format("%s&start=%s", url, i*10);

                ResponseGetter getter = new ResponseGetter(tempUrl, selector);
                Future<Elements> result = executor.submit(getter);
                futures.add(result);

                System.out.println(String.format("Get request took: %s", System.currentTimeMillis()-simpleGet));

                simpleGet = System.currentTimeMillis();

                System.out.println(String.format("Adding elements took: %s", System.currentTimeMillis()-simpleGet));
            }catch (Exception e){
                logger.info("Error getting elements.");
            }

            futures.forEach(
                    elem -> {
                        try {
                            elements.addAll(elem.get());
                        } catch (InterruptedException | ExecutionException e) {
                            e.printStackTrace();
                        }
                    }
            );

            System.out.println(String.format("Total get time: %s", System.currentTimeMillis()-execTime));
            return elements;
        }

        System.out.println(String.format("Fetch elements execution: %s", (System.currentTimeMillis()-execTime)));
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
