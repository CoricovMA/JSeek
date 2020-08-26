package org.jseek.requests;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.jseek.response.IJSeekResponse;

public class JobRequest implements IJSeekRequest {

    public JobRequest(MessageReceivedEvent event){

    }

    @Override
    public void createRequest() {

    }

    @Override
    public IJSeekResponse generateResponse() {
        return null;
    }
}
