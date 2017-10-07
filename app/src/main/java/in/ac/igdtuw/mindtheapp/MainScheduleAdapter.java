package in.ac.igdtuw.mindtheapp;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import in.ac.igdtuw.mindtheapp.models.Medicine;

/**
 * Created by nishtha on 7/10/17.
 */
// TODO: 7/10/17 Edit adapter

public class MainScheduleAdapter extends RecyclerView.Adapter<MainScheduleAdapter.MainScheduleHolder> {

    Context context;
    ArrayList<Medicine.MedicineSchedule> medicineScheduleArrayList;
    ArrayList<Medicine> medicineArrayList;

    @Override
    public MainScheduleHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MainScheduleHolder(LayoutInflater.from(context).inflate(R.layout.single_row_main_schedule,parent,false));
    }

    @Override
    public void onBindViewHolder(MainScheduleHolder holder, int position) {
        Medicine.MedicineSchedule medicineSchedule=medicineScheduleArrayList.get(position);
        holder.tvTimeMainSchedule.setText(medicineSchedule.getTime());
        holder.recViewMedicinesForSingleTime.setLayoutManager(new CustomLinearLayoutManager(context));
//        holder.recViewMedicinesForSingleTime.setAdapter();
    }

    @Override
    public int getItemCount() {
        return medicineScheduleArrayList.size();
    }

    class MainScheduleHolder extends RecyclerView.ViewHolder{
        TextView tvTimeMainSchedule;
        RecyclerView recViewMedicinesForSingleTime;
        public MainScheduleHolder(View itemView) {
            super(itemView);
            tvTimeMainSchedule=itemView.findViewById(R.id.tvTimeMainSchedule);
            recViewMedicinesForSingleTime=itemView.findViewById(R.id.recViewMedicinesForSingleTime);

        }
    }

    private class CustomLinearLayoutManager extends LinearLayoutManager {
        public CustomLinearLayoutManager(Context context) {
            super(context);
        }

        @Override
        public boolean canScrollVertically() {
            return false;
        }
    }

    class ListOfMedicinesAdapter extends RecyclerView.Adapter<ListOfMedicinesAdapter.ListHolder>{
        @Override
        public ListHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return null;
        }

        @Override
        public void onBindViewHolder(ListHolder holder, int position) {

        }

        @Override
        public int getItemCount() {
            return 0;
        }

        class ListHolder extends RecyclerView.ViewHolder {

            public ListHolder(View itemView) {
                super(itemView);
            }
        }
    }
}
