package org.smartinrubio.springbootdynamodb.repository;

import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.model.*;
import lombok.extern.slf4j.Slf4j;
import org.smartinrubio.springbootdynamodb.exception.DuplicateTableException;
import org.smartinrubio.springbootdynamodb.exception.GenericDynamoDBException;

import java.util.Collections;

@Slf4j
public class CustomHotelRepositoryImpl implements CustomHotelRepository {

    private static final String TABLE_NAME = "Hotels";

    private final DynamoDB dynamoDB;

    public CustomHotelRepositoryImpl(DynamoDB dynamoDB) {
        this.dynamoDB = dynamoDB;
    }

    @Override
    public void createTable() {

        try {
            Table table = dynamoDB.createTable(TABLE_NAME,
                    Collections.singletonList(new KeySchemaElement("id", KeyType.HASH)),
                    Collections.singletonList(new AttributeDefinition("id", ScalarAttributeType.N)),
                    new ProvisionedThroughput(10L, 10L));
            table.waitForActive();

            log.info("Success. Table status: " + table.getDescription().getTableStatus());
        } catch (ResourceInUseException e) {
            log.error("Table already Exists: {}", e.getMessage());
            throw new DuplicateTableException(e);

        } catch (Exception e) {
            log.error("Unable to create table: {}", e.getMessage());
            throw new GenericDynamoDBException(e);
        }
    }

    @Override
    public void loadData() {

    }
}
