package org.jseek.requests;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.jseek.errors.NoRequestFoundException;
import org.jseek.response.IJSeekResponse;
import org.jseek.response.InfoResponse;

public class InfoRequest extends IJSeekRequest {


    public InfoRequest(MessageReceivedEvent event) {
        super(event);
    }

    @Override
    public IJSeekResponse generateResponse() throws NoRequestFoundException {
        return new InfoResponse(this.getEvent());
    }
}
