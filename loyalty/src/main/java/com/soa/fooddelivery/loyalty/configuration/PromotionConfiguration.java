package com.soa.fooddelivery.loyalty.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "promotion")
public class PromotionConfiguration {
    private String host;
    private String grantPath;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getGrantPath() {
        return grantPath;
    }

    public void setGrantPath(String grantPath) {
        this.grantPath = grantPath;
    }
}
