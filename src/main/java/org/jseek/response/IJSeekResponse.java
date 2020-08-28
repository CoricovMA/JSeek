package org.jseek.response;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.jseek.requests.IJSeekRequest;

public abstract class IJSeekResponse {

    private MessageReceivedEvent event;
    private IJSeekRequest parentRequest;
    private EmbedBuilder eb = new EmbedBuilder();

    IJSeekResponse(MessageReceivedEvent event){
        this.event = event;
    }

    public void send() {
        event.getChannel().sendMessage("This command has not yet been implemented.").queue();
    }

    protected MessageReceivedEvent getEvent(){
        return this.event;
    }

    protected void setEvent(MessageReceivedEvent event){
        this.event = event;
    }

    public void setParentRequest(IJSeekRequest request){
        this.parentRequest = request;
    }

    public IJSeekRequest getParentRequest(){
        return this.parentRequest;
    }

    public EmbedBuilder getEmbedBuilder(){
        return this.eb;
    }
}
