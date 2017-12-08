package com.sttx.bookmanager.util.http;

import java.io.IOException;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class CcyJsonUtil {
  
    
    public static String toJson(Object obj) throws JsonProcessingException{
      return toJson(obj,true);
    }
    
    public static String toJson(Object obj, boolean usePrettyPrinter) throws JsonProcessingException{
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(Include.NON_NULL);
        mapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
        if(usePrettyPrinter) {
            return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
        } else {
            return mapper.writeValueAsString(obj);
        }
    }
    
    @SuppressWarnings("unchecked")
  public static <S>  S fromJson(String data, Class<?> cl) {
      if(data == null)
            return null;
        ObjectMapper mapper = new ObjectMapper();
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        try {
            return (S) mapper.readValue(data, cl);
        } catch (IOException e) {
          e.printStackTrace();
            return null;
        }
    }
    
    @SuppressWarnings("unchecked")
  public static <S> S fromJson(final String jsonStr, Class<?> collectionCl, Class<?> elmentCl) {
        if(jsonStr == null)
            return null;
        ObjectMapper mapper = new ObjectMapper();
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        JavaType javaType = mapper.getTypeFactory().constructParametricType(collectionCl, elmentCl);
        try {
          return (S) mapper.readValue(jsonStr, javaType);
        } catch(IOException e) {
          e.printStackTrace();
          return null;
        }
    }
}
