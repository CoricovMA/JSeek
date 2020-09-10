package org.jseek.requests;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.jseek.errors.NoRequestFoundException;
import org.jseek.response.IJSeekResponse;

public abstract class IJSeekRequest {

    private MessageReceivedEvent event;
    private IJSeekResponse response;
    private String [] elements;

    IJSeekRequest(MessageReceivedEvent event){
        this.event = event;
    }

    public IJSeekResponse generateResponse() throws NoRequestFoundException {
        throw new NoRequestFoundException();
    }

    public MessageReceivedEvent getEvent(){
        return this.event;
    }

    protected void setResponse(IJSeekResponse response){
        this.response = response;
    }

    protected IJSeekResponse getResponse(){
        return this.response;
    }

    protected void setElements(String [] elements){
        this.elements = elements;
    }

    protected String [] getElements(){
        return this.elements;
    }

}
