package com.neocryptolabs.core.util

import com.google.gson.*
import com.google.gson.reflect.TypeToken
import java.sql.Timestamp

var gson: Gson = GsonBuilder()
    .registerTypeAdapter(
        Timestamp::class.java,
        JsonDeserializer<Timestamp> { json, /*typeOfT*/ _, /*context*/ _ -> Timestamp(json.asJsonPrimitive.asLong) })
    .registerTypeAdapter(
        Timestamp::class.java,
        JsonSerializer<Timestamp> { timestamp, /*type*/ _, /*jsonSerializationContext*/ _ -> JsonPrimitive(timestamp.time) })
    .create()

inline fun <reified T> fromBasicJson(json: String): T {
    return gson.fromJson(json, object : TypeToken<T>() {}.type)
}

inline fun <reified T> fromBasicJson(jsonElement: JsonElement): T {
    return gson.fromJson(jsonElement, object : TypeToken<T>() {}.type)
}

fun toJsonString(obj: Any): String? =
    gson.toJson(obj)

fun toJsonString(obj: JsonElement): String? =
    gson.toJson(obj)