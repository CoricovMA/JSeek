package org.jseek.jobs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JobStore {

    private static JobStore instance;
    private static Map<Parser, List<String>> store;

    public enum Parser{
        INDEED,
        GOOGLE;
    }

    private JobStore(){
        store = new HashMap<>();
        store.put(Parser.INDEED, new ArrayList<>());
    }

    public static JobStore getInstance(){
        if(instance == null){
            instance = new JobStore();
        }

        return instance;
    }

    public static void addJob(Parser parser, Job job){
        store.get(parser).add(job.title);
    }

    public boolean contains(Parser parser, Job job){
        boolean has = store.get(parser).contains(job.title);
        boolean timedOut = (System.currentTimeMillis() - job.initTime) > 600000;

        if(has && timedOut) {
            return false;
        }else if(!has){
            return true;
        }else {
            store.get(parser).remove(job.title);
            return false;
        }
        
    }

}
