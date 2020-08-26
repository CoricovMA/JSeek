package org.jseek.listeners;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class MessageListeners extends ListenerAdapter {

    @Override
    public void onMessageReceived(MessageReceivedEvent event){
        if(isSelf(event)){
            return;
        }
        Message msg = event.getMessage();
        MessageChannel channel = event.getChannel();
        channel.sendMessage("leggo").queue();
//        if(event.getAuthor().getName().equalsIgnoreCase("mando") || event.getAuthor().getName().equalsIgnoreCase("hydroo")){
//            channel.sendMessage(String.format("@%s, bro. Get a job.", event.getAuthor().getName())).queue();
//        }else{
//            System.out.println(msg.getContentRaw());
//        }
    }


    private boolean isSelf(MessageReceivedEvent event){
        return event.getAuthor().getName().equalsIgnoreCase("JSeek");
    }
}
