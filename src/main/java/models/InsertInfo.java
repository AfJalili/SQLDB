package models;

import java.io.Serializable;
import java.util.ArrayList;

public class InsertInfo implements Serializable {
    String  command;
    String tableName;
    ArrayList<Object> data;

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public ArrayList<Object> getData() {
        return data;
    }

    public void setData(ArrayList<Object> data) {
        this.data = data;
    }
}
