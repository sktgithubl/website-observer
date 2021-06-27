package com.exam.backend_developer.Services;

import com.exam.backend_developer.Model.WebsiteRequest;
import com.exam.backend_developer.Repository.WebsiteRepository;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Calendar;

@Service
public class WebsiteService {
    @Autowired
    WebsiteRepository websiteRepository;

    public void addWebsite(WebsiteRequest websiteRequest) {
        websiteRepository.save(websiteRequest);
        return;
    }

    @Scheduled(fixedRate = 1000)
    public void run() {
        //website check
    }
}
