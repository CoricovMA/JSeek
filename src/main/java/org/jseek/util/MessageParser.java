package org.jseek.util;

public class MessageParser {



    public MessageParser(String messageToParse){
    }

    private boolean isCommand(String message){
        return message.split(" ")[0].equalsIgnoreCase("jseek");
    }

}
