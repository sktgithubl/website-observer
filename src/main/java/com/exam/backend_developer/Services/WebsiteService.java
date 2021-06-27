package com.exam.backend_developer.Services;

import com.exam.backend_developer.Model.WebsiteRequest;
import com.exam.backend_developer.Repository.WebsiteRepository;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class WebsiteService {
    @Autowired
    WebsiteRepository websiteRepository;

    public void addWebsite(WebsiteRequest websiteRequest) {
        websiteRequest.setStatus("DOWN");
        Date currentTime = new Date(System.currentTimeMillis());
        websiteRequest.setLastStatusUpdate(currentTime);
        websiteRequest.setLastStatusTime(currentTime);
        websiteRepository.save(websiteRequest);
        return;
    }

    @Scheduled(fixedRate = 1000)
    public void run() {
        websiteRepository.findAll().forEach((websiteRequest -> {

            if((System.currentTimeMillis() - websiteRequest.getLastStatusUpdate().getTime())
                    >= websiteRequest.getFrequency() * 1000L) {
                long startTime, endTime;

                try {
                    URL urlObj = new URL(websiteRequest.getWebsiteUrl());

                    startTime = System.currentTimeMillis();
                    HttpURLConnection connection = (HttpURLConnection) urlObj.openConnection();
                    int connectionResponseCode = connection.getResponseCode();
                    endTime = System.currentTimeMillis();
                    Long responsetime = endTime-startTime;

                    websiteRequest.setLastStatusUpdate(new Date(System.currentTimeMillis()));
                    websiteRequest.setResponseTime(responsetime);

                    if(connectionResponseCode>=200 && connectionResponseCode<400) {
                        if(!websiteRequest.getStatus().equals("UP")) {
                            websiteRequest.setLastStatusTime(new Date(System.currentTimeMillis()));
                        }
                        websiteRequest.setStatus("UP");
                    } else {
                        if(!websiteRequest.getStatus().equals("DOWN")) {
                            websiteRequest.setLastStatusTime(new Date(System.currentTimeMillis()));
                        }
                        websiteRequest.setStatus("DOWN");
                    }

                    websiteRepository.save(websiteRequest);
                    System.out.println(websiteRequest);
                    connection.disconnect();

                } catch (Exception e) {
                    System.out.println(e);
                    if(!websiteRequest.getStatus().equals("DOWN")) {
                        websiteRequest.setLastStatusTime(new Date(System.currentTimeMillis()));
                    }
                    websiteRequest.setStatus("DOWN");

                    websiteRepository.save(websiteRequest);
                    System.out.println(websiteRequest);
                }
            }
        }));
    }

    public List<WebsiteRequest> showAllChecks() {
        return (List<WebsiteRequest>) websiteRepository.findAll();
    }

    public List<WebsiteRequest> showWebsite(String name) {
        return websiteRepository.getByName(name);
    }
}
