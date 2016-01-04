package merlini.mvcpattern.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by mattia on 16/12/15.
 */
public class DatabaseHelper extends SQLiteOpenHelper {
    public static String CREATE_DATABASE =
            "CREATE TABLE Utenti" +
                    "(" +
                    "id INT PRIMARY KEY," +
                    "nome VARCHAR(300) NOT NULL," +
                    "cognome VARCHAR(300) NOT NULL," +
                    "email VARCHAR(300) NOT NULL," +
                    "psw VARCHAR(300) NOT NULL" +
                    ")";
    private String dbName;

    public DatabaseHelper(Context context, String dbName) {
        super(context, dbName, null, 1);
        this.dbName = dbName;
        //context.deleteDatabase(dbName);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Se all'avvio non trova nessun database già creato
        db.execSQL(DatabaseHelper.CREATE_DATABASE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
