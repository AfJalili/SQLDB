import DAO.DAO;
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
        final String JSON_INPUT_DIR = "E:\\projects\\Java\\APcodes\\my_database\\src\\main\\java\\jsonfiles\\";
        DAO dao = new Engine();
        ObjectMapper om = new ObjectMapper();
        File newTable_input = new File(JSON_INPUT_DIR + "newTable.json");
        File insert_input = new File(JSON_INPUT_DIR + "insert.json");
        try {
            TableInfo tableInfo = om.readValue(newTable_input, TableInfo.class);
            System.out.println(dao.createTable(tableInfo));
            InsertInfo insertInfo = om.readValue(insert_input, InsertInfo.class);
//            System.out.println(dao.insert(insertInfo));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
