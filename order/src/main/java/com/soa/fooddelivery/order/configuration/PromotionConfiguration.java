package com.soa.fooddelivery.order.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "promotion")
public class PromotionConfiguration {
    private String host;
    private String eligibilityPath;
    private String applyPath;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getEligibilityPath() {
        return eligibilityPath;
    }

    public void setEligibilityPath(String eligibilityPath) {
        this.eligibilityPath = eligibilityPath;
    }

    public String getApplyPath() {
        return applyPath;
    }

    public void setApplyPath(String applyPath) {
        this.applyPath = applyPath;
    }
}
