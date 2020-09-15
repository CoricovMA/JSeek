package org.jseek.response;

import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO
 * 1.
 */
public class JobResponse extends IJSeekResponse{

    private List<MessageEmbed> messageEmbedList;

    public JobResponse(MessageReceivedEvent event) {
        super(event);
        this.messageEmbedList = new ArrayList<>();
    }

    public void setMessages(List<MessageEmbed> messages){
        this.messageEmbedList = messages;
    }

    public void send(){
        for(MessageEmbed embed: messageEmbedList){
            this.getEvent().getChannel().sendMessage(embed).queue();
        }
    }


}
