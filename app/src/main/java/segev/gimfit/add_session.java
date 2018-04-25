package segev.gimfit;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_session_layout);

        btnDatePicker=(EditText) findViewById(R.id.date_picker_id);
        btnTimePicker=(EditText)findViewById(R.id.time_picker_id);
        saveButton = (Button) findViewById(R.id.saveButton);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveandsend();
            }
        });

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

        if(datePicker.matches("")){
            dateText.setBackgroundResource(R.drawable.border2);
            dateText.setError("OOPS... this field should not be empty");}
        else{ dateText.setBackgroundResource(R.drawable.border);
            num++;
            sessionData.setDate(datePicker);}
        if(timePicker.matches("")){
            dateText.setBackgroundResource(R.drawable.border2);
            dateText.setError("OOPS... this field should not be empty");}
        else{ dateText.setBackgroundResource(R.drawable.border);
            num++;
            sessionData.setDate(timePicker);}
        if(description.matches("")){
            dateText.setBackgroundResource(R.drawable.border2);
            dateText.setError("OOPS... this field should not be empty");}
        else{ dateText.setBackgroundResource(R.drawable.border);
            num++;
            sessionData.setDate(description);}
        if(hours.matches("")){
            dateText.setBackgroundResource(R.drawable.border2);
            dateText.setError("OOPS... this field should not be empty");}
        else{ dateText.setBackgroundResource(R.drawable.border);
            num++;
            sessionData.setDate(hours);}
        if(minutes.matches("")){
            dateText.setBackgroundResource(R.drawable.border2);
            dateText.setError("OOPS... this field should not be empty");}
        else{ dateText.setBackgroundResource(R.drawable.border);
            num++;
            sessionData.setDate(minutes);}
        if(seconds.matches("")){
            dateText.setBackgroundResource(R.drawable.border2);
            dateText.setError("OOPS... this field should not be empty");}
        else{ dateText.setBackgroundResource(R.drawable.border);
            num++;
            sessionData.setDate(seconds);}
        if(steps.matches("")){
            dateText.setBackgroundResource(R.drawable.border2);
            dateText.setError("OOPS... this field should not be empty");}
        else{ dateText.setBackgroundResource(R.drawable.border);
            num++;
            sessionData.setDate(steps);}
        if(calories.matches("")){
            dateText.setBackgroundResource(R.drawable.border2);
            dateText.setError("OOPS... this field should not be empty");}
        else{ dateText.setBackgroundResource(R.drawable.border);
            num++;
            sessionData.setDate(calories);}
        if(distanse.matches("")){
            dateText.setBackgroundResource(R.drawable.border2);
            dateText.setError("OOPS... this field should not be empty");}
        else{ dateText.setBackgroundResource(R.drawable.border);
            num++;
            sessionData.setDate(distanse);}

            if (num==9){

                mRoot = new Firebase("https://gimfit-654d0.firebaseio.com/trainee/niv"+ FirebaseAuth.getInstance().getCurrentUser().getUid());

                mRoot.child("Extra").setValue(sessionData);
            }

    }

}




