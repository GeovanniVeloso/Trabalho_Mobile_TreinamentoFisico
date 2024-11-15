package persistance;

import java.sql.SQLException;

public interface ITreinoAcademiaDAO {

    public TreinoAcademiaDAO open()throws SQLException;
    public void close()throws SQLException;

}
