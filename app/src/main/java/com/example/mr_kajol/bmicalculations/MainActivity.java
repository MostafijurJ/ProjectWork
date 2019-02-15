package com.example.mr_kajol.bmicalculations;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

import static java.lang.Math.floor;

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
     Spinner genderspiner;

     int heightindex, weightindex,genderindex;
     Double heightincm, weightinkg;
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
        genderspiner = findViewById(R.id.genderspiner);


        bmibtn.setOnClickListener(this);

        // GENDER
        ArrayAdapter<CharSequence> gender = ArrayAdapter.createFromResource(this,R.array.SEX, android.R.layout.simple_spinner_item);
        gender.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        genderspiner.setAdapter(gender);


        //Height
        ArrayAdapter<CharSequence> heightadepter = ArrayAdapter.createFromResource(this,R.array.heightunits, android.R.layout.simple_spinner_item);
        heightadepter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        heightunits.setAdapter(heightadepter);

        // Weight
        ArrayAdapter<CharSequence> weightadepter = ArrayAdapter.createFromResource(this,R.array.weightunits, android.R.layout.simple_spinner_item);
        weightadepter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        weightunits.setAdapter(weightadepter);

        genderspiner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                //Toast.makeText(MainActivity.this," Gender ", Toast.LENGTH_LONG).show();

                genderindex = position;
            }
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }
        });


        weightunits.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
               //Toast.makeText(MainActivity.this," Weight", Toast.LENGTH_LONG).show();

               weightindex = position;
            }
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }
        });

        heightunits.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                 heightindex = position;
                //Toast.makeText(MainActivity.this,"Height", Toast.LENGTH_LONG).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }
        });
    }


    @Override
    public void onClick(View v) {

        try {
             Double Height = Double.parseDouble(height.getText().toString());
             Double Weight = Double.parseDouble(weight.getText().toString());

             //for height
           if(heightindex == 0){
               heightincm = (Height); }
           else if(heightindex == 1){
               double temp, fractional,last;
               temp = floor(Height);
               fractional = (Height - temp)*10;
               // i inch = 2.54 cm  1 foot = 30.48 cm
                      heightincm = (temp * 30.48)+ (fractional * 2.54);
                      //Toast.makeText(MainActivity.this, "fractional"+heightincm,Toast.LENGTH_LONG).show();
           }
               // for weight
            if(weightindex == 0){
                weightinkg = (Weight); }
            else if(weightindex == 1){
                weightinkg = (Weight * 0.453592); }

            double bmi, temp, check=1;

            temp = heightincm / 100;
            check = temp * temp;
            bmi = (weightinkg / check);
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
