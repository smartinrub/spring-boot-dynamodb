package org.smartinrubio.springbootdynamodb.repository;

import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.model.*;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.extern.slf4j.Slf4j;
import org.smartinrubio.springbootdynamodb.exception.DuplicateTableException;
import org.smartinrubio.springbootdynamodb.exception.GenericDynamoDBException;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.Iterator;

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
    public void loadData() throws IOException {
        Table table = dynamoDB.getTable(TABLE_NAME);

        JsonParser parser = new JsonFactory().createParser(new File("hotels.json"));

        JsonNode rootNode = new ObjectMapper().readTree(parser);
        Iterator<JsonNode> iterator = rootNode.iterator();

        ObjectNode currentNode;

        while (iterator.hasNext()) {
            currentNode = (ObjectNode) iterator.next();

            Long id = currentNode.path("id").asLong();
            String name = currentNode.path("name").asText();
            String geo = currentNode.path("geo").toString();

            try {
                table.putItem(new Item()
                        .withPrimaryKey("id", id)
                        .withString("name", name)
                        .withJSON("geo", geo));
                log.info("PutItem succeeded " + id + " " + name);
            } catch (Exception e) {
                log.error("Unable to add hotel: {} - {}: \n{}", id, name, e.getMessage());
                break;
            }
        }
        parser.close();

    }
}
