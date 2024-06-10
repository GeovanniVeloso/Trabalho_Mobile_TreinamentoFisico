package persistance;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import model.TreinoAcademia;

public class TreinoAcademiaDAO implements ITreinoAcademiaDAO, ICRUD<TreinoAcademia> {

    private final Context context;
    private GenericDAO genericDAO;
    private SQLiteDatabase db;

    public TreinoAcademiaDAO(Context context) {
        this.context = context;
    }

    @Override
    public TreinoAcademiaDAO open() throws SQLException {
        genericDAO = new GenericDAO(context);
        db = genericDAO.getWritableDatabase();
        db.execSQL("PRAGMA foreign_keys=ON;");
        return this;
    }

    private static ContentValues getContentValues( TreinoAcademia TA, boolean Super){
        ContentValues ca = new ContentValues();
        if(Super){
            ca.put("id", TA.getId());
            ca.put("date", TA.getDate().toString());
            ca.put("muscularGroup", TA.getMuscularGroup());
            ca.put("exercises", TA.getExercises());
        }else{
            ca.put("id", TA.getId());
            ca.put("academia", TA.getAcademia());
        }
        return ca;
    }
    @Override
    public void close() throws SQLException {
        genericDAO.close();
    }

    @Override
    public void insert(TreinoAcademia treinoAcademia) throws SQLException {
        db.insert("treino",null, getContentValues(treinoAcademia, true));
        db.insert("treinoacademia",null, getContentValues(treinoAcademia, false));
    }

    @Override
    public void update(TreinoAcademia treinoAcademia) throws SQLException {
        db.update("treino", getContentValues(treinoAcademia, true),
                "id = " + treinoAcademia.getId(), null);
        db.update("treinoacademia", getContentValues(treinoAcademia, false),
                "Id = " + treinoAcademia.getId(), null);
    }

    @Override
    public void delete(TreinoAcademia treinoAcademia) throws SQLException {
        db.delete("treinoacademia","id = " + treinoAcademia.getId(), null);
        db.delete("treino","id = " + treinoAcademia.getId(), null);
    }

    @SuppressLint("Range")
    @Override
    public TreinoAcademia findOne(TreinoAcademia treinoAcademia) throws SQLException {
        String query = "SELECT " +
                "treino.id , treino.date , treino.muscularGroup , treino.exercises , treinoacademia.academia " +
                "FROM treino, treinoacademia " +
                "WHERE treino.id = treinoacademia.id " +
                "AND treino.id = " + treinoAcademia.getId();

        Cursor cursor = db.rawQuery(query , null);

        if(cursor != null){
            cursor.moveToFirst();
        }

        if(!cursor.isAfterLast()){
            treinoAcademia.setId(cursor.getInt(cursor.getColumnIndex("id")));
            treinoAcademia.setDate(LocalDate.parse(cursor.getString(cursor.getColumnIndex("date"))));
            treinoAcademia.setMuscularGroup(cursor.getString(cursor.getColumnIndex("muscularGroup")));
            treinoAcademia.setExercises(cursor.getString(cursor.getColumnIndex("exercises")));
            treinoAcademia.setAcademia(cursor.getString(cursor.getColumnIndex("academia")));
        }

        cursor.close();

        return treinoAcademia;
    }

    @SuppressLint("Range")
    @Override
    public List<TreinoAcademia> findAll() throws SQLException {
        List<TreinoAcademia> Treinos = new ArrayList<>();

        String query = "SELECT " +
                "treino.id , treino.date , treino.muscularGroup , treino.exercises , treinoacademia.academia " +
                "FROM treino, treinoacademia " +
                "WHERE treino.id = treinoacademia.id ";

        Cursor cursor = db.rawQuery(query , null);

        if(cursor != null){
            cursor.moveToFirst();
        }

        while(!cursor.isAfterLast()){
            TreinoAcademia treinoAcademia = new TreinoAcademia();
            treinoAcademia.setId(cursor.getInt(cursor.getColumnIndex("id")));
            treinoAcademia.setDate(LocalDate.parse(cursor.getString(cursor.getColumnIndex("date"))));
            treinoAcademia.setMuscularGroup(cursor.getString(cursor.getColumnIndex("muscularGroup")));
            treinoAcademia.setExercises(cursor.getString(cursor.getColumnIndex("exercises")));
            treinoAcademia.setAcademia(cursor.getString(cursor.getColumnIndex("academia")));

            Treinos.add(treinoAcademia);

            cursor.moveToNext();
        }

        cursor.close();

        return Treinos;
    }
}
