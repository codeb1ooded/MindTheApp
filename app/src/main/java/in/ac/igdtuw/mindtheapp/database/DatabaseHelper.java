package in.ac.igdtuw.mindtheapp.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by megha on 07/10/17.
 */

public class DatabaseHelper extends SQLiteOpenHelper implements DatabaseContract{

    static int version = 1;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_MEDICINE_TABLE = "CREATE TABLE " + TABLE_NAME_MEDICINE + "(" +
                COLUMN_NAME_MEDICINE_ID + " INTEGER PRIMARY KEY ," +
                COLUMN_NAME_MEDICINE_NAME + " STRING," +
                COLUMN_NAME_TIMES_IN_DAY + " INTEGER," +
                COLUMN_NAME_MEDICINES_LEFT + " INTEGER," +
                COLUMN_NAME_DAYS_LEFT + " INTEGER," +
                COLUMN_NAME_DAYS_TOTAL + " INTEGER," +
                COLUMN_NAME_MEDICINE_DESCRIPTION + " TEXT, " +
                COLUMN_NAME_DISEASE + " STRING" +
                ")";

        String CREATE_TIME_TABLE = "CREATE TABLE " + TABLE_NAME_TIME + "(" +
                COLUMN_NAME_TIME_ID + " INTEGER PRIMARY KEY ," +
                COLUMN_NAME_FOREIGN_MEDICINE_ID + " INTEGER," +
                COLUMN_NAME_TIME + " STRING" +
                ")";

        String CREATE_MEDICINE_TAKEN_TABLE = "CREATE TABLE " + TABLE_NAME_DATE_TIME_MEDICINE_TAKEN + "(" +
                COLUMN_NAME_DATE_TIME_MEDICINE_ID + " INTEGER ," +
                COLUMN_NAME_DATE_TIME_TIME_ID + " INTEGER ," +
                COLUMN_NAME_DATE_TIME_MEDICINE_NAME + " STRING, " +
                COLUMN_NAME_DATE_TIME_TIME + " STRING, " +
                COLUMN_NAME_DATE + " STRING" +
                ")";

        db.execSQL(CREATE_MEDICINE_TABLE);
        db.execSQL(CREATE_TIME_TABLE);
        db.execSQL(CREATE_MEDICINE_TAKEN_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) {}

}
