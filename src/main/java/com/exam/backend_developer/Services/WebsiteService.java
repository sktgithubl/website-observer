package com.exam.backend_developer.Services;

import com.exam.backend_developer.Model.WebsiteRequest;
import com.exam.backend_developer.Repository.WebsiteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WebsiteService {

    @Autowired
    WebsiteRepository websiteRepository;

    public void addWebsite(WebsiteRequest websiteRequest) {
        websiteRepository.save(websiteRequest);
        return;
    }
}
