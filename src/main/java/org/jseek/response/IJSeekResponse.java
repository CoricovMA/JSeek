package org.jseek.response;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public abstract class IJSeekResponse {

    private MessageReceivedEvent event;

    IJSeekResponse(MessageReceivedEvent event){
        this.event = event;
    }

    public void send() {
        event.getChannel().sendMessage("This send method has not yet been implemented").queue();
    }

}
