package org.smatinrubio.springbootdynamodb.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import lombok.*;

@Getter
@AllArgsConstructor
@DynamoDBTable(tableName = "Movies")
public class Movie {

    @DynamoDBHashKey
    private final String year;

    @DynamoDBRangeKey
    private final String title;

    @DynamoDBAttribute
    private final Info info;

}
