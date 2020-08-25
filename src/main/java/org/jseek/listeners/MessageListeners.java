package org.jseek.listeners;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class MessageListeners extends ListenerAdapter {

    @Override
    public void onMessageReceived(MessageReceivedEvent event){
        Message msg = event.getMessage();
        System.out.println(event.getMessage());
        System.out.println(msg.getContentRaw());
    }

}
