package com.assesment.droneapplication.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Nyasha Chirinda - 30/01/2023
 */

@Slf4j
public class FormatterUtil {

    private FormatterUtil(){}

    private static final ObjectMapper prettyPrinter = new ObjectMapper()
            .enable(SerializationFeature.INDENT_OUTPUT)
            .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
            .disable(SerializationFeature.FAIL_ON_EMPTY_BEANS)
            .registerModule(new JavaTimeModule());

    /**
     * convert POJO to json preformatted string
     *
     * @param obj POJO to convert to json String
     * @return json string
     */
    public static String toJson(Object obj) {

        String prettyJsonObj = "";
        if (obj != null) {
            try {
                prettyJsonObj = prettyPrinter.writeValueAsString(obj);
            } catch (JsonProcessingException e) {
                log.error("Failed to convert Object to JSON - {}", e.getMessage(), e);
            }
        }
        return prettyJsonObj;
    }

    /**
     * convert json string to POJO
     *
     * @param jsonString - string to convert to POJO
     * @param classType  - POJO Type / Class Type to use for the deserialization
     * @return deserialized POJO of type <T>
     */
    public static <T> T fromJson(String jsonString, Class<T> classType) {

        T value = null;
        try {
            value = prettyPrinter.readValue(jsonString, classType);
        } catch (JsonProcessingException e) {
            log.error("Failed to convert JSON String to Object", e);
        }
        return value;
    }
}
