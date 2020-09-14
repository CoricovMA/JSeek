package org.jseek.errors;

import java.awt.desktop.SystemEventListener;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Logger {

    private class Log{

        private Date date;
        private String message;

        public Log(String message, Date date){
            this.message = message;
            this.date = date;
        }

        @Override
        public String toString() {
            return "Log{" +
                    "date=" + date +
                    ", message='" + message + '\'' +
                    '}';
        }
    }


    private static Logger instance;
    private static List<Log> logs;
    private static long initTime;

    private Logger(){
        logs = new ArrayList<>();
        initTime = System.currentTimeMillis();
    }

    public static Logger getInstance(){
        wipeLogs();
        if(instance == null){
            instance = new Logger();
        }

        return instance;
    }

    public List<String> getLogs(){
        List<String> logsAsString = new ArrayList<>();
        for(Log log: logs){
            logsAsString.add(log.toString());
        }

        return logsAsString;
    }


    private static void wipeLogs(){
        if((System.currentTimeMillis() - initTime) > 3600000){
            initTime = System.currentTimeMillis();
            logs = new ArrayList<>();
        }
    }

    public void log(String message){
        Log log = new Log(message, Date.from(Instant.now()));
        logs.add(log);
        System.gc();
    }

}
