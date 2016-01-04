package merlini.mvcpattern.Database;

import android.content.ContentValues;
import android.database.Cursor;

/**
 * Created by mattia on 23/12/15.
 */
public abstract class QueryManager {
    private TableManager tableManager;

    public QueryManager(TableManager tableManager) {
        this.tableManager = tableManager;
    }

    /**
     * Query Manager for select queries
     *
     * @param distinct
     * @param columns
     * @param where
     * @param whereArgs
     * @param groupBy
     * @param having
     * @param orderBy
     * @param limit
     */
    public void select(boolean distinct, String[] columns, String where, String[] whereArgs, String groupBy, String having, String orderBy, String limit) {
        Cursor cursor = this.tableManager.select(distinct, columns, where, whereArgs, groupBy, having, orderBy, limit);
        this.onBeforeSelectIterations();
        while (cursor.moveToNext()) {
            this.onSelectIteration(cursor);
        }
        this.onAfterSelectIterations();
    }

    /**
     * Query Manager for insert queries
     *
     * @param values
     */
    public void insert(ContentValues values) {
        this.onInsertResult(this.tableManager.insert(values));
    }

    /**
     * Query Manager for update queries
     *
     * @param updatedValues
     * @param where
     * @param bindArray
     */
    public void update(ContentValues updatedValues, String where, String[] bindArray) {
        this.onUpdateResult(this.tableManager.update(updatedValues, where, bindArray));
    }

    /**
     * Query Manager for delete queries
     *
     * @param where
     * @param whereArgs
     */
    public void delete(String where, String[] whereArgs) {
        this.onDeleteResult(this.tableManager.delete(where, whereArgs));
    }

    /**
     * Code executed before the select loop.
     * Example:
     * //Code executed here
     * while(cursor.moveToNext()) {...}
     */
    protected abstract void onBeforeSelectIterations();

    /**
     * Code executed within the select loop.
     *
     * @param cursor Cursor
     *               <p/>
     *               Example:
     *               while(...)
     *               {
     *               //Code executed here
     *               //Ex: cursor.getString(cursor.getColumnsIndex("field");
     *               }
     */
    protected abstract void onSelectIteration(Cursor cursor);

    /**
     * Code executed after the select loop.
     * Example:
     * while(cursor.moveToNext()) {...}
     * //Code executed here
     */
    protected abstract void onAfterSelectIterations();

    /**
     * Code executed after the insert query has returned a boolean result
     *
     * @param insert boolean
     */
    protected abstract void onInsertResult(boolean insert);

    /**
     * Code executed after the update query has returned a boolean result
     *
     * @param update boolean
     */
    protected abstract void onUpdateResult(boolean update);

    /**
     * Code executed after the delete query has returned a boolean result
     *
     * @param delete boolean
     */
    protected abstract void onDeleteResult(boolean delete);
}
