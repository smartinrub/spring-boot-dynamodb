package org.smatinrubio.springbootdynamodb.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@DynamoDBTable(tableName = "Movies")
public class Movie {
    private final String year;
    private final String title;

}
