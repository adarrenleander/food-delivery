package com.soa.fooddelivery.user.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "loyalty")
public class LoyaltyConfiguration {
    private String host;
    private String createPath;
    private String getPath;
}
