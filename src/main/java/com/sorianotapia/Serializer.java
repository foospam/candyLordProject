package com.sorianotapia;

import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.sorianotapia.accessories.Arm;
import com.sorianotapia.accessories.ArmContainer;
import com.sorianotapia.accessories.Holster;
import com.sorianotapia.fromVersion1.LoanSharkDebt;
import com.sorianotapia.fromVersion1.Player;
import com.sorianotapia.places.Place;
import com.sorianotapia.places.PlaceContainer;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class Serializer {

    static ObjectMapper mapper = new ObjectMapper();

    static {
        SimpleModule module =
                new SimpleModule("CustomCarSerializer", new Version(1, 0, 0, null, null, null));

        module.addSerializer(Place.class, new CustomPlaceSerializer(Place.class));
        module.addSerializer(Player.class, new CustomPlayerSerializer(Player.class));
        module.addSerializer(GameDate.class, new CustomGameDateSerializer(GameDate.class));

        module.addDeserializer(Place.class, new CustomPlaceDeserializer(Place.class));
        module.addDeserializer(Player.class, new CustomPlayerDeserializer(Player.class));
        module.addDeserializer(GameDate.class, new CustomGameDateDeserializer(GameDate.class));

        mapper.registerModule(module);
    }

    public static void loadGame() throws IOException {
        loadPlaces();
        Player player = loadPlayer();
        Controller.setPlayer(player);
        Controller.setGameDate(loadGameDate());
    }

    public static void saveGame(Player player) throws IOException {

        ArrayNode placeNode = serializePlaces();
        ObjectNode playerNode = serializePlayer(player);
        ObjectNode gameDateNode = serializeGameDate(Controller.date);

        ObjectNode rootNode = (ObjectNode) mapper.createObjectNode();
        rootNode.put("places", placeNode);
        rootNode.put("player", playerNode);
        rootNode.put("gameDate", gameDateNode);

        mapper.writeValue(new File("savedgame.json"), rootNode);
    }

    private static ArrayNode serializePlaces() throws IOException {
        ArrayNode rootNode = (ArrayNode) mapper.createArrayNode();

        for (int i = 0; i < 8; i++) {
            Place place = PlaceContainer.getPlaceByIndex(i);
            String nodeString = mapper.writeValueAsString(place);
            ObjectNode addedNode = (ObjectNode) mapper.readTree(nodeString);
            rootNode.add(addedNode);
        }

        mapper.writeValue(new File("savedplacecontainer.json"), rootNode);
        return rootNode;
    }


    private static ObjectNode serializePlayer(Player player) throws JsonProcessingException {
        String playerString = mapper.writeValueAsString(player);
        return (ObjectNode) mapper.readTree(playerString);
    }

    private static ObjectNode serializeGameDate(GameDate gameDate) throws JsonProcessingException {
        String gameDateString = mapper.writeValueAsString(gameDate);
        return (ObjectNode) mapper.readTree(gameDateString);
    }


    private static void loadPlaces() {
        try {
            JsonNode placeNode = mapper.readTree(new File("savedgame.json")).get("places");
            placeNode.forEach(s -> {
                try {
                    Place place = mapper.readValue(s.toString(), Place.class);
                    System.out.println(place.getName());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static Player loadPlayer(){
        try {
            JsonNode playerNode = mapper.readTree(new File("savedgame.json")).get("player");
            System.out.println(playerNode);
            Player player  = mapper.readValue(playerNode.toString(), Player.class);
            return player;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static GameDate loadGameDate(){
        try {
            JsonNode gameDateNode = mapper.readTree(new File("savedgame.json")).get("gameDate");
            System.out.println(gameDateNode);
            GameDate gameDate  = mapper.readValue(gameDateNode.toString(), GameDate.class);
            Controller.setGameDate(gameDate);
            return gameDate;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}

class CustomPlaceSerializer extends StdSerializer<Place> {

    protected CustomPlaceSerializer(Class<Place> t) {
        super(t);
    }

    @Override
    public void serialize(Place place, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField("name", place.getName());
        jsonGenerator.writeNumberField("x", place.getCoordinates().x);
        jsonGenerator.writeNumberField("y", place.getCoordinates().y);
        jsonGenerator.writeArrayFieldStart("stuff");
        for (int i = 0; i < 8; i++) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeStringField("name", place.getStuffName(i));
            jsonGenerator.writeNumberField("price", place.getStuffPrice(i));
            jsonGenerator.writeEndObject();
        }
        jsonGenerator.writeEndArray();
        jsonGenerator.writeEndObject();
    }
}

class CustomGameDateSerializer extends StdSerializer<GameDate> {

    public CustomGameDateSerializer(Class<GameDate> t) {
        super(t);
    }

    @Override
    public void serialize(GameDate gameDate, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField("gameDate", gameDate.getStringDate());
        jsonGenerator.writeEndObject();
    }
}

class CustomPlayerSerializer extends StdSerializer<Player> {

    protected CustomPlayerSerializer(Class<Player> t) {
        super(t);
    }

    @Override
    public void serialize(Player player, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField("location", player.getLocation().getName());
        jsonGenerator.writeNumberField("health", player.getHealth());
        jsonGenerator.writeNumberField("reputation", player.getReputation());
        jsonGenerator.writeNumberField("hold", player.getHold());
        jsonGenerator.writeNumberField("maxHold", player.getMaxHold());
        jsonGenerator.writeNumberField("cash", player.getCash());
        jsonGenerator.writeNumberField("deposits", player.getDeposits());

        jsonGenerator.writeArrayFieldStart("armList");
        for (Arm s : player.getHolster().getArmList()) {
            try {
                jsonGenerator.writeString(s.getName());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        jsonGenerator.writeEndArray();

        jsonGenerator.writeObjectFieldStart("debt");
        jsonGenerator.writeNumberField("value", player.getDebtValue());
        jsonGenerator.writeNumberField("paymentPeriod", player.getDebtDays());
        jsonGenerator.writeBooleanField("activeCredit", player.getActiveCredit());
        jsonGenerator.writeNumberField("overdue", player.getOverdue());
        jsonGenerator.writeEndObject();

        jsonGenerator.writeArrayFieldStart("stuffOnHand");
        for (String stuff : player.getStuffOnHandMap().keySet()) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeStringField("name", stuff);
            jsonGenerator.writeNumberField("quantity", player.getStuffOnHandMap().get(stuff));
            jsonGenerator.writeEndObject();
        }
        jsonGenerator.writeEndArray();
        jsonGenerator.writeEndObject();
    }
}

class CustomPlaceDeserializer extends StdDeserializer<Place> {

    protected CustomPlaceDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public Place deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
        Place place = new Place();
        ObjectCodec codec = jsonParser.getCodec();
        JsonNode node = codec.readTree(jsonParser);

        JsonNode nameNode = node.get("name");
        JsonNode xNode = node.get("x");
        JsonNode yNode = node.get("y");
        place.setName(nameNode.asText());
        place.setCoordinates(new Point(xNode.asInt(), yNode.asInt()));
        return place;
    }
}

class CustomPlayerDeserializer extends StdDeserializer<Player> {

    protected CustomPlayerDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public Player deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
        Player player = new Player();
        ObjectCodec codec = jsonParser.getCodec();
        JsonNode node = codec.readTree(jsonParser);

        player.setLocation(PlaceContainer.getPlaceByName(node.get("location").asText()));
        player.setHealth(node.get("health").asInt());
        player.setReputation(node.get("reputation").asInt());
        player.setHold(node.get("hold").asInt());
        player.setMaxHold(node.get("maxHold").asInt());
        player.setCash(node.get("cash").asInt());
        player.setDeposits(node.get("deposits").asInt());


        Holster holster = new Holster();
        holster.clear();

        ArrayNode armListNode = (ArrayNode) node.get("armList");
        armListNode.forEach(s -> {
            System.out.println("One go!");
            Arm arm = ArmContainer.getArmByName(s.asText());
            holster.add(arm);
        });

        player.setHolster(holster);

        ObjectNode debtNode = (ObjectNode) node.get("debt");
        LoanSharkDebt debt = new LoanSharkDebt(
                debtNode.get("value").asInt(),
                debtNode.get("paymentPeriod").asInt(),
                debtNode.get("activeCredit").asBoolean(),
                debtNode.get("overdue").asInt()
        );

        player.setDebt(debt);

        HashMap<String, Integer> stuffOnHand = new HashMap<>();
        ArrayNode stuffNode = (ArrayNode) node.get("stuffOnHand");

        System.out.println("Printing stuff on hand:");
        stuffNode.forEach(s -> {
            System.out.println(s.get("name").asText()+" "+s.get("quantity").asInt());
            stuffOnHand.put(s.get("name").asText(), s.get("quantity").asInt());
        });

        player.setStuffOnHand(stuffOnHand);

        return player;
    }
}

class CustomGameDateDeserializer extends StdDeserializer<GameDate>{

    protected CustomGameDateDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public GameDate deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
        ObjectCodec codec = jsonParser.getCodec();
        JsonNode node = codec.readTree(jsonParser);
        String dateValue = node.get("gameDate").asText();
        return new GameDate(dateValue);
    }
}
