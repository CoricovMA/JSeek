package org.jseek.listeners;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jseek.errors.NoRequestFoundException;
import org.jseek.requests.Request;
import org.jseek.requests.RequestFactory;

public class MessageListeners extends ListenerAdapter {

    private static final Logger logger = LogManager.getLogger();

    @Override
    public void onMessageReceived(MessageReceivedEvent event){
        logger.info("Got message {}", event.getMessage());

        if(event.getAuthor().isBot()){
            return;
        }

        Request request = RequestFactory.createRequest(event);

        try {
            assert request != null;
            request.generateResponse().send();
        } catch (NoRequestFoundException | NullPointerException e) {
           logger.warn("{} said {}", event.getAuthor().getName(), event.getMessage().getContentRaw());
        }

    }

}
