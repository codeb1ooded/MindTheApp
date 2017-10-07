package in.ac.igdtuw.mindtheapp;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import in.ac.igdtuw.mindtheapp.database.AbstractDBAdapter;
import in.ac.igdtuw.mindtheapp.models.Medicine;

/**
 * Created by megha on 07/10/17.
 */

public class AddMedicineActivity extends AppCompatActivity {


    EditText name, description, disease, total_days, total_pills;
    TextView timeTv;
    Button submit;
    ImageView add;

    AbstractDBAdapter database;

    LinearLayout parent;

    int time = 0;
    ArrayList<String> times = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_medicine);

        name = (EditText) findViewById(R.id.name);
        description = (EditText) findViewById(R.id.description);
        disease = (EditText) findViewById(R.id.disease);
        total_days = (EditText) findViewById(R.id.days);
        total_pills = (EditText) findViewById(R.id.pills);

        timeTv = (TextView) findViewById(R.id.times_tv);

        submit = (Button) findViewById(R.id.submit);
        add = (ImageView) findViewById(R.id.add_time);

        parent = (LinearLayout) findViewById(R.id.parent);

        database = new AbstractDBAdapter(this);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(AddMedicineActivity.this);
                alertDialog.setTitle("ADD TIME TO TAKE MEDICINE");

                final EditText input = new EditText(AddMedicineActivity.this);
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.MATCH_PARENT);
                input.setLayoutParams(lp);
                alertDialog.setView(input);

                alertDialog.setPositiveButton("YES",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                timeTv.setText((++time)+"");
                                TextView textView = new TextView(AddMedicineActivity.this);
                                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                                        LinearLayout.LayoutParams.MATCH_PARENT,
                                        LinearLayout.LayoutParams.WRAP_CONTENT);
                                textView.setLayoutParams(lp);
                                textView.setText(input.getText());
                                times.add(input.getText().toString());
                                parent.addView(textView);
                            }
                        });

                alertDialog.setNegativeButton("NO",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });

                alertDialog.show();
            }

        });


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                long id = database.getNextMedicineId();
                database.insertMedicine(new Medicine(id, name.getText().toString(),
                        time, Integer.parseInt(total_pills.getText().toString()),
                        Integer.parseInt(total_days.getText().toString()),
                        Integer.parseInt(total_days.getText().toString()),
                        disease.getText().toString(),
                        description.getText().toString(),
                        null));
                for(String s: times){
                    database.insertTime(new Medicine.Time(database.getNextTimeId(), id, s));
                }
                AddMedicineActivity.this.finish();
            }
        });
    }

}
