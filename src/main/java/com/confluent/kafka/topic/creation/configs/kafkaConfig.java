package com.confluent.kafka.topic.creation.configs;

import org.apache.kafka.common.config.TopicConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.apache.kafka.clients.admin.NewTopic;

@Configuration
public class kafkaConfig {

    @Value("${spring.kafka.properties.bootstrap.servers}")
    String bootstrapServers;

    @Value("${topics.asset-commercial.name}")
    String topicName;

    @Value("${topics.asset-commercial.partitions}")
    int topicPartitions;

    @Value("${topics.asset-commercial.replicas}")
    int topicReplicas;

    @Bean
    public NewTopic assetBasicTopic() {
        return TopicBuilder.name(topicName)
                .partitions(topicPartitions)
                .replicas(topicReplicas)
                .compact()
                .build();
    }

    @Bean
    public NewTopic assetRestrictionsTopic() {
        return TopicBuilder.name(topicName + "_Restrictions")
                .partitions(topicPartitions)
                .replicas(topicReplicas)
                .config(TopicConfig.DELETE_RETENTION_MS_CONFIG, "360000")
                .build();
    }
}