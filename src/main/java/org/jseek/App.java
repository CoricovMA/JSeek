package org.jseek;

import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jseek.config.JSeekConfig;

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

        for(String key: JSeekConfig.getInstance().getProperties().keySet()){
            System.out.println(key);
            System.out.println(JSeekConfig.getInstance().getProperties().get(key));
        }
    }
}
