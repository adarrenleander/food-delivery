package com.soa.fooddelivery.notification.configuration;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ResourceLoader;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;

@EnableJms
@Configuration
public class JmsConfiguration {
    private static final Logger log = org.slf4j.LoggerFactory.getLogger(JmsConfiguration.class);
    private final ResourceLoader resourceLoader;
    @Autowired
    public JmsConfiguration(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }
    @Value("${mq.url}") private String brokerUrl;
    @Value("${mq.username}") private String brokerUsername;
    @Value("${mq.password}") private String brokerPassword;

    /**
     * Connect JMS to an external ActiveMQ session, based on the active.broker-url of application.properties
     */
    @Bean
    public ActiveMQConnectionFactory activeMQConnectionFactory() {
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory();

        activeMQConnectionFactory.setBrokerURL(brokerUrl);
        log.info("Connect to ActiveMQ host: {}", brokerUrl);
        activeMQConnectionFactory.setUserName(brokerUsername);
        activeMQConnectionFactory.setPassword(brokerPassword);

        return activeMQConnectionFactory;
    }

    /**
     * Serialize message content to json using TextMessage
     */
    @Bean
    public MessageConverter jacksonJmsMessageConverter() {
        MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
        converter.setTargetType(MessageType.TEXT);
        converter.setTypeIdPropertyName("_type");
        return converter;
    }

    /**
     * Create the JmsListenerFactory with the correct marshaller.
     */
    @Bean
    public DefaultJmsListenerContainerFactory jmsListenerContainerFactory() {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        factory.setConnectionFactory(activeMQConnectionFactory());
        factory.setMessageConverter(jacksonJmsMessageConverter());
        factory.setConcurrency("3-10"); // limit concurrent listener
        factory.setErrorHandler((e) -> {
            log.error("An error occured while processing the MQ message", e);
        });

        return factory;
    }
}