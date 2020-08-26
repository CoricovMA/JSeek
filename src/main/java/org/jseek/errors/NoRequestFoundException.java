package org.jseek.errors;

public class NoRequestFoundException extends Exception {

    public NoRequestFoundException(){
        super("No request detected in the message.");
    }

    public NoRequestFoundException(String message){
        super(message);
    }
}
