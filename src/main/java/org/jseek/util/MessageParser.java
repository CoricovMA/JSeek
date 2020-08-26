package org.jseek.util;

public class MessageParser {

    private boolean isCommand(String message){
        return message.split(" ")[0].equalsIgnoreCase("jseek");
    }

}
