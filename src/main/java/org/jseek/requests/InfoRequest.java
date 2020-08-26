package org.jseek.requests;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.jseek.response.IJSeekResponse;

public class InfoRequest implements IJSeekRequest {

    public InfoRequest(MessageReceivedEvent event) {
    }

    @Override
    public void createRequest() {

    }

    @Override
    public IJSeekResponse generateResponse() {
        return null;
    }

}
