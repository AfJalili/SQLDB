package DAO;

import java.util.ArrayList;

public interface DAO {
    public Boolean createTable(Object tableInfo);
    public Boolean insert(Object insertInfo);
    public Boolean delete(Object deleteInfo);
    public Boolean update(Object updateInfo);
    public ArrayList<Object> find(Object findInfo);


}
