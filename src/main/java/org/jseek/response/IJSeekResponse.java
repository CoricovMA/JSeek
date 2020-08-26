package org.jseek.response;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public abstract class IJSeekResponse {


    void send(MessageReceivedEvent event) {
        event.getChannel().sendMessage("This send method has not yet been implemented").queue();
    }

}
