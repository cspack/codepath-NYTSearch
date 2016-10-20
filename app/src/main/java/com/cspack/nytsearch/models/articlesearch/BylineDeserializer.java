package com.cspack.nytsearch.models.articlesearch;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSyntaxException;

import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Created by chris on 10/18/16.
 */

public class BylineDeserializer implements JsonDeserializer<Byline> {
    public BylineDeserializer() {
        super();
    }

    @Override
    public Byline deserialize(JsonElement json, Type typeOfT,
                              JsonDeserializationContext context) throws JsonParseException {
        try {
            JsonObject obj = json.getAsJsonObject();
            // Remove empty array since GSON doesn't handle it correctly either.
            if (obj.get("person").isJsonArray()) {
                if (obj.get("person").getAsJsonArray().size() == 0) {
                    obj.remove("person");
                }
            }
            // Resume parsing.
            return new Gson().fromJson(obj, Byline.class);
        } catch (IllegalStateException | JsonSyntaxException e) {
            Log.d("Byline", "Expected catch of Not a JSON Object: [] GSON bug.");
            return null;
        }
    }
}
