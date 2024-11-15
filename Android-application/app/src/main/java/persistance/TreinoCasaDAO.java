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

import model.TreinoCasa;

public class TreinoCasaDAO implements ITreinoCasaDAO, ICRUD<TreinoCasa> {

    private final Context context;
    private GenericDAO genericDAO;
    private SQLiteDatabase db;

    public TreinoCasaDAO(Context context) {
        this.context = context;
    }

    @Override
    public TreinoCasaDAO open() throws SQLException {
        genericDAO = new GenericDAO(context);
        db = genericDAO.getWritableDatabase();
        db.execSQL("PRAGMA foreign_keys=ON;");
        return this;
    }

    @Override
    public void close() throws SQLException {
        genericDAO.close();
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
        db.insert("treino", null, getContentValues(treinoCasa, true));
        db.insert("treinocasa", null, getContentValues(treinoCasa, false));
    }

    @Override
    public void update(TreinoCasa treinoCasa) throws SQLException {
        db.update("treino", getContentValues(treinoCasa, true),"id = " + treinoCasa.getId(), null);
        db.update("treinocasa", getContentValues(treinoCasa, false),"id = " + treinoCasa.getId(), null);
    }

    @Override
    public void delete(TreinoCasa treinoCasa) throws SQLException {
        db.delete("treinocasa","id = " + treinoCasa.getId(), null);
        db.delete("treino", "id = " + treinoCasa.getId(), null);
    }

    @SuppressLint("Range")
    @Override
    public TreinoCasa findOne(TreinoCasa treinoCasa) throws SQLException {

        TreinoCasa tc = new TreinoCasa();

        String query = "SELECT " +
                "t.id , t.date , t.muscularGroup , t.exercises , c.time " +
                "FROM treino t, treinocasa c " +
                "WHERE t.id = c.id " +
                "AND t.id = " + treinoCasa.getId();

        Cursor cursor = db.rawQuery(query, null);

        if(cursor != null){
            cursor.moveToFirst();
        }

        if(!cursor.isAfterLast()){
            tc.setId(cursor.getInt(cursor.getColumnIndex("id")));
            tc.setDate(LocalDate.parse(cursor.getString(cursor.getColumnIndex("date"))));
            tc.setMuscularGroup(cursor.getString(cursor.getColumnIndex("muscularGroup")));
            tc.setExercises(cursor.getString(cursor.getColumnIndex("exercises")));
            tc.setTime(cursor.getInt(cursor.getColumnIndex("time")));
        }

        cursor.close();

        return tc;
    }

    @SuppressLint("Range")
    @Override
    public List<TreinoCasa> findAll() throws SQLException {
        List<TreinoCasa> Treinos = new ArrayList<>();

        String query = "SELECT " +
                "treino.id , treino.date , treino.muscularGroup , treino.exercises , treinocasa.time " +
                "FROM treino , treinocasa " +
                "WHERE treino.id = treinocasa.id ";

        Cursor cursor = db.rawQuery(query, null);

        if(cursor != null){
            cursor.moveToFirst();
        }

        while(!cursor.isAfterLast()){
            TreinoCasa treinoCasa = new TreinoCasa();
            treinoCasa.setId(cursor.getInt(cursor.getColumnIndex("id")));
            treinoCasa.setDate(LocalDate.parse(cursor.getString(cursor.getColumnIndex("date"))));
            treinoCasa.setMuscularGroup(cursor.getString(cursor.getColumnIndex("muscularGroup")));
            treinoCasa.setExercises(cursor.getString(cursor.getColumnIndex("exercises")));
            treinoCasa.setTime(cursor.getInt(cursor.getColumnIndex("time")));

            Treinos.add(treinoCasa);

            cursor.moveToNext();
        }

        cursor.close();

        return Treinos;
    }
}
