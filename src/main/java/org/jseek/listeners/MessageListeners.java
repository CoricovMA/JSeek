package org.jseek.listeners;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jseek.requests.IJSeekRequest;
import org.jseek.requests.RequestFactory;
import org.jseek.response.IJSeekResponse;

public class MessageListeners extends ListenerAdapter {

    @Override
    public void onMessageReceived(MessageReceivedEvent event){
        if(event.getAuthor().isBot()){
            return;
        }

        IJSeekRequest request = RequestFactory.createRequest(event);
        IJSeekResponse response = request.generateResponse();

        MessageChannel channel = event.getChannel();

    }

}
