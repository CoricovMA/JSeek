package org.jseek.scrapers;

import org.jseek.requests.JobRequest;
import org.jseek.response.IJSeekResponse;

import java.io.IOException;

public interface SeekScraper {

    void execute(JobRequest request) throws IOException;

    IJSeekResponse  retrieveResponses();
}
