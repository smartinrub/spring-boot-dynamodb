package org.smartinrubio.springbootdynamodb.utils;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverter;
import org.smartinrubio.springbootdynamodb.model.Geo;

public class GeoTypeConverter implements DynamoDBTypeConverter<String, Geo> {
    @Override
    public String convert(Geo geo) {
        String geoValue = null;
        try {
            if (geo != null) {
                geoValue = String.format("%f, %f", geo.getLatitude(), geo.getLongitude());
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return geoValue;
    }

    @Override
    public Geo unconvert(String jsonData) {
        Geo itemGeo = null;
        try {
            if (jsonData != null && jsonData.length() != 0) {
                String[] data = jsonData.split(",");
                itemGeo = new Geo(Double.valueOf(data[0].trim()), Double.valueOf(data[0].trim()));
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return itemGeo;
    }
}
