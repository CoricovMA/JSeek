package org.jseek.requests;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.jseek.response.Response;
import org.jseek.response.JobResponse;
import org.jseek.scrapers.IndeedScraper;

import java.io.IOException;


/**
 * jseek job "job title" "location" "num"
 */

public class JobRequest extends Request {

    private String requestedJob;
    private String location;
    private IndeedScraper scraper;
    private JobResponse response;
    private int numResults;

    public JobRequest(MessageReceivedEvent event){
        super(event);
        this.setElements(this.getEvent().getMessage().getContentRaw().split("\""));
        this.scraper = new IndeedScraper();
    }

    @Override
    public Response generateResponse() {
        parseRequest();
        this.response = new JobResponse(this.getEvent());

        try {
            scraper.execute(this);
            scraper.retrieveResponses();
            response.setMessages(scraper.retrieveResponses().getJobResponse());

            return this.response;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    private void parseRequest() {
        if(validateReq()){
            setRequestedJob();
            setLocation();
            setNumResults();
        }

    }

    private void setNumResults() {
        if(this.getElements().length >= 4){
            this.numResults = Integer.parseInt(this.getElements()[3]);
        }else{
            this.numResults = 5;
        }
    }

    private void setRequestedJob(){
        this.requestedJob = this.getElements()[1];
    }

    private void setLocation(){
        if(this.getElements().length > 3){
            try{
                this.numResults = Integer.parseInt(this.getElements()[3]);
            }catch (Exception e){
                this.location = this.getElements()[3];
                if(this.location.strip().length() == 0){
                    this.location = "montreal";
                }
            }
        }
        this.location = "montreal";
    }

    private boolean validateReq(){
        String str = getElements()[0].trim();
        return str.equalsIgnoreCase("!job") || str.equalsIgnoreCase("!j");
    }


    public String getRequestedJob() {
        return requestedJob;
    }

    public String getLocation() {
        return location;
    }

    public int getNumResults(){
        return this.numResults;
    }
}
