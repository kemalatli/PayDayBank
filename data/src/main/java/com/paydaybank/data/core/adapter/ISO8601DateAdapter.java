package com.paydaybank.data.core.adapter;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.google.gson.JsonSyntaxException;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.util.Date;

public class ISO8601DateAdapter implements JsonSerializer<Date>, JsonDeserializer<Date> {
    public ISO8601DateAdapter() {
    }

    @Override
    public Date deserialize(JsonElement json, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        if (!(json instanceof JsonPrimitive)) {
            throw new JsonParseException("The date should be a string value");
        }
        Date date = deserializeToDate(json);
        if (type == Date.class) {
            return date;
        }else {
            throw new IllegalArgumentException(getClass() + " cannot deserialize to " + type);
        }
    }

    private Date deserializeToDate(JsonElement json) {
        try {
            return ISO8601DateParser.parse(json.getAsString());
        } catch (ParseException e) {
            throw new JsonSyntaxException(json.getAsString(), e);
        }
    }

    @Override
    public JsonElement serialize(Date date, Type type, JsonSerializationContext jsonSerializationContext) {
        return new JsonPrimitive(ISO8601DateParser.format(date));
    }
}
