package org.jseek;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jseek.listeners.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.security.auth.login.LoginException;
import java.io.IOException;

public class App extends ListenerAdapter {
    public static void main( String[] args ) throws IOException {

//        System.out.println(System.getenv("DISC_BOT_KEY"));
//
//        try {
//            JDA jda = JDABuilder.createDefault(System.getenv("DISC_BOT_KEY"))
//                    .addEventListeners(new MessageListeners())
//                    .build();
//        } catch (LoginException e) {
//            e.printStackTrace();e.printStackTrace();
//        }

        String url = String.format("https://ca.indeed.com/jobs?q=%s&l=%s",
                "software dev intern",
                "montreal");
        Document doc = Jsoup.connect(url).get();
        Elements jobs = doc.select("div.jobsearch-SerpJobCard");
        for(Element element: jobs){
            System.out.println(element);
            System.out.println("\n");
            break;
        }
    }
}
