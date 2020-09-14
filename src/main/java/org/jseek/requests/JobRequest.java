package org.jseek.requests;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.jseek.response.IJSeekResponse;
import org.jseek.response.JobResponse;


/**
 * jseek job "job title" "location" "num"
 */

public class JobRequest extends IJSeekRequest {

    private String requestedJob;
    private String location;
    private IJSeekResponse response;
    private int numResults;


    public JobRequest(MessageReceivedEvent event){
        super(event);
        this.setElements(this.getEvent().getMessage().getContentRaw().split("\""));
    }

    @Override
    public IJSeekResponse generateResponse() {
        this.setResponse(new JobResponse(this.getEvent()));
        this.getResponse().setParentRequest(this);


        parseRequest();
        return this.response;
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
            this.numResults = Integer.parseInt(this.getElements()[4]);
        }else{
            this.numResults = 1;
        }
    }

    private void setRequestedJob(){
        this.requestedJob = this.getElements()[1];
    }

    private void setLocation(){
        if(this.getElements().length >= 3){
            this.location = this.getElements()[3];
        }else{
            this.location = "montreal";
        }
    }

    private boolean validateReq(){
        return getElements()[0].trim().equalsIgnoreCase("jseek job");
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
