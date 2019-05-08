package com.lanu.travian_helper.config;

import com.gargoylesoftware.htmlunit.WebClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebSecurityConfig {

    @Bean
    public WebClient webClient() throws Exception {
        WebClient webClient = new WebClient();
        webClient.getOptions().setCssEnabled(false);
        webClient.getOptions().setDownloadImages(false);
        webClient.getOptions().setJavaScriptEnabled(false);
        return webClient;
    }
}
