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
     TextView tvbmistatus;

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
        tvshowbmi = findViewById(R.id.tvshowbmi);
        tvbmistatus = findViewById(R.id.bmistatus);

        heightunits = findViewById(R.id.heightunits);
        weightunits = findViewById(R.id.weightunits);


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

        bmi level

                Very severely underweight  0-15
                Severely underweight 15 -16
                Underweight	 16 -18.5
                Normal (healthy weight) 18.5 -25
                Overweight 25 - 30

*/
            temp = Height / 100;
            check = temp * temp;
            bmi = (Weight / check);
            String dc = new DecimalFormat("##.##").format(bmi);
            tvshowbmi.setText("Your BMI is : " + dc);

            ///bmi status
            String Status = "Current Status: ";
            String Status1 = "Very severely underweight.";
            String Status2 = "Severely underweight";
            String Status3 = "Underweight";
            String Status4 = "Normal (healthy weight)";
            String Status5 = "Overweight";

            if(StatusCheck(bmi)==-1){
                tvbmistatus.setText(Status+Status1);
            }
            if(StatusCheck(bmi)==1){
                tvbmistatus.setText(Status+Status2);
            }
            if(StatusCheck(bmi)==2){
                tvbmistatus.setText(Status+Status3);
            }
            if(StatusCheck(bmi)==3){
                tvbmistatus.setText(Status+Status4);
            }
            if(StatusCheck(bmi)==4){
                tvbmistatus.setText(Status+Status5);
            }
            if(StatusCheck(bmi)==55){
                tvbmistatus.setText(Status+"Strongly OverWeight");
            }


        }catch (Exception e){
            Toast.makeText(MainActivity.this,"Please Fill up the info.", Toast.LENGTH_LONG).show();
        }


    }

  public  double StatusCheck(Double Bmi){

        if(Bmi < 15)
            return  -1;
        else if(Bmi > 15 && Bmi < 16)
            return 1;
        else if(Bmi >= 16 && Bmi < 18.5)
            return 2;
        else if(Bmi > 18.5 && Bmi < 25)
            return 3;
        else if(Bmi > 25 && Bmi < 30)
            return 4;
        else if(Bmi>30)return 55;

        return 6;
  }
}
