package persistence;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.Cursor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import model.TreinoCasa;

public class TreinoCasaDAO implements ITreinoCasaDAO, ICRUD<TreinoCasa> {

    private GenericDAO gDAO;
    private Connection connection;

    public TreinoCasaDAO(GenericDAO gDao) {
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

    private static ContentValues getContentValues(TreinoCasa TC, boolean Super){
        ContentValues cv = new ContentValues();
        if(Super){
            cv.put("id", TC.getId());
            cv.put("date", TC.getDate().toString());
            cv.put("muscularGroup", TC.getMuscularGroup());
            cv.put("exercises", TC.getExercises());
        }else{
            cv.put("id", TC.getId());
            cv.put("time", TC.getTime());
        }
        return cv;
    }

    @Override
    public void insert(TreinoCasa treinoCasa) throws SQLException {
        open();
        String SQL = "INSERT INTO treino (id, date, muscularGroup, exercises)Values(?,?,?,?)";
        PreparedStatement ps = connection.prepareStatement(SQL);
        ps.setInt(1, treinoCasa.getId());
        ps.setString(2, treinoCasa.getDate().toString());
        ps.setString(3, treinoCasa.getMuscularGroup());
        ps.setString(4, treinoCasa.getExercises());
        ps.execute();
        ps.close();
        SQL = "INSERT INTO treinocasa (id, time) Values (?,?);";
        ps.setInt(1,treinoCasa.getId());
        ps.setInt(2, treinoCasa.getTime());
        ps.execute();
        ps.close();
    }

    @Override
    public void update(TreinoCasa treinoCasa) throws SQLException {
        open();
        String SQL = "UPDATE treino SET date = ?, muscularGroup = ?, exercises = ? WHERE id = ?";
        PreparedStatement ps = connection.prepareStatement(SQL);
        ps.setString(1, treinoCasa.getDate().toString());
        ps.setString(2, treinoCasa.getMuscularGroup());
        ps.setString(3, treinoCasa.getExercises());
        ps.setInt(4, treinoCasa.getId());
        ps.execute();
        ps.close();
        SQL = "UPDATE treinocasa SET time = ? WHERE id = ?;";
        ps.setInt(1,treinoCasa.getTime());
        ps.setInt(2, treinoCasa.getId());
        ps.execute();
        ps.close();
    }

    @Override
    public void delete(TreinoCasa treinoCasa) throws SQLException {
        open();
        String SQL = "DELETE treinocasa WHERE id = ?";
        PreparedStatement ps = connection.prepareStatement(SQL);
        ps.setInt(1, treinoCasa.getId());
        ps.execute();
        ps.close();
        SQL = "DELETE treino WHERE id = ?";
        ps.setInt(1, treinoCasa.getId());
        ps.execute();
        ps.close();
    }

    @SuppressLint("Range")
    @Override
    public TreinoCasa findOne(TreinoCasa treinoCasa) throws SQLException {
        TreinoCasa tc = new TreinoCasa();
        String SQL = "SELECT T.id AS id, T.date AS DATA, " +
                "T.muscularGroup AS GRUPO MUSCULAR," +
                " T.exercises AS EXERCICIOS, TC.time AS TEMPO " +
                "FROM treino T, treinocasa TC WHERE T.id = TC.id AND TC.id = ?";

        PreparedStatement ps = connection.prepareStatement(SQL);
        ps.setInt(1, treinoCasa.getId());
        ResultSet rs = ps.executeQuery();
        if(rs.next()){

            tc.setId(rs.getInt("id"));
            tc.setDate(LocalDate.parse(rs.getString("DATA")));
            tc.setMuscularGroup(rs.getString("GRUPO MUSCULAR"));
            tc.setExercises(rs.getString("EXERCICIOS"));
            tc.setTime(rs.getInt("TEMPO"));
        }

        rs.close();
        ps.close();
        connection.close();
        return tc;

    }

    @SuppressLint("Range")
    @Override
    public List<TreinoCasa> findAll() throws SQLException {
        List<TreinoCasa> Treinos = new ArrayList<>();

        TreinoCasa tc = new TreinoCasa();
        String SQL = "SELECT T.id AS id, T.date AS DATA, " +
                "T.muscularGroup AS GRUPO MUSCULAR," +
                " T.exercises AS EXERCICIOS, TC.time AS TEMPO " +
                "FROM treino T, treinocasa TC WHERE T.id = TC.id";

        PreparedStatement ps = connection.prepareStatement(SQL);
        ResultSet rs = ps.executeQuery();
        while(rs.next()){

            tc.setId(rs.getInt("id"));
            tc.setDate(LocalDate.parse(rs.getString("DATA")));
            tc.setMuscularGroup(rs.getString("GRUPO MUSCULAR"));
            tc.setExercises(rs.getString("EXERCICIOS"));
            tc.setTime(rs.getInt("TEMPO"));

            Treinos.add(tc);
        }

        rs.close();
        ps.close();
        connection.close();
        return Treinos;
    }
}
