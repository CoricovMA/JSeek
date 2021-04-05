package org.jseek;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jseek.jobs.JobStore;
import org.jseek.listeners.MessageListeners;

import javax.security.auth.login.LoginException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class App extends ListenerAdapter {

    private JobStore jobStore = JobStore.getInstance();
    private static final String key = fetchKey();

    private static String fetchKey() {
        try {
            return Files.readString(Paths.get("main.env"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static void main( String[] args ) throws IOException {
        startBot();
    }

    public static void startBot(){
        try {
            JDA jda = JDABuilder.createDefault(key)
                    .addEventListeners(new MessageListeners())
                    .build();
        } catch (LoginException e) {
            e.printStackTrace();e.printStackTrace();
        }
    }
}
