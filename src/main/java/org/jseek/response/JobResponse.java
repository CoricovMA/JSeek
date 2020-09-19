package org.jseek.response;

import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.ArrayList;
import java.util.List;

public class JobResponse extends Response {

    private List<MessageEmbed> messageEmbedList;

    public JobResponse(MessageReceivedEvent event) {
        super(event);
        this.messageEmbedList = new ArrayList<>();
    }

    public void setMessages(List<MessageEmbed> messages){
        this.messageEmbedList = messages;
    }

    public void send(){
        long execStart = System.currentTimeMillis();
        for(MessageEmbed embed: messageEmbedList){
            this.getEvent()
                    .getMessage()
                    .getAuthor()
                    .openPrivateChannel()
                    .flatMap(
                            privateChannel -> privateChannel.sendMessage(embed))
                    .queue();
        }

        System.out.println(String.format("Message send time: %s", System.currentTimeMillis()-execStart));
    }


}
