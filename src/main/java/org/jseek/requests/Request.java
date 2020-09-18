package org.jseek.requests;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.jseek.errors.NoRequestFoundException;
import org.jseek.response.Response;

public abstract class Request {

    private MessageReceivedEvent event;
    private Response response;
    private String [] elements;

    Request(MessageReceivedEvent event){
        this.event = event;
    }

    public Response generateResponse() throws NoRequestFoundException {
        throw new NoRequestFoundException();
    }

    public MessageReceivedEvent getEvent(){
        return this.event;
    }

    protected void setResponse(Response response){
        this.response = response;
    }

    protected void setElements(String [] elements){
        this.elements = elements;
    }

    protected String [] getElements(){
        return this.elements;
    }

}
