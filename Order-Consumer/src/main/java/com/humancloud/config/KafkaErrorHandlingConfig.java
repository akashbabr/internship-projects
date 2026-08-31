/*
package com.humancloud.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.listener.DeadLetterPublishingRecoverer;
import org.springframework.kafka.listener.DefaultErrorHandler;
import org.springframework.util.backoff.FixedBackOff;

import com.humancloud.event.OrderCreatedEvent;

@Configuration
public class KafkaErrorHandlingConfig {

    @Bean
    public DeadLetterPublishingRecoverer deadLetterPublishingRecoverer(
            KafkaTemplate<String, OrderCreatedEvent> dltKafkaTemplate) {

        return new DeadLetterPublishingRecoverer(dltKafkaTemplate);
    }

    @Bean
    public DefaultErrorHandler errorHandler(
            DeadLetterPublishingRecoverer recoverer) {

        FixedBackOff backOff = new FixedBackOff(1000L, 4L);

        return new DefaultErrorHandler(recoverer, backOff);
    }
}
*/
