package org.smartinrubio.springbootdynamodb.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@DynamoDBTable(tableName = "Hotels")
public class Hotel {

    @DynamoDBHashKey
    private final Long id;

    @DynamoDBAttribute
    private final String name;

    @DynamoDBAttribute
    private final Geo geo;

}
