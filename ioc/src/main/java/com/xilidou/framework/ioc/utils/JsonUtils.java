package com.xilidou.framework.ioc.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.InputStream;
import java.text.SimpleDateFormat;

public class JsonUtils {

    private static final ObjectMapper mapper = new ObjectMapper();

    private JsonUtils() {
    }

    public static ObjectMapper getObjectMapper() {
        return mapper;
    }

    public static <T> T readValue(String json, Class<T> cls) {
        try {
            return mapper.readValue(json, cls);
        } catch (Exception var3) {
            return null;
        }
    }

    public static <T> T readValue(InputStream is,Class<T> cls){
        try{
            return mapper.readValue(is,cls);
        }catch (Exception e){
            return null;
        }
    }

    public static <T> T readValue(byte[] bytes, Class<T> cls) {
        try {
            return mapper.readValue(bytes, cls);
        } catch (Exception var3) {
            return null;
        }
    }

    public static <T> T readValue(String json, TypeReference valueTypeRef) {
        try {
            return mapper.readValue(json, valueTypeRef);
        } catch (Exception var3) {
            return null;
        }
    }

    public static <T> T readValue(byte[] bytes, TypeReference valueTypeRef) {
        try {
            return mapper.readValue(bytes, valueTypeRef);
        } catch (Exception var3) {
            return null;
        }
    }

    public static <T> T readValue(InputStream is,TypeReference valueTypeRef){
        try{
            return mapper.readValue(is,valueTypeRef);
        }catch (Exception e){
            return null;
        }
    }
    public static String writeValue(Object entity) {
        try {
            return mapper.writeValueAsString(entity);
        } catch (Exception var2) {
            return null;
        }
    }

    public static byte[] writeByteValue(Object entity) {
        try {
            return mapper.writeValueAsBytes(entity);
        } catch (Exception var2) {
            return null;
        }
    }

    static {
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        mapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
        mapper.getDeserializationConfig().withoutFeatures(new DeserializationFeature[]{DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES});
        mapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
        mapper.configure(JsonParser.Feature.ALLOW_YAML_COMMENTS, true);
        mapper.configure(JsonParser.Feature.ALLOW_COMMENTS, true);
        mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
        mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
        mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

}
