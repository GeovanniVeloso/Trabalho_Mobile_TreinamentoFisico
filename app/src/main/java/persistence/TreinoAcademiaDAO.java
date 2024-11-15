package persistence;

import android.annotation.SuppressLint;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import model.TreinoAcademia;

public class TreinoAcademiaDAO implements ITreinoAcademiaDAO, ICRUD<TreinoAcademia> {

    private GenericDAO gDAO;
    private Connection connection;

    public TreinoAcademiaDAO(GenericDAO gDAO){
        this.gDAO = gDAO;
    }

    @Override
    public void open() throws SQLException {
        if (connection.isClosed()) {
            connection = gDAO.getConnection();
        }
    }

    @Override
    public void close() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }

    @Override
    public void insert(TreinoAcademia treinoAcademia) throws SQLException {
        open();
        String SQL = "INSERT INTO treino (id, date, muscularGroup, exercises) VALUES (?, ?, ?, ?)";
        PreparedStatement ps = connection.prepareStatement(SQL);
        ps.setInt(1, treinoAcademia.getId());
        ps.setString(2, treinoAcademia.getDate().toString());
        ps.setString(3, treinoAcademia.getMuscularGroup());
        ps.setString(4, treinoAcademia.getExercises());
        ps.execute();
        ps.close();
        SQL = "INSERT INTO treinoacademia (id, academia) VALUES (?, ?)";
        ps = connection.prepareStatement(SQL);
        ps.setInt(1, treinoAcademia.getId());
        ps.setString(2, treinoAcademia.getAcademia());
        ps.execute();
        ps.close();
        close();
    }

    @Override
    public void update(TreinoAcademia treinoAcademia) throws SQLException {
        open();
        String SQL = "UPDATE treino SET date = ?, muscularGroup = ?, exercises = ? WHERE id = ?";
        PreparedStatement ps = connection.prepareStatement(SQL);
        ps.setInt(1, treinoAcademia.getId());
        ps.setString(2, treinoAcademia.getDate().toString());
        ps.setString(3, treinoAcademia.getMuscularGroup());
        ps.setString(4, treinoAcademia.getExercises());
        ps.execute();
        ps.close();
        SQL = "UPDATE treinoacademia SET academia = ? WHERE id = ?";
        ps = connection.prepareStatement(SQL);
        ps.setInt(1, treinoAcademia.getId());
        ps.setString(2, treinoAcademia.getAcademia());
        ps.execute();
        ps.close();
        close();
    }

    @Override
    public void delete(TreinoAcademia treinoAcademia) throws SQLException {
        open();
        String SQL = "DELETE FROM treino WHERE id = ?";
        PreparedStatement ps = connection.prepareStatement(SQL);
        ps.setInt(1, treinoAcademia.getId());
        ps.execute();
        ps.close();
        close();
    }

    @SuppressLint("Range")
    @Override
    public TreinoAcademia findOne(TreinoAcademia treinoAcademia) throws SQLException {
        TreinoAcademia ta = new TreinoAcademia();
        String query = "SELECT " +
                "treino.id , treino.date , treino.muscularGroup , treino.exercises , treinoacademia.academia " +
                "FROM treino, treinoacademia " +
                "WHERE treino.id = treinoacademia.id " +
                "AND treino.id = " + treinoAcademia.getId();

        PreparedStatement ps = connection.prepareStatement(query);
        ps.setInt(1, treinoAcademia.getId());
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            ta.setId(rs.getInt("id"));
            ta.setDate(LocalDate.parse(rs.getString("date")));
            ta.setMuscularGroup(rs.getString("muscularGroup"));
            ta.setExercises(rs.getString("exercises"));
            ta.setAcademia(rs.getString("academia"));
        }
        rs.close();
        ps.close();
        connection.close();
        return ta;
    }

    @SuppressLint("Range")
    @Override
    public List<TreinoAcademia> findAll() throws SQLException {
        List<TreinoAcademia> Treinos = new ArrayList<>();
        open();

        String query = "SELECT " +
                "treino.id , treino.date , treino.muscularGroup , treino.exercises , treinoacademia.academia " +
                "FROM treino, treinoacademia " +
                "WHERE treino.id = treinoacademia.id ";

        PreparedStatement ps = connection.prepareStatement(query);
        ResultSet rs = ps.executeQuery();

        while(rs.next()){
            TreinoAcademia treinoAcademia = new TreinoAcademia();
            treinoAcademia.setId(rs.getInt("id"));
            treinoAcademia.setDate(LocalDate.parse(rs.getString("date")));
            treinoAcademia.setMuscularGroup(rs.getString("muscularGroup"));
            treinoAcademia.setExercises(rs.getString("exercises"));
            treinoAcademia.setAcademia(rs.getString("academia"));
            Treinos.add(treinoAcademia);
        }
        rs.close();
        ps.close();
        connection.close();
        return Treinos;
    }
}