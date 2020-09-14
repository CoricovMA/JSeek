package org.jseek.ScraperResponse;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import org.jseek.errors.Logger;
import org.jseek.jobs.IndeedJob;

import java.io.IOException;
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
            try {
                IndeedJob job = new IndeedJob(elem);
            } catch (IOException e) {
                Logger.getInstance().log("Error with Indeed response execute().");
            }
        }
    }
}
