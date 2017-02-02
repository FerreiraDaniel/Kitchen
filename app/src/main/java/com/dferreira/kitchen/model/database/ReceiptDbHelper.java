package com.dferreira.kitchen.model.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.dferreira.kitchen.model.database.entities.Receipt;

/**
 * Provides the necessary elements to access to the database
 */
public class ReceiptDbHelper extends SQLiteOpenHelper {

    private static final String CREATE_RECEIPTS_TABLE = "CREATE TABLE " + Receipt.TABLE_NAME + "(" +
            Receipt.ID_COLUMN_NAME + " INTEGER PRIMARY KEY     AUTOINCREMENT," +
            Receipt.LINK_COLUMN_NAME + " TEXT    NULL," +
            Receipt.DESCRIPTION_COLUMN_NAME + " TEXT    NULL," +
            Receipt.TITLE_COLUMN_NAME + " TEXT    NULL," +
            Receipt.CONTENT_ENCODED_COLUMN_NAME + " TEXT    NULL," +
            Receipt.THUMBNAIL_URL_COLUMN_NAME + " TEXT    NULL," +
            Receipt.THUMBNAIL_PATH_COLUMN_NAME + " TEXT    NULL" +
            ");";

    private static final String SQL_DELETE_ENTRIES = "DROP TABLE " + Receipt.TABLE_NAME + ";";


    // If you change the database schema, you must increment the database version.
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "receipts.db";

    /**
     * @param context to use to open or create the database
     */
    public ReceiptDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * Called when the database is created for the first time. This is where the
     * creation of tables and the initial population of the tables should happen.
     *
     * @param db The database.
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_RECEIPTS_TABLE);
    }

    /**
     * Called when the database needs to be upgraded. The implementation
     * should use this method to drop tables, add tables, or do anything else it
     * needs to upgrade to the new schema version.
     * <p>
     * <p>
     * The SQLite ALTER TABLE documentation can be found
     * <a href="http://sqlite.org/lang_altertable.html">here</a>. If you add new columns
     * you can use ALTER TABLE to insert them into a live table. If you rename or remove columns
     * you can use ALTER TABLE to rename the old table, then create the new table and then
     * populate the new table with the contents of the old table.
     * </p><p>
     * This method executes within a transaction.  If an exception is thrown, all changes
     * will automatically be rolled back.
     * </p>
     *
     * @param db         The database.
     * @param oldVersion The old database version.
     * @param newVersion The new database version.
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
// This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }
}
