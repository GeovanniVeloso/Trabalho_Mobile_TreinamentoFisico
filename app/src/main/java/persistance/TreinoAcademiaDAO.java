package persistance;

import java.sql.SQLException;
import java.util.List;

import model.TreinoAcademia;
import model.TreinoCasa;

public class TreinoAcademiaDAO implements ITreinoAcademiaDAO, ICRUD<TreinoAcademia> {
    @Override
    public TreinoAcademiaDAO open() throws SQLException {
        return null;
    }

    @Override
    public void close() throws SQLException {

    }

    @Override
    public void insert(TreinoAcademia treinoAcademia) throws SQLException {

    }

    @Override
    public int update(TreinoAcademia treinoAcademia) throws SQLException {
        return 0;
    }

    @Override
    public void delete(TreinoAcademia treinoAcademia) throws SQLException {

    }

    @Override
    public TreinoAcademia findOne(TreinoAcademia treinoAcademia) throws SQLException {
        return null;
    }

    @Override
    public List<TreinoAcademia> findAll() throws SQLException {
        return null;
    }
}
