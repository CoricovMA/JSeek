package org.jseek.jobs;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import org.jseek.util.Util;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;

public class IndeedJob extends Job{

    public IndeedJob(String url) throws IOException {
        Document doc = Jsoup.connect(url).get();

        StringBuilder sb = new StringBuilder(doc.select("div#jobDescriptionText").text());

        this.title = doc.select("h1.jobsearch-JobInfoHeader-title").text();
        this.company = doc.select("div.jobsearch-InlineCompanyRating").text();
        this.salary = doc.select("div.jobsearch-JobMetadataHeader-item ").text();

        setDescription(sb);
        this.url = url;
        this.initTime = System.currentTimeMillis();
    }

    public IndeedJob(Element elem){
        this.title = elem.select("h2.title").text().strip();
        this.company = elem.select("span.company").text().strip();
        this.salary = elem.select("span.salaryText").text().strip();
        this.url = String.format("https://ca.indeed.com%s", elem.select("a.jobtitle").attr("href"));
        this.description = elem.select("div.summary").text().strip();
        this.initTime = System.currentTimeMillis();
    }

    public MessageEmbed getEmbed(){
        EmbedBuilder eb = new EmbedBuilder();

        eb.setTitle(this.company, url);
        eb.setAuthor(this.title, url);
        eb.setDescription(this.description);
        eb.setColor(Util.getRandColor());
        if(salary.strip().length() > 0) eb.addField("Salary", this.salary, true);


        return eb.build();
    }

    private void setDescription(StringBuilder sb){
        if(sb.toString().length() > 200){
            this.description = sb.substring(0, 248) + "...";
        }else{
            this.description = sb.toString();
        }
    }
}
