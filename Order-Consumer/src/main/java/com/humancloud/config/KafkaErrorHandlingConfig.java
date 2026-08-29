package com.humancloud.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.common.TopicPartition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.listener.DefaultErrorHandler;
import org.springframework.kafka.listener.DeadLetterPublishingRecoverer;
import org.springframework.util.backoff.FixedBackOff;

@Configuration
public class KafkaErrorHandlingConfig {

	@Bean
	public DefaultErrorHandler errorHandler(KafkaTemplate<Object, Object> kafkaTemplate)
	{
		DeadLetterPublishingRecoverer recoverer = new DeadLetterPublishingRecoverer(kafkaTemplate,
				(record, exception) -> new TopicPartition(record.topic() + ".DLT", record.partition()));

		// 3 retry attempts, 1 second between attempts
		FixedBackOff backOff = new FixedBackOff(1000L, 3L);

		return new DefaultErrorHandler(recoverer, backOff);
	}
}