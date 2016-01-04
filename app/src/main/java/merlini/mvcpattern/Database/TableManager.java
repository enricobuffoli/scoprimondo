package merlini.mvcpattern.Database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

import merlini.mvcpattern.Base.Console;

/**
 * Created by mattia on 16/12/15.
 *
 * @author Mattia Merlini
 */
public class TableManager {
    private String tableName;
    private DatabaseHelper databaseHelper;

    public TableManager(String tableName, DatabaseHelper databaseHelper) {
        this.tableName = tableName;
        this.databaseHelper = databaseHelper;
    }

    /**
     * Retrieves records from database
     * Example:
     * ...select(false, String[] {"id", "name"},
     * "id=? AND name=?",
     * String[] {"10", "mattia"},
     * "name", null, "name, id", "0, 100");
     * Explained:
     * SELECT id, name FROM tableName WHERE id="10" AND name="mattia" GROUP BY name ORDER BY name, id LIMIT 0, 100;
     */
    synchronized public Cursor select(boolean distinct, String[] columns, String where, String[] whereArgs, String groupBy, String having, String orderBy, String limit) {
        Cursor cursor = null;
        try {
            SQLiteDatabase db = this.databaseHelper.getReadableDatabase();
            cursor = db.query(distinct, this.tableName, columns, where, whereArgs, groupBy, having, orderBy, limit);
        } catch (SQLiteException e) {
            Console.log(e);
        }
        return cursor;
    }

    /**
     * Inserts records to database
     * Example:
     * ContentValues v = new ContentValues();
     * v.put("id", "10");
     * v.put("name", "mattia");
     * ...insert(v);
     * Explained: INSERT INTO tableName (id, name) VALUES ("10", "mattia");
     */
    synchronized public boolean insert(ContentValues values) {
        try (SQLiteDatabase db = this.databaseHelper.getWritableDatabase()) {
            db.insert(this.tableName, null, values);
            return true;
        } catch (SQLiteException e) {
            Console.log(e);
            return false;
        }
    }

    /**
     * Updates records to database
     * Example:
     * ContentValues v = new ContentValues();
     * v.put("id", "10");
     * v.put("name", "mattia");
     * ...update(v, "id=? AND name=?", String[] {"11", "merlini"});
     * Explained: UPDATE tableName SET id=10, name="mattia" WHERE id="11" AND name="merlini";
     */
    synchronized public boolean update(ContentValues updatedValues, String where, String[] bindArray) {
        try (SQLiteDatabase db = this.databaseHelper.getWritableDatabase()) {
            db.update("Tabella", updatedValues, where, bindArray);
            return true;
        } catch (SQLiteException e) {
            Console.log(e);
            return false;
        }
    }

    /**
     * Deletes records to database
     * Example:
     * ...delete("id=? AND name=?", String[] {"10", "mattia"});
     * Explained: DELETE FROM tableName WHERE id=10 AND name="mattia";
     */
    synchronized public boolean delete(String where, String[] whereArgs) {
        try (SQLiteDatabase db = this.databaseHelper.getWritableDatabase()) {
            db.delete(this.tableName, where, whereArgs);
            return true;
        } catch (SQLiteException e) {
            Console.log(e);
            return false;
        }
    }
}
