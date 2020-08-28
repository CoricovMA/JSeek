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
        sb.append("Available Commands: \n");

        setCommands(sb);

        sb.append("For more information on a command: ```jseek \"command\" help```For Example: " +
                "```jseek info help```");

        this.getEvent().getChannel().sendMessage(sb.toString()).queue();
    }

    private void sendSpecificRequest(){

    }

    public void setReqType(InfoRequestType reqType){
        this.reqType = reqType;
    }

    private void setCommands(StringBuilder stringBuilder){
        String [] availableCommands = (String [])JSeekConfig.getInstance().getProperties().get("commands");
        for(int i = 0; i < availableCommands.length; i++){
            stringBuilder.append(String.format("```%s: %s```",i+1 ,availableCommands[i]));
        }
    }


}
