package com.soa.fooddelivery.order.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "dispatch")
public class DispatchConfiguration {
    private String host;
    private String dispatchOrderPath;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getDispatchOrderPath() {
        return dispatchOrderPath;
    }

    public void setDispatchOrderPath(String dispatchOrderPath) {
        this.dispatchOrderPath = dispatchOrderPath;
    }
}
