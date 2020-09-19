package org.jseek.jobs;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * TODO
 * 1. Query object
 *    - query time
 *    - results
 *    - timer
 *    - query
 *2. Query store
 *    - key = query
 *    - val = query object
 *    - append results
 *    - if query timer has expired, start returning results
 *
 */
public class QueryStore {

    private static Map<String, List<Job>> store;
    private static QueryStore instance;

    private QueryStore(){
        store = new HashMap<>();
    }

    public static QueryStore getInstance(){
        if(instance == null){
            instance = new QueryStore();
        }
        return instance;
    }

    public void doQuery(String query, List<Job> results){
        if(!store.containsKey(query)){
            store.put(query, results);
        }else if(store.containsKey(query) && (store.get(query).size() < results.size())){
            List<Job> notThere = results.stream()
                    .filter(job -> !store.get(query).contains(job))
                    .collect(Collectors.toList());
            store.get(query).addAll(notThere);
        }
    }

}
