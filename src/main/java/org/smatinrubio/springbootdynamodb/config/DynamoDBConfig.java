package org.smatinrubio.springbootdynamodb.config;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig;
import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

@Configuration
@EnableDynamoDBRepositories
public class DynamoDBConfig {

    @Value("${amazon.dynamodb.accesskey}")
    private String amazonDynamoDBAccessKey;

    @Value("${amazon.dynamodb.secretkey}")
    private String amazonDynamoDBSecretKey;

    @Value("${amazon.dynamodb.endpoint}")
    private String amazonDynamoDBEndpoint;

    @Value("${amazon.dynamodb.region}")
    private String amazonDynamoDBRegion;

    public AWSCredentialsProvider amazonAWSCredentialsProvider() {
        return new AWSStaticCredentialsProvider(amazonAWSCredentials());
    }

    @Bean
    @Profile("prod")
    public AWSCredentials amazonAWSCredentials() {
        return new BasicAWSCredentials(amazonDynamoDBAccessKey, amazonDynamoDBSecretKey);
    }

    @Bean
    public DynamoDBMapperConfig dynamoDBMapperConfig() {
        return DynamoDBMapperConfig.DEFAULT;
    }

    @Bean
    public DynamoDBMapper dynamoDBMapper(AmazonDynamoDB amazonDynamoDB, DynamoDBMapperConfig config) {
        return new DynamoDBMapper(amazonDynamoDB, config);
    }

    @Bean
    @Primary
    @Profile("dev")
    public AmazonDynamoDB amazonDynamoDBDev() {
        return AmazonDynamoDBClientBuilder
                .standard()
                .withEndpointConfiguration(
                        new AwsClientBuilder
                                .EndpointConfiguration(amazonDynamoDBEndpoint, amazonDynamoDBRegion))
                .build();
    }

    @Bean
    @Profile("prod")
    public AmazonDynamoDB amazonDynamoDBProd() {
        return AmazonDynamoDBClientBuilder
                .standard()
                .withCredentials(amazonAWSCredentialsProvider())
                .withRegion(Regions.US_WEST_2)
                .build();
    }

}
