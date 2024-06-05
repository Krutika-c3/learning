package com.application.springConfig;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfiguration {

    @Value("${server.port}")
    private String serverPort;

    @Value("${url}")
    private String url;

    public String getServerPort() {
        return serverPort;
    }

    public String getUrl() {
        return url;
    }
}
