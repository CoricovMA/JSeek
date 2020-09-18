package org.jseek.requests;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.jseek.errors.NoRequestFoundException;
import org.jseek.response.Response;
import org.jseek.response.VoiceResponse;

public class VoiceRequest extends Request {

    private String requestedAudioRes;

    public VoiceRequest(MessageReceivedEvent event) {
        super(event);
        setRequestedAudioRes();
    }

    private void setRequestedAudioRes(){
        this.requestedAudioRes = this.getEvent().getMessage().getContentRaw().replaceAll("!v", "").strip();
    }

    @Override
    public Response generateResponse() throws NoRequestFoundException {
        VoiceResponse response = new VoiceResponse(this.getEvent());
        response.setAudioResFile(this.requestedAudioRes);
        return response;
    }


}
