package com.sorianotapia.places;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeCreator;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.sorianotapia.GameSettings;
import com.sorianotapia.stuff.Stuff;
import com.sorianotapia.stuff.StuffContainer;
import org.w3c.dom.Node;

import java.awt.Point;
import java.io.File;
import java.io.IOException;

public class Place {

    public static void main(String[] args) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        SimpleModule module =
                new SimpleModule("CustomCarSerializer", new Version(1, 0, 0, null, null, null));
        module.addSerializer(Place.class, new CustomPlaceSerializer(Place.class));
        mapper.registerModule(module);

        ArrayNode node = mapper.createArrayNode();



        //Place place = new Place("Cordoba", 3131, 4144);
        //place = new Place("Cordoba", new Point(22,43));
        Place place2 = new Place("Cordoba2", new Point(22,43));
        String string = mapper.writeValueAsString(place2);
        JsonNode subnode = mapper.readTree(mapper.writeValueAsString(place2));

        node.add(subnode);
        node.add(subnode);
        node.forEach(a -> System.out.println(a.asText()));

        mapper.writeValue(new File("placecontainer.json"), node);
        //JsonNode node = JsonNodeCreator();
        System.out.println(string);

    }


    private String name;
    private Point coordinates;
    private StuffContainer stuffContainer;


    public double distanceTo(Place other) {
        return coordinates.distance(other.getCoordinates());
    }


    public Place(String name, Point coordinates) {
        this.name = name;
        this.coordinates = coordinates;
        this.stuffContainer = new StuffContainer();
    }

    public Place(String name, int x, int y){
        new Place(name, new Point(x,y));
    }

    public Place(){
    }

    public int getStuffPrice(String stuffName) {
        return stuffContainer.getPrice(stuffName);
    }

    public int getStuffPrice(int index){
        return stuffContainer.getPrice(index);
    }

    public String getName() {
        return name;
    }

    public String getStuffName(int index){
        return stuffContainer.getStuffName(index);
    }

    public Stuff getStuff(int index){
        return stuffContainer.getStuff(index);
    }

    public Stuff getRandomStuff(){
        int randomIndex = (int) (Math.random() * stuffContainer.getSize());
        return getStuff(randomIndex);
    }

    public void updateStuffPrices(){
        stuffContainer.updateStuffPrices(GameSettings.RAMDOM_PRICE_VARIATION_PERCENTAGE);
    }

    public Point getCoordinates(){
        return coordinates;
    }


    public void setName(String name) {
        this.name = name;
    }

    public void setCoordinates(Point coordinates){
        this.coordinates = coordinates;
    }
}

class CustomPlaceSerializer extends StdSerializer<Place> {

    protected CustomPlaceSerializer(Class<Place> t) {
        super(t);
    }

    protected CustomPlaceSerializer(JavaType type) {
        super(type);
    }

    protected CustomPlaceSerializer(Class<?> t, boolean dummy) {
        super(t, dummy);
    }

    protected CustomPlaceSerializer(StdSerializer<?> src) {
        super(src);
    }

    @Override
    public void serialize(Place place, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField("name", place.getName());
        jsonGenerator.writeNumberField("x", place.getCoordinates().x);
        jsonGenerator.writeNumberField("y", place.getCoordinates().y);
        jsonGenerator.writeEndObject();
    }
}