package segev.gimfit;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Calendar;

/**
 * Created by segevcohen on 02/01/2018.
 */

public class add_session extends AppCompatActivity implements View.OnClickListener {
    String datePicker;
    String timePicker;
    String description;
    String hours;
    String minutes;
    String seconds;
    String steps;
    String calories;
    String distanse;
    private EditText btnDatePicker;
    private EditText btnTimePicker;
    private Button saveButton;
    private int mYear, mMonth, mDay, mHour, mMinute;
    private Firebase mRoot;
    private Spinner spinner;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_session_layout);

        btnDatePicker=(EditText) findViewById(R.id.date_picker_id);
        btnTimePicker=(EditText)findViewById(R.id.time_picker_id);
        saveButton = (Button) findViewById(R.id.saveButton);
        saveButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                saveandsend();

                }
            });

        spinner = findViewById(R.id.session_type_id);

        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this,
                R.array.typeoftrainning, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter1);
        btnDatePicker.setOnClickListener(this);
        btnTimePicker.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        if (v == btnDatePicker) {

            // Get Current Date
            final Calendar c = Calendar.getInstance();
            mYear = c.get(Calendar.YEAR);
            mMonth = c.get(Calendar.MONTH);
            mDay = c.get(Calendar.DAY_OF_MONTH);


            DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                    new DatePickerDialog.OnDateSetListener() {

                        @Override
                        public void onDateSet(DatePicker view, int year,
                                              int monthOfYear, int dayOfMonth) {

                            btnDatePicker.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                        }
                    }, mYear, mMonth, mDay);
            datePickerDialog.show();
        }
        if (v == btnTimePicker) {

            // Get Current Time
            final Calendar c = Calendar.getInstance();
            mHour = c.get(Calendar.HOUR_OF_DAY);
            mMinute = c.get(Calendar.MINUTE);

            // Launch Time Picker Dialog
            TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                    new TimePickerDialog.OnTimeSetListener() {

                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay,
                                              int minute) {

                            btnTimePicker.setText(hourOfDay + ":" + minute);
                        }
                    }, mHour, mMinute, false);
            timePickerDialog.show();
        }
    }

    public void saveandsend(){
        session_data sessionData = new session_data();
        int num=0;
        EditText dateText=(EditText)findViewById(R.id.date_picker_id);
        EditText timeText=(EditText)findViewById(R.id.time_picker_id);
        EditText descriptionText=(EditText)findViewById(R.id.description_text_id);
        EditText hoursText=(EditText)findViewById(R.id.hours_text_id);
        EditText minutesText=(EditText)findViewById(R.id.minuets_text_id);
        EditText secondsText=(EditText)findViewById(R.id.seconds_text_id);
        EditText stepsText=(EditText)findViewById(R.id.steps_text_id);
        EditText caloriesText=(EditText)findViewById(R.id.calories_text_id);
        EditText distanseText=(EditText)findViewById(R.id.distance_text_id);



        datePicker=dateText.getText().toString();
        timePicker=timeText.getText().toString();
        description=descriptionText.getText().toString();
        hours=hoursText.getText().toString();
        minutes=minutesText.getText().toString();
        seconds=secondsText.getText().toString();
        steps=stepsText.getText().toString();
        calories=caloriesText.getText().toString();
        distanse=distanseText.getText().toString();
        final TextView errorText = (TextView) spinner.getSelectedView();
        final String typeOfTrainnig = spinner.getSelectedItem().toString();

        if(datePicker.matches("")){
            dateText.setBackgroundResource(R.drawable.border2);
            dateText.setError("OOPS... this field should not be empty");}
        else{ dateText.setBackgroundResource(R.drawable.border);
            num++;
            sessionData.setDate(datePicker);}
        if(timePicker.matches("")){
            timeText.setBackgroundResource(R.drawable.border2);
            timeText.setError("OOPS... this field should not be empty");}
        else{ timeText.setBackgroundResource(R.drawable.border);
            num++;
            sessionData.setTime(timePicker);}
        if(description.matches("")){
            descriptionText.setBackgroundResource(R.drawable.border2);
            descriptionText.setError("OOPS... this field should not be empty");}
        else{ descriptionText.setBackgroundResource(R.drawable.border);
            num++;
            sessionData.setDescription(description);}
        if(hours.matches("")){
            hoursText.setBackgroundResource(R.drawable.border2);
            hoursText.setError("OOPS... this field should not be empty");}
        else{ hoursText.setBackgroundResource(R.drawable.border);
            num++;
            sessionData.setHour(hours);}
        if(minutes.matches("")){
            minutesText.setBackgroundResource(R.drawable.border2);
            minutesText.setError("OOPS... this field should not be empty");}
        else{ minutesText.setBackgroundResource(R.drawable.border);
            num++;
            sessionData.setMinute(minutes);}
        if(seconds.matches("")){
            secondsText.setBackgroundResource(R.drawable.border2);
            secondsText.setError("OOPS... this field should not be empty");}
        else{ secondsText.setBackgroundResource(R.drawable.border);
            num++;
            sessionData.setSecond(seconds);}
        if(steps.matches("")){
            stepsText.setBackgroundResource(R.drawable.border2);
            stepsText.setError("OOPS... this field should not be empty");}
        else{ stepsText.setBackgroundResource(R.drawable.border);
            num++;
            sessionData.setStep(steps);}
        if(calories.matches("")){
            caloriesText.setBackgroundResource(R.drawable.border2);
            caloriesText.setError("OOPS... this field should not be empty");}
        else{ caloriesText.setBackgroundResource(R.drawable.border);
            num++;
            sessionData.setCalories(calories);}
        if(distanse.matches("")){
            distanseText.setBackgroundResource(R.drawable.border2);
            distanseText.setError("OOPS... this field should not be empty");}
        else{ distanseText.setBackgroundResource(R.drawable.border);
            num++;
            sessionData.setDistance(distanse);}

        if (typeOfTrainnig.equals("Select the type of trainning")) {
            spinner.setBackgroundResource(R.drawable.border2);
            errorText.setError("You did not select");
        } else {
            spinner.setBackgroundResource(R.drawable.border);
            sessionData.setTypeoftrining(typeOfTrainnig);
            num = num + 1;
        }

        if (num==10){

                mRoot = new Firebase("https://gimfit-654d0.firebaseio.com/trainee/" + FirebaseAuth.getInstance().getCurrentUser().getUid().toString()  );
                mRoot.child("traning data").child(timePicker).setValue(sessionData);
            Intent intent = new Intent(add_session.this, athlete_area_playground.class);
            startActivity(intent);
        }
        else {
            Toast.makeText(add_session.this, "cant save the data", Toast.LENGTH_LONG).show();
        }

    }

}




