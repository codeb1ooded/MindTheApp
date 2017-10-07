package in.ac.igdtuw.mindtheapp;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import in.ac.igdtuw.mindtheapp.database.AbstractDBAdapter;
import in.ac.igdtuw.mindtheapp.models.Medicine;


/**
 * A simple {@link Fragment} subclass.
 */
public class ScheduleFragment extends AppCompatActivity {

    TextView tvDate,tvMedLeft,tvTotalMed;
    RecyclerView recViewSchedule;
    ArrayList<Medicine.MedicineSchedule> medicineScheduleArrayList;
    ArrayList<Medicine.Time> completedTimes;
    AbstractDBAdapter abstractDBAdapter;

//    public ScheduleFragment() {
//        // Required empty public constructor
//    }


    public ScheduleFragment() {
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_schedule);
        tvDate= (TextView) findViewById(R.id.tvDate);
        recViewSchedule= (RecyclerView) findViewById(R.id.recViewSchedule);
        abstractDBAdapter=new AbstractDBAdapter(this);
        Medicine medicine = (Medicine) getIntent().getSerializableExtra("data");
;
        recViewSchedule.setLayoutManager(new LinearLayoutManager(this));

       // medicineScheduleArrayList = abstractDBAdapter.getScheduleOfMedicine()

        Calendar c=Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("dd+MMM-yyyy");
        String formattedDate = df.format(c.getTime());

        medicineScheduleArrayList = abstractDBAdapter.getScheduleOfMedicine(medicine.getId(),formattedDate);
        completedTimes = abstractDBAdapter.getTimesByMedicine(medicine.getId());

        recViewSchedule.setAdapter(new ScheduleAdapter(medicineScheduleArrayList,completedTimes,this));
    }

//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        // Inflate the layout for this fragment
//        View v= inflater.inflate(R.layout.fragment_schedule, container, false);
//        tvDate=v.findViewById(R.id.tvDate);
//        recViewSchedule=v.findViewById(R.id.recViewSchedule);
//        abstractDBAdapter=new AbstractDBAdapter(getContext());
//
//        recViewSchedule.setLayoutManager(new LinearLayoutManager(getContext()));
//
//        Calendar c=Calendar.getInstance();
//        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
//        String formattedDate = df.format(c.getTime());
////        medicineScheduleArrayList.addAll(abstractDBAdapter.getScheduleOfMedicine(1,formattedDate));
//
//
//        recViewSchedule.setAdapter(new ScheduleAdapter(medicineScheduleArrayList,completedTimes,getContext()));
//        return v;
//    }

}
