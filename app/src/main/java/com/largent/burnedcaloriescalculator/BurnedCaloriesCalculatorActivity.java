package com.largent.burnedcaloriescalculator;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import android.widget.Toast;

import java.text.NumberFormat;

import static com.largent.burnedcaloriescalculator.R.id.bmiLabel;
import static com.largent.burnedcaloriescalculator.R.id.milesSB;

public class BurnedCaloriesCalculatorActivity extends AppCompatActivity implements OnEditorActionListener {

    //define our widget variables
    private TextView WeightLabel;
    private TextView MilesLabel;
    private TextView MilesOutput;
    private TextView CaloriesLabel;
    private TextView CaloriesOutput;
    private TextView HeightLabel;
    private TextView bmiLabel;
    private TextView bmiOutput;
    private SeekBar milesSB;
    private EditText WeightOutput;
    private EditText NameET;
    private Spinner HeightOutFeet;
    private Spinner HeightOutInch;


    //define instance variable
    private String weight = "";
    private float miles = 0;
    private int feet = 0;
    private int inches = 0;

    private SharedPreferences savedValues;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_burned_calories_calculator);

        //get reference to the widgets
        WeightLabel = (TextView) findViewById(R.id.WeightLabel);
        MilesLabel = (TextView) findViewById(R.id.MilesLabel);
        MilesOutput = (TextView) findViewById(R.id.MilesOutput);
        CaloriesLabel = (TextView) findViewById(R.id.CaloriesLabel);
        CaloriesOutput = (TextView) findViewById(R.id.CaloriesOutput);
        HeightLabel = (TextView) findViewById(R.id.HeightLabel);
        bmiLabel = (TextView) findViewById(R.id.bmiLabel);
        bmiOutput = (TextView) findViewById(R.id.bmiOutput);
        milesSB = (SeekBar) findViewById(R.id.milesSB);
        WeightOutput = (EditText) findViewById(R.id.WeightOutput);
        NameET = (EditText) findViewById(R.id.NameET);
        HeightOutFeet = (Spinner) findViewById(R.id.HeightOutFeet);
        HeightOutInch = (Spinner) findViewById(R.id.HeightOutInch);


        //set the listener for the event
        WeightOutput.setOnEditorActionListener(this);

        milesSB.setOnSeekBarChangeListener(seekBarChangeListener);

        //create array adaptor for feet
        ArrayAdapter<CharSequence> feet_adapter = ArrayAdapter.createFromResource(
                this, R.array.array_feet, android.R.layout.simple_spinner_item
        );

        //set the layout for the dropdown list
        feet_adapter.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item
        );

        //set the adapter for the feet spinner
        HeightOutFeet.setAdapter(feet_adapter);

        //anonymous inner class as the listener
        HeightOutFeet.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                int pos = HeightOutFeet.getSelectedItemPosition()+1;
                String selectedText = (String) HeightOutFeet.getSelectedItem();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //create array adaptor for inches
        ArrayAdapter<CharSequence> inches_adapter = ArrayAdapter.createFromResource(
                this, R.array.array_inch, android.R.layout.simple_spinner_item
        );

        //set the layout for the dropdown list
        inches_adapter.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item
        );

        //set the adapter for the feet spinner
        HeightOutInch.setAdapter(inches_adapter);

        //anonymous inner class as the listener
        HeightOutInch.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                int pos = HeightOutInch.getSelectedItemPosition()+1;
                String selectedText = (String) HeightOutInch.getSelectedItem();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

        if(actionId == EditorInfo.IME_ACTION_DONE ||
                actionId == EditorInfo.IME_ACTION_UNSPECIFIED){
            calculateAndDisplay();
        }

        //hide soft keyboard
        return false;
    }


    //anonymous class
    private SeekBar.OnSeekBarChangeListener seekBarChangeListener = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            MilesOutput.setText(progress + "");
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) { calculateAndDisplay();
        }
    };


    private void calculateAndDisplay() {

        float Weight = Float.parseFloat(WeightOutput.getText().toString());
        miles = Float.parseFloat(String.valueOf(miles));


        float Calories = (float) 0.75 * Weight * miles;
        float BMI = (Weight * 703) + feet;
        //        /((12*feet+inches)*(12*feet+inches));

        CaloriesOutput.setText(String.valueOf(Calories));
        bmiOutput.setText(String.valueOf(BMI));

    }

  /*  @Override
    protected void onPause(){

        SharedPreferences.Editor editor = savedValues.edit();
        editor.putString("weight", weight);
        editor.putFloat("miles", miles);
        editor.putInt("feet", feet);
        editor.putInt("inches", inches);
        editor.commit();

        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();

        weight = savedValues.getString("weight", "");
        miles = savedValues.getFloat("miles", 1);
        feet = savedValues.getInt("feet", 3);
        inches = savedValues.getInt("inches", 0);

        calculateAndDisplay();
    }*/





}
