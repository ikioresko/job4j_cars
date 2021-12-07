package ru.job4j.cars.model;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;

/**
 * Класс представляет собой кастомную реализацию json сериализатора для объектов Advert
 *
 * @author ikioresko
 * @version 0.1
 */
public class AdvertGsonSerializer implements JsonSerializer<Advert> {
    private SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

    @Override
    public JsonElement serialize(Advert advert, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject advJsonObj = new JsonObject();
        advJsonObj.addProperty("id", advert.getId());
        advJsonObj.addProperty("created", sdf.format(advert.getCreated()));
        advJsonObj.addProperty("category", advert.getCategory().getName());
        advJsonObj.addProperty("brand", advert.getBrand().getName());
        advJsonObj.addProperty("model", advert.getModel().getName());
        advJsonObj.addProperty("body", advert.getBody().getName());
        advJsonObj.addProperty("description", advert.getDescription());
        advJsonObj.addProperty("price", advert.getPrice());
        advJsonObj.addProperty("sold", advert.isSold());
        advJsonObj.addProperty("author", advert.getAuthor().getUsername());
        advJsonObj.addProperty("photoPath", advert.getPhotoPath());
        return advJsonObj;
    }
}
