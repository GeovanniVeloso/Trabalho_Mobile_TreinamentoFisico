package persistence;

import java.sql.SQLException;

public interface ITreinoCasaDAO {

    public void open()throws SQLException;
    public void close()throws SQLException;
}
