package in.ac.igdtuw.mindtheapp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;

import in.ac.igdtuw.mindtheapp.models.Medicine;

import static in.ac.igdtuw.mindtheapp.models.Medicine.MedicineSchedule;

/**
 * Created by nishtha on 7/10/17.
 */

public class ScheduleAdapter extends RecyclerView.Adapter<ScheduleAdapter.ScheduleHolder> {

    ArrayList<MedicineSchedule> medicineScheduleArrayList;
    ArrayList<Medicine.Time> completedTimes;
    Context context;

    public ScheduleAdapter(ArrayList<MedicineSchedule> medicineScheduleArrayList, ArrayList<Medicine.Time> completedTimes, Context context) {
        this.medicineScheduleArrayList = medicineScheduleArrayList;
        this.completedTimes = completedTimes;
        this.context = context;
    }

    @Override
    public ScheduleHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ScheduleHolder(LayoutInflater.from(context).inflate(R.layout.single_row_schedule,parent,false));
    }

    @Override
    public void onBindViewHolder(ScheduleHolder holder, int position) {
        Medicine.Time medicine = completedTimes.get(position);
        holder.tvTime.setText(medicine.getTime_string());
        boolean flag=false;
        for(int i=0; i<medicineScheduleArrayList.size();i++){
            if(medicine.getTime().equals(medicineScheduleArrayList.get(position).getTime())){
                flag=true;
                break;
            }
        }
        if(flag){
            //taken
            holder.ibStatus.setImageResource(R.drawable.greencheck);

        } else {

            if (medicine.getTime_string().compareToIgnoreCase(Calendar.getInstance().getTime().toString()) >= 0) {
                //not taken
                holder.ibStatus.setImageResource(R.drawable.yellow_circle);
            } else {
                //pending
                holder.ibStatus.setImageResource(R.drawable.redcross);
            }
        }
    }

    @Override
    public int getItemCount() {
        return completedTimes.size();
    }

    class ScheduleHolder extends RecyclerView.ViewHolder{
        TextView tvTime;
        ImageButton ibStatus;
        public ScheduleHolder(View itemView) {
            super(itemView);
            tvTime=itemView.findViewById(R.id.tvTime);
            ibStatus=itemView.findViewById(R.id.ibStatus);
        }
    }
}
