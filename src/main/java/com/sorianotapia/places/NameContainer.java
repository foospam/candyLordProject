package com.sorianotapia.places;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class NameContainer {


    private static ArrayList<String> placeNames = new ArrayList<>();
    private static ArrayList<String> stuffNames = new ArrayList<>();
    private static ObjectMapper mapper = new ObjectMapper();
    private static ObjectNode root;


    private static void readAllPlaceNames() {
        try {
            ArrayNode nameSource = (ArrayNode) root.get("placeNames");
            nameSource.forEach(s -> placeNames.add(s.toString().replace("\"", "")));
        } catch (NullPointerException E) {
            throw new NullPointerException("The name file does not contain any 'placeNames' element.");
        }
    }

    private static void readAllStuffNames() {
        try {
            ArrayNode nameSource = (ArrayNode) root.get("stuffNames");
            nameSource.forEach(s -> stuffNames.add(s.toString().replace("\"", "")));
        } catch (NullPointerException E) {
            throw new NullPointerException("The name file does not contain any 'stuffNames' element.");
        }
    }

    public static void setFile(String fileName) throws IOException {
        root = (ObjectNode) mapper.readTree(new File(fileName));
        readAllPlaceNames();
        readAllStuffNames();
    }

    public static String getRandomPlaceName() {
        if (placeNames.size() > 0) {
            int index = (int) (Math.random() * placeNames.size());
            String name = placeNames.get(index);
            placeNames.remove(index);
            return name;
        }
        return null;
    }

    public static ArrayList<String> getPlaceNames(){
        return placeNames;
    }

    public static ArrayList<String> getStuffNames(){
        return stuffNames;
    }

}