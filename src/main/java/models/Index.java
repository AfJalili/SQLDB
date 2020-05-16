package models;

import java.io.Serializable;
import java.util.ArrayList;

public class Index{
    String primaryKeyFieldName;
    String primaryKeyDataType;
    int primaryKeyFieldIndex;
    ArrayList<Object> indexes = new ArrayList<>();

    public Index() {
    }

    public Index(String primaryKeyFieldName, String primaryKeyDataType, int primaryKeyFieldIndex) {
        this.primaryKeyFieldName = primaryKeyFieldName;
        this.primaryKeyDataType = primaryKeyDataType;
        this.primaryKeyFieldIndex = primaryKeyFieldIndex;
    }

    public String getPrimaryKeyFieldName() {
        return primaryKeyFieldName;
    }

    public void setPrimaryKeyFieldName(String primaryKeyFieldName) {
        this.primaryKeyFieldName = primaryKeyFieldName;
    }

    public String getPrimaryKeyDataType() {
        return primaryKeyDataType;
    }

    public void setPrimaryKeyDataType(String primaryKeyDataType) {
        this.primaryKeyDataType = primaryKeyDataType;
    }

    public int getPrimaryKeyFieldIndex() {
        return primaryKeyFieldIndex;
    }

    public void setPrimaryKeyFieldIndex(int primaryKeyFieldIndex) {
        this.primaryKeyFieldIndex = primaryKeyFieldIndex;
    }

    public ArrayList<Object> getIndexes() {
        return indexes;
    }

    public void setIndexes(ArrayList<Object> indexes) {
        this.indexes = indexes;
    }
}
