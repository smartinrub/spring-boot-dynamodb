package org.smatinrubio.springbootdynamodb.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Info {
    private final Double rating;
    private final String plot;
}
