package org.jseek.listeners;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jseek.errors.Logger;
import org.jseek.errors.NoRequestFoundException;
import org.jseek.requests.IJSeekRequest;
import org.jseek.requests.RequestFactory;

public class MessageListeners extends ListenerAdapter {

    @Override
    public void onMessageReceived(MessageReceivedEvent event){
        if(event.getAuthor().isBot()){
            return;
        }

        IJSeekRequest request = RequestFactory.createRequest(event);

        try {
            assert request != null;
            request.generateResponse().send();
        } catch (NoRequestFoundException e) {

        } catch (NullPointerException npe){
            Logger.getInstance().log("Error thrown while handling message received.");
        }

    }

}
