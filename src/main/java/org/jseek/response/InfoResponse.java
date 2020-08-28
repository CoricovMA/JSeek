package org.jseek.response;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.jseek.config.JSeekConfig;

public class InfoResponse extends IJSeekResponse {

    private InfoRequestType reqType;

    public enum InfoRequestType{
        SIMPLE,
        SPECIFIC
    }

    public InfoResponse(MessageReceivedEvent event) {
        super(event);
    }

    @Override
    public void send() {
        if(this.reqType == InfoRequestType.SIMPLE){
            sendSimpleResponse();
        }else if(this.reqType == InfoRequestType.SPECIFIC){
            sendSpecificRequest();
        }
    }

    public void setCommandInfoResponse(String commandInfo){
    }

    private void sendSimpleResponse(){
        StringBuilder sb = new StringBuilder();
        String [] availableCommands = (String [])JSeekConfig.getInstance().getProperties().get("commands");
        sb.append("Available Commands: \n");

        for(int i = 0; i < availableCommands.length; i++){
            sb.append(String.format("%s %s",i ,availableCommands[i]));
        }

        this.getEvent().getChannel().sendMessage(sb.toString()).queue();
    }

    private void sendSpecificRequest(){

    }

    public void setReqType(InfoRequestType reqType){

    }

}
