package org.jseek;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jseek.jobs.JobStore;
import org.jseek.listeners.*;

import javax.security.auth.login.LoginException;
import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioSystem;
import java.io.IOException;

public class App extends ListenerAdapter {

    private JobStore jobStore = JobStore.getInstance();

    public static void main( String[] args ) throws IOException {
        startBot();
//        for(AudioFileFormat.Type t : AudioSystem.getAudioFileTypes()){
//            System.out.println(t);
//        }
    }

    public static void startBot(){
        System.out.println(System.getenv("DISC_BOT_KEY"));

        try {
            JDA jda = JDABuilder.createDefault(System.getenv("DISC_BOT_KEY"))
                    .addEventListeners(new MessageListeners())
                    .build();
        } catch (LoginException e) {
            e.printStackTrace();e.printStackTrace();
        }
    }
}
