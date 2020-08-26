package org.jseek.requests;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.jseek.errors.NoRequestFoundException;

public class RequestFactory {

    public static IJSeekRequest createRequest(MessageReceivedEvent event){
        String requestType;
        try{
            requestType = getRequestType(event.getMessage().getContentRaw());
        }catch (NoRequestFoundException e){
            event.getChannel().sendMessage(e.getMessage());
            return null;
        }

        switch(requestType){
            case "job":
                return new JobRequest(event);
            case "info":
                return new InfoRequest(event);
            default:
                return null;
        }

    }

    private static String getRequestType(String messageString) throws NoRequestFoundException {
        String [] elements = messageString.split(" ");
        if(elements[0].equalsIgnoreCase("jseek")){
            return elements[1];
        }else{
            throw new NoRequestFoundException();
        }
    }
}
