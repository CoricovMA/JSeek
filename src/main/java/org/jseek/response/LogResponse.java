package org.jseek.response;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class LogResponse extends Response {

    public LogResponse(MessageReceivedEvent event) {
        super(event);
    }

    @Override
    public void send() {

    }
}
