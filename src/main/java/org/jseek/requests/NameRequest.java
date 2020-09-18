package org.jseek.requests;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.jseek.response.Response;

public class NameRequest extends Request {


    NameRequest(MessageReceivedEvent event) {
        super(event);
    }

    @Override
    public Response generateResponse() {
        return null;
    }
}
