package org.jseek.requests;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.jseek.response.IJSeekResponse;
import org.jseek.response.JobResponse;

public class JobRequest extends IJSeekRequest {

    private String requestedJob;
    private String location;

    public JobRequest(MessageReceivedEvent event){
        super(event);
        this.setElements(this.getEvent().getMessage().getContentRaw().split("\""));
    }

    @Override
    public IJSeekResponse generateResponse() {
        this.setResponse(new JobResponse(this.getEvent()));
        this.getResponse().setParentRequest(this);



        parseRequest();
        return null;
    }

    private void parseRequest() {
        if(validateReq()){
            setRequestedJob();
            setLocation();
        }

    }

    private void setRequestedJob(){
        this.requestedJob = this.getElements()[1];
    }

    private void setLocation(){
        if(this.getElements().length > 3){
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
}
