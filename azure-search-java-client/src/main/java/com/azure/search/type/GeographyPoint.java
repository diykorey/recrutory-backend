package com.azure.search.type;

import java.util.Arrays;
import static java.lang.String.*;

public class GeographyPoint {
    private final String type = "Point";
    private final double[] coordinates;

    public GeographyPoint(double longitude, double latitude) {
        this.coordinates = new double[]{longitude, latitude};
    }

    public String getType() {
        return type;
    }

    public double getLongitude() {
        return coordinates[0];
    }

    public double getLatitude() {
        return coordinates[1];
    }

    @Override
    public String toString() {
        return format("{type: '%s', coordinates: %s}",
                type, Arrays.toString(coordinates));
    }
}
