package persistence;

import java.sql.SQLException;

public interface ITreinoAcademiaDAO {

    public void open()throws SQLException;
    public void close()throws SQLException;

}
