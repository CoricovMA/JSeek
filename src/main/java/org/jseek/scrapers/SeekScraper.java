package org.jseek.scrapers;

import org.jseek.requests.JobRequest;
import org.jseek.response.IJSeekResponse;

public interface SeekScraper {

    void execute(JobRequest request);

    IJSeekResponse [] retrieveResponses();
}
