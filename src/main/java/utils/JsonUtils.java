package utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class JsonUtils {
    public static String parseToJson(Object value) {
        String jsonObject = "{}";
        try {
            ObjectMapper mapper = new ObjectMapper();
            jsonObject = mapper.writeValueAsString(value);
        } catch (JsonProcessingException e) {
            System.out.println(e.getMessage());
        }
        return jsonObject;
    }

    public static <T> T getJavaObj(String filePath, Class<T> valueType) {
        File file = new File(filePath);
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(file, valueType);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public static boolean writeToFile(String filePath, Object value) {
        File file = new File(filePath);
        if (!file.exists()) { return false; }
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            objectMapper.writeValue(file, value);
        } catch (IOException e) {
            System.out.println(e.getMessage()); // todo delete
//            return false;
        }
        return true;
    }
}
