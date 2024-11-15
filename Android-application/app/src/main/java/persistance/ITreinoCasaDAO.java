package persistance;

import java.sql.SQLException;

public interface ITreinoCasaDAO {

    public TreinoCasaDAO open()throws SQLException;
    public void close()throws SQLException;
}
