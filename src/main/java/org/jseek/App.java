package org.jseek;

import net.dv8tion.jda.api.AccountType;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.GenericEvent;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.EventListener;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jseek.listeners.MessageListeners;

import javax.annotation.Nonnull;
import javax.security.auth.login.LoginException;

/**
 * Hello world!
 *
 */
public class App extends ListenerAdapter {
    public static void main( String[] args )
    {
        try {
            JDA jda = JDABuilder.createDefault("NzQ3Nzk2OTA3OTM3NjI4MjIx.X0UGJA.IJ2_W9kY6r9LjqSgvFrW1w_WuQY")
                    .addEventListeners(new MessageListeners())
                    .build();
        } catch (LoginException e) {
            e.printStackTrace();e.printStackTrace();
        }


    }


}
