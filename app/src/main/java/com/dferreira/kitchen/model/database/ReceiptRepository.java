package com.dferreira.kitchen.model.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.dferreira.kitchen.model.database.entities.Receipt;
import com.dferreira.kitchen.model.network.receipt.entities.ReceiptItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Set of methods used in order to save and retrieve data from database
 */
public class ReceiptRepository {

    private ReceiptDbHelper dbHelper;
    private SQLiteDatabase dbRead;
    private SQLiteDatabase dbWrite;

    /**
     * @param context to use to open or create the database
     */
    public ReceiptRepository(Context context) {
        this.dbHelper = new ReceiptDbHelper(context);
        this.dbRead = dbHelper.getReadableDatabase();
        this.dbWrite = dbHelper.getWritableDatabase();
    }

    /**
     * Creates on receipt using for that the information of a receiptItem
     *
     * @param receiptItem Receipt item (input)
     * @return The receipt created
     */
    public Receipt ReceiptItem2Receipt(ReceiptItem receiptItem) {
        Receipt receipt = new Receipt();

        receipt.link = receiptItem.link;
        receipt.description = receiptItem.description;
        receipt.title = receiptItem.title;
        receipt.contentEncoded = receiptItem.contentEncoded;
        receipt.thumbnailUrl = receiptItem.thumbnailUrl;
        return receipt;
    }

    /**
     * Save on receipt in database
     *
     * @param receipt The receipt to save
     */
    public long save(Receipt receipt) {
        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(Receipt.LINK_COLUMN_NAME, receipt.link);
        values.put(Receipt.DESCRIPTION_COLUMN_NAME, receipt.description);
        values.put(Receipt.TITLE_COLUMN_NAME, receipt.title);
        values.put(Receipt.CONTENT_ENCODED_COLUMN_NAME, receipt.contentEncoded);
        values.put(Receipt.THUMBNAIL_URL_COLUMN_NAME, receipt.thumbnailUrl);
        values.put(Receipt.THUMBNAIL_PATH_COLUMN_NAME, receipt.thumbnailPath);


        // Insert the new row, returning the primary key value of the new row
        long newId = dbWrite.insert(Receipt.TABLE_NAME, null, values);
        return newId;
    }

    /**
     * Makes a cursor to access to the database
     *
     * @param selection     The where element
     * @param selectionArgs The parameters to put to make the query
     * @param sortOrder     the sort parameter of the query
     * @return The Cursor to make the query
     */
    private Cursor getReceiptCursor(String selection, String[] selectionArgs, String sortOrder) {
        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String[] projection = {
                Receipt.ID_COLUMN_NAME,
                Receipt.LINK_COLUMN_NAME,
                Receipt.DESCRIPTION_COLUMN_NAME,
                Receipt.TITLE_COLUMN_NAME,
                Receipt.CONTENT_ENCODED_COLUMN_NAME,
                Receipt.THUMBNAIL_URL_COLUMN_NAME,
                Receipt.THUMBNAIL_PATH_COLUMN_NAME
        };


        Cursor cursor = dbRead.query(
                Receipt.TABLE_NAME,                     // The table to query
                projection,                               // The columns to return
                selection,                                // The columns for the WHERE clause
                selectionArgs,                            // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                sortOrder                                 // The sort order
        );

        return cursor;
    }

    /**
     * Gets the cursor and creates a receipt from it
     *
     * @param cursor Cursor where the information is going be read
     * @return The receipt as it was parsed
     */
    private Receipt parseReceiptCursor(Cursor cursor) {
        try {
            Receipt receipt = new Receipt();
            receipt.id = cursor.getLong(cursor.getColumnIndexOrThrow(Receipt.ID_COLUMN_NAME));
            receipt.link = cursor.getString(cursor.getColumnIndexOrThrow(Receipt.LINK_COLUMN_NAME));
            receipt.description = cursor.getString(cursor.getColumnIndexOrThrow(Receipt.DESCRIPTION_COLUMN_NAME));
            receipt.title = cursor.getString(cursor.getColumnIndexOrThrow(Receipt.TITLE_COLUMN_NAME));
            receipt.contentEncoded = cursor.getString(cursor.getColumnIndexOrThrow(Receipt.CONTENT_ENCODED_COLUMN_NAME));
            receipt.thumbnailUrl = cursor.getString(cursor.getColumnIndexOrThrow(Receipt.THUMBNAIL_URL_COLUMN_NAME));
            receipt.thumbnailPath = cursor.getString(cursor.getColumnIndexOrThrow(Receipt.THUMBNAIL_PATH_COLUMN_NAME));
            return receipt;
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

    /**
     * @param receiptId Identifier of the
     * @return The receipt with
     */
    public Receipt loadById(long receiptId) {
        String selection = Receipt.ID_COLUMN_NAME + " = ?";
        String[] selectionArgs = {Long.toString(receiptId)};
        Cursor cursor = getReceiptCursor(selection, selectionArgs, null);
        try {
            if (cursor.moveToNext()) {
                return parseReceiptCursor(cursor);
            } else {
                return null;
            }
        } finally {
            cursor.close();
        }
    }

    /**
     * @return Return all the receipts in database
     */
    public List<Receipt> getAll() {
        Cursor cursor = getReceiptCursor(null, null, null);
        try {
            List<Receipt> receipts = new ArrayList<>();
            while (cursor.moveToNext()) {
                receipts.add(parseReceiptCursor(cursor));
            }
            return receipts;
        } finally {
            cursor.close();
        }
    }


    /**
     * Closes this resource, relinquishing any underlying resources.
     * This method is invoked automatically on objects managed by the
     */
    public void close() {
        this.dbRead.close();
        this.dbWrite.close();
    }
}
