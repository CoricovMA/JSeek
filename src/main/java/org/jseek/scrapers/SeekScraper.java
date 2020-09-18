package org.jseek.scrapers;

import org.jseek.ScraperResponse.ScraperResponse;
import org.jseek.requests.JobRequest;

import java.io.IOException;

public interface SeekScraper {

    void execute(JobRequest request) throws IOException;

    ScraperResponse retrieveResponses();
}
