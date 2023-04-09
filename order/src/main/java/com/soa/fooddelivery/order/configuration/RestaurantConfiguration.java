package com.soa.fooddelivery.order.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "restaurant")
public class RestaurantConfiguration {
    private String host;
    private String getRestaurantPath;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getGetRestaurantPath() {
        return getRestaurantPath;
    }

    public void setGetRestaurantPath(String getRestaurantPath) {
        this.getRestaurantPath = getRestaurantPath;
    }
}
