package org.jseek.requests;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.jseek.response.IJSeekResponse;

public interface IJSeekRequest {

    void createRequest();

    IJSeekResponse generateResponse();

}
