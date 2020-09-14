package org.jseek.ScraperResponse;

import net.dv8tion.jda.api.entities.MessageEmbed;

import java.util.ArrayList;
import java.util.List;

public abstract class ScraperResponse {

    protected List<MessageEmbed> jobResponse;

    protected ScraperResponse(){
        this.jobResponse = new ArrayList<>();
    }

    private void execute(){};

    public List<MessageEmbed> getJobResponse() {
        return jobResponse;
    }
}
