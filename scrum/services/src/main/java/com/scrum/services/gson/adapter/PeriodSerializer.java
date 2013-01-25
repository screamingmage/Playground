package com.scrum.services.gson.adapter;

import java.lang.reflect.Type;

import org.joda.time.Period;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

public class PeriodSerializer implements JsonSerializer<Period> {
	
	public JsonElement serialize(Period src, Type typeOfSrc,
			JsonSerializationContext context) {
		return new JsonPrimitive(src.toString());
	}
}
