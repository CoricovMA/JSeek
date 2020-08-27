package org.jseek.requests;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.jseek.response.IJSeekResponse;

public class JobRequest extends IJSeekRequest {

    public JobRequest(MessageReceivedEvent event){
        super(event);
    }

    @Override
    public IJSeekResponse generateResponse() {
        return null;
    }
}
