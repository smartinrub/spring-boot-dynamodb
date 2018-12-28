package org.smartinrubio.springbootdynamodb.model;

import com.amazonaws.services.dynamodbv2.datamodeling.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.smartinrubio.springbootdynamodb.utils.GeoTypeConverter;

import java.util.UUID;

@Getter
@AllArgsConstructor
@DynamoDBTable(tableName = "Hotels")
public class Hotel {

    @DynamoDBHashKey
//    @DynamoDBGeneratedUuid(DynamoDBAutoGenerateStrategy.CREATE) // Requires a mutable object
    private final String id = UUID.randomUUID().toString(); // With this we can have an inmutable object

    @DynamoDBAttribute
    private final String name;

    @DynamoDBAttribute
    @DynamoDBTypeConverted(converter = GeoTypeConverter.class)
    private final Geo geo;

}
