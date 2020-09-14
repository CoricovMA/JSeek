package org.jseek.response;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.jseek.config.JSeekConfig;
import org.jseek.util.Util;

import java.awt.*;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

public class InfoResponse extends IJSeekResponse {

    private InfoRequestType reqType;
    private static final String commandName = "Info";

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

    private void sendSimpleResponse(){
        EmbedBuilder eb = this.getEmbedBuilder();
        eb.setAuthor("JSeek");
        eb.setColor(Util.getRandColor());
        setCommandsEmbed(eb);
        setFields(eb);
        eb.addBlankField(false);
        eb.setFooter(String.format("%s Command", commandName));
        this.getEvent().getChannel().sendMessage(eb.build()).queue();

    }

    private void sendSpecificRequest(){

    }

    private void setFields(EmbedBuilder eb){
        Map properties = JSeekConfig.getInstance().getProperties();
        for(String command: availableCommands){
            eb.addField(command, (String)properties.get(command), true );
        }
    }

    public void setReqType(InfoRequestType reqType){
        this.reqType = reqType;
    }

    private void setCommandsEmbed(EmbedBuilder eb){
        StringBuilder sb = new StringBuilder();
        sb.append("```");
        for (String availableCommand : availableCommands) {
            sb.append(String.format("- %s\n", availableCommand));
        }
        sb.append("```");
        eb.setDescription(sb.toString());
    }

    private void setCommands(StringBuilder stringBuilder){
        for(int i = 0; i < availableCommands.length; i++){
            stringBuilder.append(String.format("```%s: %s```",i+1 ,availableCommands[i]));
        }
    }

}
