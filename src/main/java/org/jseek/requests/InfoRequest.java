package org.jseek.requests;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.jseek.config.JSeekConfig;
import org.jseek.errors.NoRequestFoundException;
import org.jseek.response.IJSeekResponse;
import org.jseek.response.InfoResponse;

public class InfoRequest extends IJSeekRequest {

    private InfoResponse infoResponse;

    public InfoRequest(MessageReceivedEvent event) {
        super(event);
    }

    @Override
    public IJSeekResponse generateResponse() throws NoRequestFoundException {
        infoResponse = new InfoResponse(this.getEvent());
        parseRequestType();

        return infoResponse;
    }

    private void parseRequestType(){
        String [] elements = getEvent().getMessage().getContentRaw().split(" ");
        if(elements.length == 2){
            infoResponse.setReqType(InfoResponse.InfoRequestType.SIMPLE);
        }else{
            String potentialCommand = elements[2];
            for(String command : (String[]) JSeekConfig.getInstance().getProperties().get("commands")){
                if(potentialCommand.equalsIgnoreCase(command)){
                    infoResponse.setReqType(InfoResponse.InfoRequestType.SPECIFIC);
                    break;
                }
            }
        }
    }

}
