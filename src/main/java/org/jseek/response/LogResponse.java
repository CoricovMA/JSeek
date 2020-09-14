package org.jseek.response;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.jseek.errors.Logger;

public class LogResponse extends IJSeekResponse {

    public LogResponse(MessageReceivedEvent event) {
        super(event);
    }

    @Override
    public void send() {
        StringBuilder sb = new StringBuilder();
        if(Logger.getInstance().getLogs().size() > 0) {
            sb.append("```");
            for (String log : Logger.getInstance().getLogs()) {
                sb.append(log);
                sb.append("\n");
            }
            sb.append("```");
        }else{
            sb.append("```No logs available.```");
        }

        this.getEvent().getChannel().sendMessage(sb.toString()).queue();
    }
}
