package org.jseek.ScraperResponse;

import net.dv8tion.jda.api.entities.MessageEmbed;
import org.jseek.log.Logger;
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

    @Override
    protected void execute(){
        for(String elem: urls){
            try {
                IndeedJob job = new IndeedJob(elem);
                messages.add(job.getEmbed());
            } catch (IOException e) {
                Logger.getInstance().log("Error with Indeed response execute().");
            }
        }
    }

    public List<MessageEmbed> getJobResponse(){
        return this.messages;
    }
}
