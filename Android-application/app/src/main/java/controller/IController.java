package controller;

import java.sql.SQLException;
import java.util.List;

public interface IController <T>{

        public void insert(T t) throws SQLException;
        public void update(T t) throws SQLException;
        public void delete(T t) throws SQLException;
        public T search(T t) throws SQLException;
        public List<T> list() throws SQLException;

}
