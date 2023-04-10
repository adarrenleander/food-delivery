package com.soa.fooddelivery.dispatch.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "user")
public class UserConfiguration {
    private String host;
    private String driversPath;
    private String setDriverAvailablePath;
    private String setDriverUnavailablePath;

    public String getSetDriverAvailablePath() {
        return setDriverAvailablePath;
    }

    public void setSetDriverAvailablePath(String setDriverAvailablePath) {
        this.setDriverAvailablePath = setDriverAvailablePath;
    }

    public String getSetDriverUnavailablePath() {
        return setDriverUnavailablePath;
    }

    public void setSetDriverUnavailablePath(String setDriverUnavailablePath) {
        this.setDriverUnavailablePath = setDriverUnavailablePath;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getDriversPath() {
        return driversPath;
    }

    public void setDriversPath(String driversPath) {
        this.driversPath = driversPath;
    }
}
