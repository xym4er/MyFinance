package com.chornyiua.myfinance.adapters;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by ChornyiUA on 12.04.2016.
 */
public class DataBaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "dbmain";
    public static final int DATABASE_VERSION = 1;
    public static final String BILL_TABLE = "bill";
    public static final String CATEGORY_TABLE = "category";

    public static final String BILL_KEY_ID = "_id";
    public static final String BILL_DATE = "date";
    public static final String BILL_CATEGORY_ID = "category_id";
    public static final String BILL_CHANGE = "change";
    public static final String BILL_VALUE = "value";
    public static final String BILL_COMMENT = "comment";

    public static final String CATEGORY_KEY_ID = "_id";
    public static final String CATEGORY_NAME = "name";
    Context context;

    public DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + BILL_TABLE + " ("
                + BILL_KEY_ID + " integer primary key autoincrement,"
                + BILL_DATE + " integer,"
                + BILL_CATEGORY_ID + " integer,"
                + BILL_CHANGE + " integer,"
                + BILL_VALUE + " integer,"
                + BILL_COMMENT + " text" + ");");

        db.execSQL("create table " + CATEGORY_TABLE + " ("
                + CATEGORY_KEY_ID + " integer primary key autoincrement,"
                + CATEGORY_NAME + " text" + ");");

        db.execSQL("INSERT INTO "+CATEGORY_TABLE+" VALUES (1,'Products1');");
        db.execSQL("INSERT INTO "+CATEGORY_TABLE+" VALUES (2,'Products2');");
        db.execSQL("INSERT INTO "+CATEGORY_TABLE+" VALUES (3,'Products3');");
        db.execSQL("INSERT INTO "+CATEGORY_TABLE+" VALUES (4,'Products4');");
        db.execSQL("INSERT INTO "+BILL_TABLE+" ("+BILL_DATE+","+BILL_CATEGORY_ID+","+BILL_CHANGE+","+BILL_VALUE+","+BILL_COMMENT+") VALUES ("+System.currentTimeMillis()+",1,0,0,'');");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + BILL_TABLE);
        db.execSQL("drop table if exists " + CATEGORY_TABLE);
        onCreate(db);
    }

    public long insertTransaction(int categoryID, int change, int currentValue, String comment) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(BILL_DATE, System.currentTimeMillis() / 1000);
        contentValues.put(BILL_CATEGORY_ID, categoryID);
        contentValues.put(BILL_CHANGE, change);
        contentValues.put(BILL_VALUE, currentValue+change);
        contentValues.put(BILL_COMMENT, comment);
        long id = database.insert(BILL_TABLE, null, contentValues);
        database.close();
        return id;
    }
}