package org.jseek.ScraperResponse;

import net.dv8tion.jda.api.entities.MessageEmbed;

import java.util.ArrayList;
import java.util.List;

public class IndeedResponse extends ScraperResponse {

    private List<MessageEmbed> messages;
    private List<String> urls;

    public IndeedResponse(List<String> urls){
        super();
        this.urls = urls;
        this.messages = new ArrayList<>();
        execute();
    }

    private void execute(){
        for(String elem: urls){

        }
    }
}
