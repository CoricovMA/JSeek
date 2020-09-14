package org.jseek.requests;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.jseek.response.IJSeekResponse;
import org.jseek.response.LogResponse;

public class LogRequest extends IJSeekRequest {

    public LogRequest(MessageReceivedEvent event) {
        super(event);
    }

    @Override
    public IJSeekResponse generateResponse() {
        return new LogResponse(this.getEvent());
    }
}
