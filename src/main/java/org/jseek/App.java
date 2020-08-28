package org.jseek;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jseek.listeners.*;

import javax.security.auth.login.LoginException;
import java.awt.*;
import java.util.concurrent.ThreadLocalRandom;

public class App extends ListenerAdapter {
    public static void main( String[] args ) {

//        System.out.println(System.getenv("DISC_BOT_KEY"));
//
//        try {
//            JDA jda = JDABuilder.createDefault(System.getenv("DISC_BOT_KEY"))
//                    .addEventListeners(new MessageListeners())
//                    .build();
//        } catch (LoginException e) {
//            e.printStackTrace();e.printStackTrace();
//        }
        for(int i = 0; i < 20; i++){
            System.out.println(getRandColor());
        }

    }

    private static Color getRandColor(){
        ThreadLocalRandom thrd = ThreadLocalRandom.current();
        int first = thrd.nextInt(0, 256);
        int second = thrd.nextInt(0, 256);
        int third = thrd.nextInt(0, 256);

        return new Color(first, second, third);
    }
}
