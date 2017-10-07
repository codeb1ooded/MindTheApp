package in.ac.igdtuw.mindtheapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

import in.ac.igdtuw.mindtheapp.database.AbstractDBAdapter;
import in.ac.igdtuw.mindtheapp.models.Medicine;

/**
 * Created by nishtha on 7/10/17.
 */

public class MedicinesAdapter extends RecyclerView.Adapter<MedicinesAdapter.MedicinesHolder> {
    Context context;
    ArrayList<Medicine> medicineArrayList;
    AbstractDBAdapter abstractDBAdapter=new AbstractDBAdapter(context);

    public MedicinesAdapter(Context context, ArrayList<Medicine> medicineArrayList) {
        this.context = context;
        this.medicineArrayList = medicineArrayList;
    }

    @Override
    public MedicinesHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MedicinesHolder medicinesHolder=new MedicinesHolder(LayoutInflater.from(context).inflate(R.layout.single_row_medicine,parent,false));

        return medicinesHolder;
    }

    @Override
    public void onBindViewHolder(MedicinesHolder holder, int position) {
        final Medicine medicine=medicineArrayList.get(position);
        holder.tVMedicineName.setText(medicine.getName());
        holder.ibDeleteMedicine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                abstractDBAdapter.deleteMedicine(medicine.getId());
            }
        });
        holder.cardViewMedicine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent();
                intent.setClass(context, ScheduleFragment.class);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return medicineArrayList.size();
    }

    class MedicinesHolder extends RecyclerView.ViewHolder{
        CardView cardViewMedicine;
        TextView tVMedicineName;
        ImageButton ibDeleteMedicine;
        public MedicinesHolder(View itemView) {
            super(itemView);
            tVMedicineName=itemView.findViewById(R.id.tvMedicineName);
            ibDeleteMedicine=itemView.findViewById(R.id.ibDeleteMedicine);
            cardViewMedicine=itemView.findViewById(R.id.cardViewMedicine);
        }
    }
}
