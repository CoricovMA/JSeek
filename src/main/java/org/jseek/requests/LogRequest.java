package org.jseek.requests;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.jseek.response.Response;
import org.jseek.response.LogResponse;

public class LogRequest extends Request {

    public LogRequest(MessageReceivedEvent event) {
        super(event);
    }

    @Override
    public Response generateResponse() {
        return new LogResponse(this.getEvent());
    }
}
