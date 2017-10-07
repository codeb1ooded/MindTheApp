package in.ac.igdtuw.mindtheapp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import in.ac.igdtuw.mindtheapp.models.Medicine;

/**
 * Created by megha on 07/10/17.
 */

public class AbstractDBAdapter implements DatabaseContract{

    Context mContext;
    DatabaseHelper mdbHelper;
    SQLiteDatabase mDatabase;

    public AbstractDBAdapter(Context context){
        mContext = context;
        mdbHelper = new DatabaseHelper(mContext);
    }

    public AbstractDBAdapter open() throws SQLException {
        mDatabase = mdbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        if (mDatabase != null) {
            mDatabase.close();
        }
    }

    public void insertMedicine(Medicine medicine){
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME_MEDICINE_ID, medicine.getId());
        values.put(COLUMN_NAME_MEDICINE_NAME, medicine.getName());
        values.put(COLUMN_NAME_TIME_ID, medicine.getTimes_in_day());
        values.put(COLUMN_NAME_MEDICINES_LEFT, medicine.getMedicines_left());
        values.put(COLUMN_NAME_DAYS_LEFT, medicine.getDays_left());
        values.put(COLUMN_NAME_DAYS_TOTAL, medicine.getDays_total());
        values.put(COLUMN_NAME_DISEASE, medicine.getDisease());
        values.put(COLUMN_NAME_MEDICINE_DESCRIPTION, medicine.getMedicine_description());

        open();
        mDatabase.insert(TABLE_NAME_MEDICINE, null, values);
        close();

        for(Medicine.Time time: medicine.getTimes_of_medicine()){
            insertTime(time);
        }
    }

    public void insertTime(Medicine.Time time){
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME_TIME_ID, time.getTime_id());
        values.put(COLUMN_NAME_FOREIGN_MEDICINE_ID, time.getMedicine_id());
        values.put(COLUMN_NAME_TIME, time.getTime_string());
        open();
        mDatabase.insert(TABLE_NAME_MEDICINE, null, values);
        close();
    }

    public Medicine getMedicine(int id){
        String selectQuery = "SELECT  * FROM " + TABLE_NAME_MEDICINE + " WHERE " + COLUMN_NAME_MEDICINE_ID + " = " + id;
        Medicine medicine = null;

        open();
        Cursor cursor = mDatabase.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {

            ArrayList<Medicine.Time> times = getTimesByMedicine(id);

            medicine = new Medicine(
                    cursor.getInt(cursor.getColumnIndex(COLUMN_NAME_MEDICINE_ID)),
                    cursor.getString(cursor.getColumnIndex(COLUMN_NAME_MEDICINE_NAME)),
                    cursor.getInt(cursor.getColumnIndex(COLUMN_NAME_TIMES_IN_DAY)),
                    cursor.getInt(cursor.getColumnIndex(COLUMN_NAME_MEDICINES_LEFT)),
                    cursor.getInt(cursor.getColumnIndex(COLUMN_NAME_DAYS_LEFT)),
                    cursor.getInt(cursor.getColumnIndex(COLUMN_NAME_DAYS_TOTAL)),
                    cursor.getString(cursor.getColumnIndex(COLUMN_NAME_DISEASE)),
                    cursor.getString(cursor.getColumnIndex(COLUMN_NAME_MEDICINE_DESCRIPTION)),
                    times );
        }
        cursor.close();
        close();
        return medicine;
    }

    public ArrayList<Medicine.Time> getTimesByMedicine(int medicine_id){
        String selectQuery = "SELECT  * FROM " + TABLE_NAME_TIME + " WHERE " + COLUMN_NAME_FOREIGN_MEDICINE_ID + " = " + medicine_id;
        ArrayList<Medicine.Time> times = new ArrayList<>();

        open();
        Cursor cursor = mDatabase.rawQuery(selectQuery, null);
        while (cursor.moveToNext()) {

            Medicine.Time time = new Medicine.Time(
                    cursor.getInt(cursor.getColumnIndex(COLUMN_NAME_TIME_ID)),
                    medicine_id,
                    cursor.getString(cursor.getColumnIndex(COLUMN_NAME_TIME))
            );
            times.add(time);

        }
        cursor.close();
        close();
        return times;
    }

    public Medicine.Time getTime(int id){
        String selectQuery = "SELECT  * FROM " + TABLE_NAME_TIME + " WHERE " + COLUMN_NAME_FOREIGN_MEDICINE_ID + " = " + id;
        Medicine.Time time = null;

        open();
        Cursor cursor = mDatabase.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            time = new Medicine.Time(
                    id,
                    cursor.getInt(cursor.getColumnIndex(COLUMN_NAME_FOREIGN_MEDICINE_ID)),
                    cursor.getString(cursor.getColumnIndex(COLUMN_NAME_TIME))
            );
        }
        cursor.close();
        close();
        return time;
    }

    public boolean deleteMedicine(int id){
        open();
        boolean deleted = mDatabase.delete(TABLE_NAME_MEDICINE, COLUMN_NAME_MEDICINE_ID + "=" + id, null) > 0;
        deleted &= mDatabase.delete(TABLE_NAME_TIME, COLUMN_NAME_FOREIGN_MEDICINE_ID + "=" + id, null) > 0;
        close();
        return deleted;
    }

    public boolean deleteTime(int id){
        open();
        boolean deleted = mDatabase.delete(TABLE_NAME_TIME, COLUMN_NAME_TIME_ID + "=" + id, null) > 0;
        close();
        return deleted;
    }

    public long getNextMedicineId(){
        return DatabaseUtils.queryNumEntries(mDatabase,  TABLE_NAME_MEDICINE) + 1;
    }

    public long getNextTimeId(){
        return DatabaseUtils.queryNumEntries(mDatabase,  TABLE_NAME_TIME) + 1;
    }

}
