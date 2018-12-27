package org.smartinrubio.springbootdynamodb.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Geo {
    private final Double latitude;
    private final Double Longitude;
}
