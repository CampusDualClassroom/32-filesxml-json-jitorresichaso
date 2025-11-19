package com.campusdual.classroom;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.io.FileWriter;
import java.io.IOException;

public class JSONFileCreator {
    public static void createDocument() {
        JsonArray items = new JsonArray();

        items.add(createJSONItem(2, "Manzana"));
        items.add(createJSONItem(1, "Leche"));
        items.add(createJSONItem(3, "Pan"));
        items.add(createJSONItem(2, "Huevo"));
        items.add(createJSONItem(1, "Queso"));
        items.add(createJSONItem(1, "Cereal"));
        items.add(createJSONItem(4, "Agua"));
        items.add(createJSONItem(6, "Yogur"));
        items.add(createJSONItem(2, "Arroz"));

        JsonObject shoppingList = new JsonObject();
        shoppingList.add("items", items);

        try(FileWriter fw = new FileWriter("src/main/resources/shoppingList.json")){
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            String json = gson.toJson(shoppingList);
            fw.write(json);
            fw.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            System.out.println("JSON file successfully created!");
        }
    }

    private static JsonObject createJSONItem(int quantity, String text) {
        JsonObject obj = new JsonObject();
        obj.addProperty("quantity", quantity);
        obj.addProperty("text", text);
        return obj;
    }
}