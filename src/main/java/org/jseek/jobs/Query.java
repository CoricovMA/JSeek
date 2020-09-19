package org.jseek.jobs;


import java.util.ArrayList;
import java.util.List;

public class Query {

    private long queryTime;
    private List<Job> results;
    private String query;
    private int index;

    private Query(){
        this.queryTime = System.currentTimeMillis();
        this.results = new ArrayList<>();
    }

    public Query(String query){
        this();
        this.query = query;
        this.index = 0;
    }

    /**
     * Here we check for 3 things.
     *      1. If this query has been executed an hour or more before
     *      2. If the job's timer has run out
     *      3. If the results contain the job
     *
     * @param job Job we're checking for
     * @return boolean
     */
    public boolean check(Job job){
        return results.contains(job) &&
                job.canUse() &&
                (System.currentTimeMillis() - queryTime) > 600000;
    }

    public boolean queryFree(){
        return (System.currentTimeMillis() - queryTime) > 600000;
    }

    /**
     * @param givenJobs List of jobs
     */
    public void addJobs(List<Job> givenJobs){
        givenJobs.stream().filter(
                job -> !results.contains(job)
        ).forEach(
                job -> results.add(job)
        );
    }

    public List<Job> retrieveJobs(int numJobs){
        int tempStart = this.index;
        this.index = index + numJobs;
        try {
            return results.subList(tempStart, numJobs);
        }catch (IndexOutOfBoundsException e){
            return results.subList(tempStart, (-1 * (tempStart - results.size())));
        }

    }
}
