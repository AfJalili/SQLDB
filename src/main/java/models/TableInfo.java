package models;

import java.io.Serializable;
import java.util.ArrayList;

public class TableInfo implements Serializable {
    private static final long serialVersionUID = 6529685098267757690L;
    String command;
    String tableName;
    int primaryKeyIndex; // it must be one of fields index , zero-based
    ArrayList<Field> fields;
    int sizeOfRow;


    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public int getPrimaryKeyIndex() {
        return primaryKeyIndex;
    }

    public void setPrimaryKeyIndex(int primaryKeyIndex) {
        this.primaryKeyIndex = primaryKeyIndex;
    }

    public ArrayList<Field> getFields() {
        return fields;
    }

    public void setFields(ArrayList<Field> fields) {
        this.fields = fields;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public int getSizeOfRow() {
        return sizeOfRow;
    }

    public void setSizeOfRow(int sizeOfRow) {
        this.sizeOfRow = sizeOfRow;
    }
}
