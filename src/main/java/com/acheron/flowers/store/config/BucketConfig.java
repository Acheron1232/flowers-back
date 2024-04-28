package com.acheron.flowers.store.config;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BucketConfig {
    @Value("${aws.access_key}")
    private String accessKey;
    @Value("${aws.secret_access_key}")
    private String secretAccessKey;
    @Value("${aws.region}")
    private String region;

    public AWSCredentials credentials() {
        return new BasicAWSCredentials(
                accessKey,
                secretAccessKey);
    }

    @Bean
    public AmazonS3 getClient() {
        return AmazonS3ClientBuilder
                .standard()
                .withCredentials(new AWSStaticCredentialsProvider(credentials()))
                .withRegion(region)
                .build();
    }
}