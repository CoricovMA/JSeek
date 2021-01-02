package org.jseek;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jseek.jobs.JobStore;
import org.jseek.listeners.MessageListeners;

import javax.security.auth.login.LoginException;
import java.io.IOException;

public class App extends ListenerAdapter {

    private JobStore jobStore = JobStore.getInstance();

    public static void main( String[] args ) throws IOException {
        startBot();
    }

    public static void startBot(){
        try {
            JDA jda = JDABuilder.createDefault(System.getenv("DISC_BOT_KEY"))
                    .addEventListeners(new MessageListeners())
                    .build();
        } catch (LoginException e) {
            e.printStackTrace();e.printStackTrace();
        }
    }
}
