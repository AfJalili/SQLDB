package DAO;

import java.util.ArrayList;

public interface DAO {
    public <T> Boolean createTable(T tableInfo);
    public <T> Boolean insert(T insertInfo);
    public <T> Boolean delete(T deleteInfo);
    public <T> Boolean update(T updateInfo);
    public <T, E> ArrayList<E> find(T findInfo); // I'm not sure how it works!!


}
