package org.jseek;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jseek.listeners.*;

import javax.security.auth.login.LoginException;

public class App extends ListenerAdapter {
    public static void main( String[] args ) {
//
//        System.out.println(System.getenv("DISC_BOT_KEY"));
//
//        try {
//            JDA jda = JDABuilder.createDefault(System.getenv("DISC_BOT_KEY"))
//                    .addEventListeners(new MessageListeners())
//                    .build();
//        } catch (LoginException e) {
//            e.printStackTrace();e.printStackTrace();
//        }
//
        String s = "jseek job \"yo mama\" \"location\"";
        for(String elem: s.split("\"")){
            if(!elem.equalsIgnoreCase(" ")){
                System.out.println(elem);
            }
        }
    }
}
