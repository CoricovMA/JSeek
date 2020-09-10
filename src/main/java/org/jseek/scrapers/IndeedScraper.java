package org.jseek.scrapers;

import org.jseek.requests.JobRequest;
import org.jseek.response.IJSeekResponse;

public class IndeedScraper implements SeekScraper{


    @Override
    public void execute(JobRequest request) {

    }

    @Override
    public IJSeekResponse[] retrieveResponses() {
        return new IJSeekResponse[0];
    }
}
