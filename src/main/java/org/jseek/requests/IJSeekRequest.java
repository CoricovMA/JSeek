package org.jseek.requests;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.jseek.response.IJSeekResponse;

public abstract class IJSeekRequest {

    IJSeekRequest(){

    }

    IJSeekResponse generateResponse() {
        return null;
    }

}
