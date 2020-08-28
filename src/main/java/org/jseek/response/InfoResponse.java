package org.jseek.response;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class InfoResponse extends IJSeekResponse {

    public enum InfoRequestType{
        SIMPLE,
        SPECIFIC
    }

    public InfoResponse(MessageReceivedEvent event) {
        super(event);
    }

    @Override
    public void send() {
        this.getEvent().getChannel().sendMessage("").queue();
    }

    public void setCommandInfoResponse(String commandInfo){
    }

    private void checkBasicRequest(){

    }




}
