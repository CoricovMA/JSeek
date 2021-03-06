package org.jseek.jobs;

import net.dv8tion.jda.api.entities.MessageEmbed;
import org.jseek.errors.JobNotDefinedError;
import org.jsoup.nodes.Element;

public abstract class Job {

    protected String title;
    protected String company;
    protected String url;
    protected String salary;
    protected String description;
    protected Element element;
    protected long initTime;

    public MessageEmbed getEmbed() throws JobNotDefinedError {
        throw new JobNotDefinedError();
    }

    public boolean canUse(){
        return (System.currentTimeMillis() - initTime) >= 60000;
    }

}
