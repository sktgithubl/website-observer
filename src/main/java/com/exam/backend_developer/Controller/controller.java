package com.exam.backend_developer.Controller;

import com.exam.backend_developer.Model.WebsiteRequest;
import com.exam.backend_developer.Services.WebsiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class controller {

    @Autowired
    WebsiteService websiteService;

    @PostMapping("/add")
    public ResponseEntity addWebsite(@RequestBody WebsiteRequest websiteRequest) {
        websiteService.addWebsite(websiteRequest);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
