package in.ac.igdtuw.mindtheapp;


import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import in.ac.igdtuw.mindtheapp.database.AbstractDBAdapter;
import in.ac.igdtuw.mindtheapp.models.Medicine;


/**
 * A simple {@link Fragment} subclass.
 */
public class MedicinesFragment extends Fragment implements View.OnClickListener{
    FloatingActionButton fabAddMedicine;
    RecyclerView recViewMedicines;
    MedicinesAdapter medicinesAdapter;
    ArrayList<Medicine> medicineArrayList;
    AbstractDBAdapter abstractDBAdapter;
    public MedicinesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.fragment_medicines, container, false);

        fabAddMedicine = v.findViewById(R.id.fabAddMedicine);
        recViewMedicines= v.findViewById(R.id.recViewMedicines);
        abstractDBAdapter=new AbstractDBAdapter(getContext());

        medicineArrayList.addAll(abstractDBAdapter.getAllMedicines());

        medicinesAdapter=new MedicinesAdapter(getContext(),medicineArrayList);
        recViewMedicines.setLayoutManager(new LinearLayoutManager(getContext()));
        recViewMedicines.setAdapter(medicinesAdapter);
        fabAddMedicine.setOnClickListener(this);
        return v;
    }

    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.fabAddMedicine){
//            Intent intent=new Intent()
        }
    }
}
