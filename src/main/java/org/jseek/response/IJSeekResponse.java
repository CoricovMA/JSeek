package org.jseek.response;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public interface IJSeekResponse {

    void send(MessageReceivedEvent event);

}
