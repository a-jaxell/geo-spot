package com.laboration2.utils;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import org.geolatte.geom.G2D;
import org.geolatte.geom.Point;
import org.geolatte.geom.builder.DSL;
import org.geolatte.geom.crs.CoordinateReferenceSystems;

import java.io.IOException;

public class Point2DDeserializer extends JsonDeserializer<Point<G2D>> {
    @Override
    public Point<G2D> deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);
        double lat = node.get("lat").asDouble();
        double lng = node.get("lng").asDouble();

        return DSL.point(CoordinateReferenceSystems.WGS84, DSL.g(lng, lat));
    }
}
