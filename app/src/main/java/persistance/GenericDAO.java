package persistance;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class GenericDAO extends SQLiteOpenHelper {

    private static final String Database = "Treino";

    private static final int Database_Ver = 1;

    private static final String Create_Table_Treino = "CREATE TABLE treino(id INTEGER(10) NOT NULL PRIMARY KEY,date VARCHAR(8) NOT NULL,muscularGroup VARCHAR(30) NOT NULL, exercises VARCHAR(100) NOT NULL);";

    private static final String Create_Table_TreinoCasa = "CREATE TABLE treinocasa(time INTEGER(10) NOT NULL,id INTEGER(10), FOREIGN KEY (id) REFERENCES treino (id));";

    private static final String Create_Table_TreinoAcademia = "CREATE TABLE treinoacademia(academia VARCHAR(30) NOT NULL,id INTEGER(10),FOREIGN KEY (id) REFERENCES treino (id));";

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
