package org.jseek.jobs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JobStore {

    private static JobStore instance;
    private static Map<String, List<String>> store;

    private JobStore(){
        store = new HashMap<>();
        store.put("indeed", new ArrayList<>());
    }

    public JobStore getInstance(){
        if(instance == null){
            instance = new JobStore();
        }

        return instance;
    }

    public static void addJob(String parser, Job job){
        store.get(parser).add(job.title);
    }

    public boolean contains(String parser, Job job){
        boolean has = store.get(parser).contains(job.title);
        boolean timeout = (System.currentTimeMillis() - job.initTime) > 600000;

        if(timeout){
            store.get(parser).remove(job.title);
        }

        return timeout && has;
    }

}
