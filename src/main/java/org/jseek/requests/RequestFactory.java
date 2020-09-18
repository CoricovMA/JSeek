package org.jseek.requests;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.jseek.errors.NoRequestFoundException;

public class RequestFactory {

    public static Request createRequest(MessageReceivedEvent event){
        String requestType;
        try{
            requestType = event.getMessage().getContentRaw().split(" ")[0];
        }catch (Exception e){
            event.getChannel().sendMessage("Something went wrong.").queue();
            return null;
        }

        switch(requestType){
            case "!j":
            case "!job":
                return new JobRequest(event);
            case "!i":
            case "!info":
                return new InfoRequest(event);
            case "!l":
            case "!log":
            case "!logs":
                return new LogRequest(event);
            case "!v":
                return new VoiceRequest(event);
            default:
                return null;
        }

    }

    private static String getRequestType(String messageString) throws NoRequestFoundException {
        String [] elements = messageString.split(" ");
        if(elements[0].equalsIgnoreCase("jseek")){
            return elements[1].strip();
        }else{
            throw new NoRequestFoundException();
        }
    }
}
