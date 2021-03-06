package org.jseek.requests;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.jseek.config.JSeekConfig;
import org.jseek.errors.NoRequestFoundException;
import org.jseek.response.Response;
import org.jseek.response.InfoResponse;

public class InfoRequest extends Request {

    private InfoResponse infoResponse;
    private String [] elements;

    public InfoRequest(MessageReceivedEvent event) {
        super(event);
    }

    @Override
    public Response generateResponse() throws NoRequestFoundException {
        infoResponse = new InfoResponse(this.getEvent());
        infoResponse.setParentRequest(this);
        parseRequestType();

        return infoResponse;
    }

    private void parseRequestType(){
        elements = getEvent().getMessage().getContentRaw().split(" ");
        if(elements.length == 1){

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

    public String[] getElements(){
        return this.elements;
    }

}
