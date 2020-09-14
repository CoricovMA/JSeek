package org.jseek.jobs;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;

public class IndeedJob extends Job{

    public IndeedJob(String url) throws IOException {
        Document doc = Jsoup.connect(url).get();

        StringBuilder sb = new StringBuilder();
        sb.append(doc.select("div.jobDescriptionText").text(), 0, 1000);
        sb.append("...");

        this.title = doc.select("h1.jobsearch-JobInfoHeader-title").text();
        this.company = doc.select("div.jobsearch-InlineCompanyRating").text();
        this.salary = doc.select("div.jobsearch-JobMetadataHeader-item ").text();

        this.description = sb.toString();
        this.url = url;
    }

    public MessageEmbed getEmbed(){
        EmbedBuilder eb = new EmbedBuilder();

        eb.setTitle(this.title);
        eb.setAuthor(this.company);
        eb.setDescription(this.description);

        if(salary.strip().length() > 0) eb.addField("Salary", this.salary, true);

        eb.setFooter(url);
        
        return eb.build();
    }
}
