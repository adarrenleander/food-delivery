package com.soa.fooddelivery.order.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "payment")
public class PaymentConfiguration {
    private String host;
    private String payPath;
    private String refundPath;

    public String getRefundPath() {
        return refundPath;
    }

    public void setRefundPath(String refundPath) {
        this.refundPath = refundPath;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getPayPath() {
        return payPath;
    }

    public void setPayPath(String payPath) {
        this.payPath = payPath;
    }
}
