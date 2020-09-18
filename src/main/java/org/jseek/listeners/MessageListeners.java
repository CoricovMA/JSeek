package org.jseek.listeners;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jseek.errors.NoRequestFoundException;
import org.jseek.requests.Request;
import org.jseek.requests.RequestFactory;

import java.sql.SQLOutput;

public class MessageListeners extends ListenerAdapter {

    @Override
    public void onMessageReceived(MessageReceivedEvent event){
        if(event.getAuthor().isBot()){
            return;
        }

        Request request = RequestFactory.createRequest(event);

        try {
            assert request != null;
            long queryStart = System.currentTimeMillis();
            request.generateResponse().send();
            event.getChannel().sendMessage(String.format("Query took %s ms",
                        (System.currentTimeMillis()-queryStart)/1000.0))
                    .queue();
        } catch (NoRequestFoundException | NullPointerException e) {
            System.out.println(String.format("%s said %s", event.getAuthor().getName(), event.getMessage().getContentRaw()));
        }

    }

}
