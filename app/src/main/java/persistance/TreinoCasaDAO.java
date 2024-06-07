package persistance;

import java.sql.SQLException;
import java.util.List;

import model.TreinoCasa;

public class TreinoCasaDAO implements ITreinoCasaDAO, ICRUD<TreinoCasa> {
    @Override
    public TreinoCasaDAO open() throws SQLException {
        return null;
    }

    @Override
    public void close() throws SQLException {

    }

    @Override
    public void insert(TreinoCasa treinoCasa) throws SQLException {

    }

    @Override
    public int update(TreinoCasa treinoCasa) throws SQLException {
        return 0;
    }

    @Override
    public void delete(TreinoCasa treinoCasa) throws SQLException {

    }

    @Override
    public TreinoCasa findOne(TreinoCasa treinoCasa) throws SQLException {
        return null;
    }

    @Override
    public List<TreinoCasa> findAll() throws SQLException {
        return null;
    }
}
