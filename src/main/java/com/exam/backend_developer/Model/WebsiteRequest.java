package com.exam.backend_developer.Model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WebsiteRequest {
    private String name;
    private String websiteUrl;
    private int frequency;
}
