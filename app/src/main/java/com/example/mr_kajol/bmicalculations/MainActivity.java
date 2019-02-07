package com.example.mr_kajol.bmicalculations;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

     Button bmibtn;
     EditText height;
     EditText weight;
     TextView tvheight;
     TextView tvshowbmi;
     TextView tvweight;
     TextView heightTv;
     TextView weightTv;
     Spinner heightunits;
     Spinner weightunits;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bmibtn = findViewById(R.id.btnbmi);
        height = findViewById(R.id.height);
        weight = findViewById(R.id.weight);
        tvheight = findViewById(R.id.tvheight);
        tvweight = findViewById(R.id.tvWeight);
        heightunits = findViewById(R.id.heightunits);
        weightunits = findViewById(R.id.weightunits);
        tvshowbmi = findViewById(R.id.tvshowbmi);
        heightTv = findViewById(R.id.heighttv);


        bmibtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        try {
            Double Height = Double.parseDouble(height.getText().toString());
            Double Weight = Double.parseDouble(weight.getText().toString());

            String HeighUnit, WeightUnit;

            double bmi, temp, check;

      /*  if(HeighUnit.contains("foot")){
             double a;
             a =  30.48;
             Height = Height * a;
            Toast.makeText(MainActivity.this, "This is Foot.", Toast.LENGTH_LONG).show();
        }
*/
            temp = Height / 100;
            check = temp * temp;
            bmi = (Weight / check);
            String dc = new DecimalFormat("##.##").format(bmi);
            tvshowbmi.setText("Your BMI is : " + dc);

        }catch (Exception e){
            Toast.makeText(MainActivity.this,"Please Fill up the info.", Toast.LENGTH_LONG).show();
        }


    }

    public void Calculations(){
       /// String height, weight;

    }
}
