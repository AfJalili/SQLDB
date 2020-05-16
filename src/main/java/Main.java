import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import models.InsertInfo;
import models.TableInfo;
import mydatabase.Engine;

import java.io.*;
import java.util.ArrayList;
import java.util.Objects;

public class Main {
    public static void main(String[] args) {
        System.out.println("this will be my awesome database");
        Engine engine = new Engine();
        ObjectMapper om = new ObjectMapper();
        String s = "{\n" +
                "  \"command\" : \"CREATE TABLE\",\n" +
                "  \"tableName\" : \"test\",\n" +
                "  \"primaryKeyIndex\" : 2,\n" +
                "  \"fields\" : [\n" +
                "    {\n" +
                "      \"name\" : \"field\",\n" +
                "      \"dataType\" : \"String(45)\"\n" +
                "    },\n" +
                "    {\n" +
                "    \"name\": \"f2\",\n" +
                "    \"dataType\" : \"Integer\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"f3\",\n" +
                "      \"dataType\" : \"Boolean\"\n" +
                "    }\n" +
                "  ]\n" +
                "}";
        String insert = "{\n" +
                "  \"command\" : \"INSERT INTO\",\n" +
                "  \"tableName\" : \"test2\",\n" +
                "  \"data\" : [\n" +
                "    \"value1\",\n" +
                "    344,\n" +
                "    true\n" +
                "  ]\n" +
                "}";
        try {
//            TableInfo tableInfo = om.readValue(s, TableInfo.class);
//            System.out.println(engine.createTable(tableInfo));
            InsertInfo insertInfo = om.readValue(insert, InsertInfo.class);
            System.out.println(engine.insert(insertInfo));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
