package mydatabase;

import DAO.DAO;
import models.Field;
import models.Index;
import models.InsertInfo;
import models.TableInfo;
import utils.FileUtils;
import utils.JsonUtils;

import java.io.*;
import java.util.ArrayList;

public class Engine implements DAO {
    private final String DATABASE_PATH = "E:\\projects\\Java\\APcodes\\my_database\\my_database_sources\\";

    @Override
    public Boolean createTable(Object tableInfo) {
        TableInfo table = (TableInfo) tableInfo;
        // todo check if it exist already
        if (!makeDirs(table.getTableName())) { return false; }
        if (!createFiles(table)) { return false; }
        return true;
    }

    private boolean makeDirs(String tableName) {
        File file = new File(DATABASE_PATH + tableName);
        return file.mkdirs();
    }

    private boolean createFiles(TableInfo table) {
        try {
            File tableInfo = new File(DATABASE_PATH + table.getTableName() + "\\tableInfo.txt");
            tableInfo.createNewFile();
            if (!saveTableInfo(table)) { return false; }
            File data = new File(DATABASE_PATH + table.getTableName() + "\\data.txt");
            data.createNewFile();
            File index = new File(DATABASE_PATH + table.getTableName() + "\\index.json");
            index.createNewFile();
            if (!addPrimaryKeyInfoToIndexFile(table.getFields().get(table.getPrimaryKeyIndex()), table.getPrimaryKeyIndex(), index)) { return false; }

        } catch (IOException e) {
            System.out.println(e.getLocalizedMessage() + "   " + e.getMessage());
//            return false;
        }
        return true;
    }

    private boolean addPrimaryKeyInfoToIndexFile(Field primaryKey, int primaryKeyIndex, File indexFile) {
        Index index = new Index(primaryKey.getName(), primaryKey.getDataType(), primaryKeyIndex);
        String json = JsonUtils.parseToJson(index);
        if (!FileUtils.writeToFileUsingPrintWriter(indexFile, json)) { return false; }
        return true;
    }

    private boolean saveTableInfo(TableInfo table) {
        File file = new File(DATABASE_PATH + table.getTableName() + "\\tableInfo.txt");
        if (!file.exists()) { return false; }
            try {
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(file));
                objectOutputStream.writeObject(table);
                objectOutputStream.close();
            } catch (IOException e) {
                System.out.println(e.getMessage());
                return false;
            }
        return true;
    }

    @Override
    public Boolean insert(Object insertInfo) {
        InsertInfo insert = (InsertInfo) insertInfo;
        //  todo check if table exist
        TableInfo table = getTable(insert.getTableName());
        if(table == null) { return false; }
        // todo check data with table
        ArrayList<Object> formattedData = getFormattedData(insert.getData(), table.getFields());
        if (!updateIndexFile(insert.getData().get(table.getPrimaryKeyIndex()), insert.getTableName())) { return false; }
        if (!addToTable(formattedData, insert.getTableName())) { return false; }
        // todo calculate size of each row (next version)
        return true;
    }

    private TableInfo getTable(String tableName) {
        File file = new File(DATABASE_PATH + tableName + "\\tableInfo.txt");
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(file));
            TableInfo tableInfo = (TableInfo) objectInputStream.readObject();
            // todo delete following line after test
            System.out.println(tableInfo.getTableName() + "  " + tableInfo.getFields());
            return tableInfo;
        } catch (IOException | ClassNotFoundException e) {
            System.out.println(e.getMessage());

        }
        return null;
    }

    private ArrayList<Object> getFormattedData(ArrayList<Object> data, ArrayList<Field> fields) {
        ArrayList<Object> formattedData = new ArrayList<>();
        for (int i = 0; i < data.size(); i++) {
            if (data.get(i) == null) {
                formattedData.add(null);
                continue;
            }
            String dataType = fields.get(i).getDataType();
            switch (dataType) {
                case "String":
                    formattedData.add(addString(dataType, (String) data.get(i)));
                    break;
                case "Integer":
                    formattedData.add((Integer) data.get(i));
                    break;
                case "Double":
                    formattedData.add((Double) data.get(i));
                    break;
                case "Boolean":
                    formattedData.add((Boolean) data.get(i));
                    break;
                default:
                    formattedData.add(null);
                    break;
            }
        }
        return formattedData;
    }

    private String addString(String dataType, String data) { // todo debug this method. It has bug that cause null returning
        int size = Integer.parseInt(dataType.substring(7, dataType.length() - 1));
        StringBuilder result = null;
        if(size > data.length()) {
            result = new StringBuilder(data.replace(" ", "_"));
            result.append(" ".repeat(size - data.length()));
        } else if (size == data.length()) {
            result = new StringBuilder(data);
        } else if (size < data.length()) {
            System.out.println("invalid input for");
            result = new StringBuilder(data.substring(0, size));
        }
        return result.toString();
    }

    private <T> boolean updateIndexFile(T primaryKey, String tableName) {
        long beginIndex = FileUtils.getFileSize(DATABASE_PATH + tableName + "\\data.txt");
        if(beginIndex == -1) { return false; }
        if(!addIndex(beginIndex, primaryKey, tableName)) { return false; }
        return true;
    }



    private <T> boolean addIndex(long beginIndex,T primaryKey, String tableName) {
        Index index = JsonUtils.getJavaObj(DATABASE_PATH + tableName + "\\index.json", Index.class);
        if (index == null) { return false; }
        index.getIndexes().add(beginIndex);
        index.getIndexes().add(primaryKey);
        if (!JsonUtils.writeToFile(DATABASE_PATH + tableName + "\\index.json", index)) { return false; }
        return true;
    }

    private boolean addToTable(ArrayList<Object> data, String tableName) {
        File dataFile = new File(DATABASE_PATH + tableName + "\\data.txt");
        if (!dataFile.exists()) { return false; }
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(dataFile, true));
            objectOutputStream.writeObject(data);
            objectOutputStream.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
//            return false;
        }
        return true;
    }


    @Override
    public Boolean delete(Object deleteInfo) {

        return true;
    }

    @Override
    public Boolean update(Object updateInfo) {
        return null;
    }

    @Override
    public ArrayList<Object> find(Object findInfo) {
        return null;
    }

}
