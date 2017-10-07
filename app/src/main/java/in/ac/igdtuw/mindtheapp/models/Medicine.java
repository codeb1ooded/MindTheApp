package in.ac.igdtuw.mindtheapp.models;

import android.support.annotation.NonNull;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by megha on 07/10/17.
 */

public class Medicine implements Serializable{

    long id;
    String name;
    int times_in_day;
    int medicines_left;
    int days_left;
    int days_total;
    String disease;
    String medicine_description;
    @NonNull ArrayList<Time> times_of_medicine;

    public Medicine(long id, String name, int times_in_day, int medicines_left, int days_left, int days_total,
                    String disease, String medicine_description, @NonNull ArrayList<Time> times_of_medicine) {
        this.id = id;
        this.name = name;
        this.times_in_day = times_in_day;
        this.medicines_left = medicines_left;
        this.days_left = days_left;
        this.days_total = days_total;
        this.disease = disease;
        this.medicine_description = medicine_description;
        this.times_of_medicine = times_of_medicine;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getTimes_in_day() {
        return times_in_day;
    }

    public int getMedicines_left() {
        return medicines_left;
    }

    public int getDays_left() {
        return days_left;
    }

    public int getDays_total() {
        return days_total;
    }

    public String getDisease() {
        return disease;
    }

    public String getMedicine_description() {
        return medicine_description;
    }

    @NonNull
    public ArrayList<Time> getTimes_of_medicine() {
        return times_of_medicine;
    }

    public static class Time{
        long time_id;
        long medicine_id;
        String time_string;
        Date time;

        public Time(long time_id, long medicine_id, String time) {
            this.time_id = time_id;
            this.medicine_id = medicine_id;
            SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
            try {
                this.time = formatter.parse(time);
                this.time_string = this.time.toString();
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        public long getTime_id() {
            return time_id;
        }

        public long getMedicine_id() {
            return medicine_id;
        }

        public String getTime_string() {
            return time_string;
        }

        public Date getTime() {
            return time;
        }
    }

    public static class MedicineSchedule{
        long time_id;
        long medicine_id;
        String date_string;
        String medicine_name;
        String time;
        Date date;

        public MedicineSchedule(long time_id, long medicine_id, String date_string, String medicine_name, String time) {
            this.time_id = time_id;
            this.medicine_id = medicine_id;
            this.medicine_name = medicine_name;
            this.time = time;
            SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
            try {
                this.date = formatter.parse(date_string);
                this.date_string = this.date.toString();
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        public long getTime_id() {
            return time_id;
        }

        public long getMedicine_id() {
            return medicine_id;
        }

        public String getDate_string() {
            return date_string;
        }

        public String getMedicine_name() {
            return medicine_name;
        }

        public String getTime() {
            return time;
        }

        public Date getDate() {
            return date;
        }
    }
}
