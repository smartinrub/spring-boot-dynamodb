package org.smartinrubio.springbootdynamodb.repository;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.model.*;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class CustomHotelRepositoryImpl implements CustomHotelRepository {

    private static final String TABLE_NAME = "Hotels";

    private final AmazonDynamoDB amazonDynamoDB;

    public CustomHotelRepositoryImpl(AmazonDynamoDB amazonDynamoDB) {
        this.amazonDynamoDB = amazonDynamoDB;
    }

    @Override
    public void createTable() {

        try {
            List<AttributeDefinition> attributeDefinitions= new ArrayList<>();
            attributeDefinitions.add(new AttributeDefinition().withAttributeName("id").withAttributeType("S"));

            List<KeySchemaElement> keySchema = new ArrayList<>();
            keySchema.add(new KeySchemaElement().withAttributeName("id").withKeyType(KeyType.HASH));

            CreateTableRequest request = new CreateTableRequest()
                    .withTableName("Hotels")
                    .withKeySchema(keySchema)
                    .withAttributeDefinitions(attributeDefinitions)
                    .withProvisionedThroughput(new ProvisionedThroughput()
                            .withReadCapacityUnits(1L)
                            .withWriteCapacityUnits(1L));

            amazonDynamoDB.createTable(request);
            TableDescription description = amazonDynamoDB.describeTable(TABLE_NAME).getTable();

            log.info("Success. Table status: " + description.getTableStatus());
        } catch (Exception e) {
            log.error("Unable to create table: {}", e.getMessage());
        }
    }
}
