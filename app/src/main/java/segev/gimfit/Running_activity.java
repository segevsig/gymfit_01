package segev.gimfit;

/**
 * Created by LENOVO on 24/12/2017.
 */

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.MultiAutoCompleteTextView;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;


public class Running_activity extends AppCompatActivity implements View.OnClickListener {

    private SeekBar distanceSeekbar;
    private SeekBar durationSeekbar;
    private Spinner workoutType;
    private MultiAutoCompleteTextView description;
    private EditText btnDatePicker;
    private EditText btnTimePicker;
    private int mYear, mMonth, mDay, mHour, mMinute;




    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.running_layout);
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.navigation);
        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);
        distanceSeekbar =(SeekBar) findViewById(R.id.distanseSeekBar);
        durationSeekbar = (SeekBar) findViewById(R.id.durationSeekBar);
        workoutType = (Spinner) findViewById(R.id.spinnerWorkoutType) ;
        description = (MultiAutoCompleteTextView) findViewById(R.id.description);
        btnDatePicker=(EditText) findViewById(R.id.date_picker_id);
        btnTimePicker=(EditText)findViewById(R.id.time_picker_id);
        btnDatePicker.setOnClickListener(this);
        btnTimePicker.setOnClickListener(this);


        Spinner spinnerRunning = (Spinner) findViewById(R.id.spinnerWorkoutType);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.TypeOfTraningRunning, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerRunning.setAdapter(adapter);

        distanceSeekbar.setMax(50);
        distanceSeekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progress = 0 ;
            @Override
            public void onProgressChanged(SeekBar seekBar, int progressSeek, boolean fromUser) {
                progress = progressSeek ;
              //  runningDistanceKmId.setText(String.valueOf(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
              //  runningDistanceKmId.setText(String.valueOf(progress));

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
              //  runningDistanceKmId.setText(String.valueOf(progress));


            }
        });

        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(0);
        menuItem.setChecked(true);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){

                    case R.id.navigation_running:
                        Toast.makeText(Running_activity.this,"You are on the requested page ",Toast.LENGTH_SHORT).show();
                        break;

                    case R.id.navigation_biking:
                        Toast.makeText(Running_activity.this,"Creating biking workout",Toast.LENGTH_SHORT).show();
                        Intent intent2 = new Intent(Running_activity.this, Biking_activity.class);
                        startActivity(intent2);
                        break;

                    case R.id.navigation_gym:
                        Toast.makeText(Running_activity.this,"Creating gym workout",Toast.LENGTH_SHORT).show();
                        Intent intent3 = new Intent(Running_activity.this, Gym_activity.class);
                        startActivity(intent3);
                        break;
                }


                return false;
            }
        });
    }
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


}