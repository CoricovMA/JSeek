package org.jseek.response;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.jseek.config.JSeekConfig;
import org.jseek.requests.Request;

public abstract class Response {

    private MessageReceivedEvent event;
    private Request parentRequest;
    private EmbedBuilder eb = new EmbedBuilder();
    public static String [] availableCommands = (String []) JSeekConfig.getInstance().getProperties().get("commands");

    Response(MessageReceivedEvent event){
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

    public void setParentRequest(Request request){
        this.parentRequest = request;
    }

    public Request getParentRequest(){
        return this.parentRequest;
    }

    public EmbedBuilder getEmbedBuilder(){
        return this.eb;
    }

}
