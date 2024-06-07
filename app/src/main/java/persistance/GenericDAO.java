package persistance;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class GenericDAO extends SQLiteOpenHelper {

    private static final String Database = "Treino";

    private static final int Database_Ver = 1;

    private static final String Create_Table_Treino = "";

    private static final String Create_Table_TreinoCasa = "";

    private static final String Create_Table_TreinoAcademia = "";

    public GenericDAO(Context context){
        super(context, Database, null, Database_Ver);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Create_Table_Treino);
        db.execSQL(Create_Table_TreinoCasa);
        db.execSQL(Create_Table_TreinoAcademia);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        if(newVersion > oldVersion){
            db.execSQL("DROP TABLE IF EXISTS treino");
            db.execSQL("DROP TABLE IF EXISTS treinocasa");
            db.execSQL("DROP TABLE IF EXISTS treinoacademia");
            onCreate(db);
        }

    }
}
